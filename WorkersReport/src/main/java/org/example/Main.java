package org.example;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String directoryPath;

        // Input directory path from user
        System.out.print("Enter the directory path containing Excel files: ");
        directoryPath = scanner.nextLine().trim();

        // Validate directory path
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Error: The specified directory does not exist or is invalid.");
            return;
        }

        // Read Excel files from the specified directory
        ExcelReader reader = new ExcelReader();
        List<Task> tasks = reader.readExcelFiles(directoryPath);

        if (tasks.isEmpty()) {
            System.out.println("No tasks were found in the provided Excel files.");
            return;
        }

        TaskManager taskManager = new TaskManager(tasks);

        // Main menu loop
        boolean exit = false;
        while (!exit) {
            // Display options to the user
            System.out.println("\nChoose an option:");
            System.out.println("1. Display ranking of workers by total hours worked");
            System.out.println("2. Display ranking of months by total hours worked");
            System.out.println("3. Display top 10 most productive days");
            System.out.println("4. Exit");

            // Prompt user for choice
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    taskManager.displayWorkerRanking();
                    break;
                case "2":
                    taskManager.displayMonthRanking();
                    break;
                case "3":
                    taskManager.displayTop10Days();
                    break;
                case "4":
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        // Close scanner
        scanner.close();
    }
}


