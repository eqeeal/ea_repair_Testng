package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataProvider{
    public static Object[][] getData(String filePath, String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                Map<String, String> rowData = new LinkedHashMap<>();

                for (int j = 0; j < colCount; j++) {
                    String header = headerRow.getCell(j).getStringCellValue();
                    Cell currentCell = currentRow.getCell(j);

                    String value = "";
                    if (currentCell != null) {
                        switch (currentCell.getCellType()) {
                            case STRING:
                                value = currentCell.getStringCellValue();
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(currentCell)) {
                                    value = currentCell.getDateCellValue().toString();
                                } else {
                                    value = String.valueOf(currentCell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                value = String.valueOf(currentCell.getBooleanCellValue());
                                break;
                            default:
                                value = "";
                        }
                    }
                    rowData.put(header, value);
                }
                testData.add(rowData);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + filePath, e);
        }

        // 转换为TestNG需要的二维数组格式
        Object[][] dataArray = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            dataArray[i][0] = testData.get(i);
        }
        return dataArray;
    }
}
