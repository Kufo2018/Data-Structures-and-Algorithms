package project1;

public class Main {
    public static void main(String[] args) {

        Project1 project1 = new Project1("testFile.txt", "output.txt");
//        Project1 project1 = new Project1(args);
        project1.initializeProgram();
        project1.runSearchAlgorithms();
    }
}
