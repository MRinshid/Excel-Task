package com.example.Excel.util;
import com.example.Excel.entity.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static String EXCEL_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {
        return EXCEL_TYPE.equals(file.getContentType());
    }
    public static List<Employee> parseExcelFile(InputStream is) {
        List<Employee> employees = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                Employee employee = new Employee();

                employee.setName(getCellValueAsString(row.getCell(0)));
                employee.setEmail(getCellValueAsString(row.getCell(1)));
                employee.setDepartment(getCellValueAsString(row.getCell(2)));
                employee.setCreatedAt(getCellValueAsDateTime(row.getCell(3)));
                employee.setCreatedBy(getCellValueAsString(row.getCell(4)));
                employee.setUpdatedAt(getCellValueAsDateTime(row.getCell(5)));
                employee.setUpdatedBy(getCellValueAsString(row.getCell(6)));
                employees.add(employee);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage());
        }
        return employees;
    }

    private static LocalDateTime getCellValueAsDateTime(Cell cell) {
        if (cell == null) return LocalDateTime.now();
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue();
        } else {
            return LocalDateTime.parse(cell.getStringCellValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }


    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        }
        return "";
    }
}