package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcelReader {
    public List<Task> readExcelFiles(String directoryPath) {
        List<Task> tasks = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            List<Path> files = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".xls") || path.toString().endsWith(".xlsx"))
                    .collect(Collectors.toList());

            System.out.println("Found " + files.size() + " Excel files.");

            for (Path filePath : files) {
                System.out.println("Processing file: " + filePath);
                String workerName = getWorkerName(filePath.getFileName().toString());
                tasks.addAll(readExcel(filePath.toString(), workerName));
            }
        } catch (IOException e) {
            System.err.println("Error listing or reading files in directory: " + directoryPath);
            e.printStackTrace();
        }
        return tasks;
    }

    private List<Task> readExcel(String filePath, String workerName) {
        List<Task> tasks = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = filePath.endsWith(".xls") ? new HSSFWorkbook(fis) : new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip the header row
                    continue;
                }
                String date = getCellValueAsString(row.getCell(0));
                String quest = getCellValueAsString(row.getCell(1));
                int time = (int) getCellValueAsNumeric(row.getCell(2));

                Task task = new Task(date, quest, time, workerName);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error processing Excel file: " + filePath);
            e.printStackTrace();
        }
        return tasks;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    return dateFormat.format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    private double getCellValueAsNumeric(Cell cell) {
        if (cell == null) {
            return 0;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0;
                }
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1 : 0;
            case FORMULA:
                return cell.getNumericCellValue();
            default:
                return 0;
        }
    }

    private String getWorkerName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
}
