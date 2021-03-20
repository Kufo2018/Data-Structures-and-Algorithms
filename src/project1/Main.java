package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Driver class for Project1
 */
public class Main {
    public static void main(String[] args) {
        runProgram(args);
    }

    private static void runProgram(String[] args) {
        Project1 project1;
        int response;

        System.out.println("Enter 1 to use command line arguments, 2 to manually input file names " +
                "or 3 to have the program generate an input and output file for you :");
        Scanner scanner = new Scanner(System.in);
        try {
            response = scanner.nextInt();
        } catch (InputMismatchException exception) {
            response = 3;
        }

        if (response == 1) {
            project1 = new Project1(args);
        } else if (response == 2) {
            System.out.print(
                    "Enter input file only and program will generate an output.txt file for you OR ");
            System.out.print(
                    "Enter input and output filenames separated by a comma e.g. " +
                            "MyInputFile.txt,MyOutputFile.txt: ");

            var userInput = scanner.next();
            if (userInput.isEmpty()) {
                System.out.println("Input file cannot be empty, please retry");
                return;
            } else {
                var fileNames = userInput.split(",");
                if (fileNames.length == 1) {
                    project1 = new Project1(fileNames[0], "output.txt");
                } else {
                    project1 = new Project1(fileNames[0], fileNames[1]);
                }
            }
        } else if (response == 3) project1 = new Project1();
        else {
            System.out.println("Invalid response");
            return;
        }

        System.out.println("Running algorithms...");
        project1.initializeProgram();
        project1.runSearchAlgorithms();
        scanner.close();
        System.out.println("Finished running algorithms");
    }
}
