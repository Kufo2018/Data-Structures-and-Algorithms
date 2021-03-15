/*
Author: David Kuforiji
Date: 11th March, 2021
Purpose: Implement sequential and binary search algorithms and test using standard input
 */

package project1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Project1 {

    private final ArrayList<Integer> dataArray = new ArrayList<>();
    private final ArrayList<Integer> queryArray = new ArrayList<>();
    private String inputFile = "";
    private String outputFile = "";
    private PrintWriter printWriter;
    Timer timer = new Timer();

    /**
     * Creates an object of this class using command line arguments
     *
     * @param args the String array containing the input and output file names
     */
    public Project1(String[] args) {
        try {
            this.inputFile = args[0];
            this.outputFile = args[1];
        } catch (Exception exception) {
            print("Error accessing command line argument(s)");
        }
    }

    /**
     * Creates an object of this class using two String values
     *
     * @param inputFile  the file to be processed
     * @param outputFile the file to be written to
     */
    public Project1(String inputFile, String outputFile) {
        try {
            this.inputFile = inputFile;
            this.outputFile = outputFile;
        } catch (Exception exception) {
            print("Error with input or output string");
        }
    }

    /**
     * Populates the dataArray and queryArray
     */
    public void initializeProgram() {
        try {
            BufferedReader input = new BufferedReader(new FileReader(inputFile));
            var line = input.readLine();
            var arraySize = Integer.parseInt(line.split(" ")[0]);
            var querySize = Integer.parseInt(line.split(" ")[1]);

            // Populates data array
            for (int i = 0; i < arraySize; i++) dataArray.add(Integer.parseInt(input.readLine()));

            // Populates query array
            for (int i = 0; i < querySize; i++) queryArray.add(Integer.parseInt(input.readLine()));

            initializePrintWriter();

        } catch (FileNotFoundException fileNotFoundException) {
            print("Error! Input or output file does not exist");
        } catch (IOException ioException) {
            print("Error! IO exception occurred while reading file");
        } catch (NumberFormatException numberFormatException) {
            print("Error! Input file contains invalid data");
        }
    }

    /**
     * Runs the search algorithms implemented in this class
     */
    public void runSearchAlgorithms() {
        var isQueryItemFound = false;

        if (printWriter != null) {
            for (int queryItem : queryArray) {

                // Runs a sequential search
                timer.startTimer();
                isQueryItemFound = sequentialSearch(queryItem);
                printWriter.print(isQueryItemFound + ":" + (timer.getElapsedTime()) + "ms ");

                // Runs a binary search
                isQueryItemFound = binarySearch(queryItem);
                printWriter.print(isQueryItemFound + ":" + (timer.getElapsedTime()) + "ms " + queryItem);

                printWriter.println();
            }
            printWriter.close();
        } else {
            print("Error! PrintWriter object not instantiated!");
        }
    }

    /**
     * Linear search algorithm
     *
     * @param query the integer query
     * @return a boolean value that indicates search status
     */
    private boolean sequentialSearch(int query) {
        boolean returnValue = false;
        for (int dataItem : dataArray) {
            if (dataItem == query) {
                returnValue = true;
                break;
            }
        }
        return returnValue;
    }

    /**
     * Binary search algorithm
     *
     * @param query the integer query
     * @return a boolean value that indicates search status
     */
    private boolean binarySearch(int query) {
        Collections.sort(dataArray);

        int start = 0;
        int end = dataArray.size() - 1;

        timer.startTimer();
        if (query > dataArray.get(dataArray.size() - 1)) return false;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (dataArray.get(mid).equals(query)) return true;
            if (dataArray.get(mid).compareTo(query) > 0) end = mid - 1;
            else start = mid + 1;
        }
        return false;
    }

    /**
     * Stub to print objects to console
     *
     * @param object the object to be printed out
     */
    private void print(Object object) {
        System.out.println(object);
    }

    /**
     * Creates a PrintWriter object that writes to outputFile
     * If outputFile is not provided, default is "output.txt"
     */
    private void initializePrintWriter() {
        try {
            if (outputFile.isEmpty()) outputFile = "output.txt";
            FileWriter fileWriter = new FileWriter(outputFile);
            printWriter = new PrintWriter(fileWriter);
        } catch (IOException ioException) {
            print("Error creating output file");
        }
    }

    /**
     * Inner class to abstract timer implementation
     */
    private static class Timer {
        private long startTime;

        public Timer() {
        }

        private void startTimer() {
            startTime = System.currentTimeMillis();
        }

        private long getElapsedTime() {
            return System.currentTimeMillis() - startTime;
        }
    }
}
