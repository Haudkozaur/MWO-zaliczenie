package org.example;

import java.util.*;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void displayWorkerRanking() {
        Map<String, Integer> hoursWorked = calculateHoursWorked();

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(hoursWorked.entrySet());
        sortedList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        System.out.println("Ranking of workers by total hours worked:");
        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println((i + 1) + ". " + sortedList.get(i).getKey() + ": " + sortedList.get(i).getValue() + " hours");
        }
    }

    public void displayMonthRanking() {
        Map<String, Integer> hoursByMonth = new HashMap<>();

        for (Task task : tasks) {
            String date = task.getDate();
            // Assuming date format is "dd/MM/yyyy"
            if (date.length() >= 7) { // Check if date string is long enough to extract month/year
                String monthYear = date.substring(3, 10); // Extracts "MM/yyyy"

                int time = task.getTime();

                if (hoursByMonth.containsKey(monthYear)) {
                    int currentHours = hoursByMonth.get(monthYear);
                    hoursByMonth.put(monthYear, currentHours + time);
                } else {
                    hoursByMonth.put(monthYear, time);
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(hoursByMonth.entrySet());
        sortedList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        System.out.println("\nRanking of months by total hours worked:");
        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println((i + 1) + ". " + sortedList.get(i).getKey() + " - " + sortedList.get(i).getValue() + " hours");
        }
    }

    public void displayTop10Days() {
        Map<String, Integer> hoursByDay = new HashMap<>();

        for (Task task : tasks) {
            String date = task.getDate();

            int time = task.getTime();

            if (hoursByDay.containsKey(date)) {
                int currentHours = hoursByDay.get(date);
                hoursByDay.put(date, currentHours + time);
            } else {
                hoursByDay.put(date, time);
            }
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(hoursByDay.entrySet());
        sortedList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        System.out.println("\nTop 10 most productive days:");
        for (int i = 0; i < Math.min(10, sortedList.size()); i++) {
            System.out.println((i + 1) + ". " + sortedList.get(i).getKey() + " - " + sortedList.get(i).getValue() + " hours");
        }
    }

    private Map<String, Integer> calculateHoursWorked() {
        Map<String, Integer> hoursWorked = new HashMap<>();

        for (Task task : tasks) {
            String workerName = task.getWorkerName();
            int time = task.getTime();

            if (hoursWorked.containsKey(workerName)) {
                int currentHours = hoursWorked.get(workerName);
                hoursWorked.put(workerName, currentHours + time);
            } else {
                hoursWorked.put(workerName, time);
            }
        }

        return hoursWorked;
    }
}