package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String directoryPath = "C:\\Users\\Julian Haudek\\Java_training\\faktura\\MWO_JH_zaliczenie\\src\\main\\resources";
            ExcelReader reader = new ExcelReader();
            List<Task> tasks = reader.readExcelFiles(directoryPath);

            if (tasks.isEmpty()) {
                System.out.println("No tasks were found in the provided Excel files.");
            } else {
                for (Task task : tasks) {
                    System.out.println(task);
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while reading the Excel files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
