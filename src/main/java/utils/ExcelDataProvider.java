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
    /**
     * 从Excel文件中读取测试数据并转换为TestNG需要的二维数组格式
     * @param filePath Excel文件路径
     * @param sheetName 工作表名称
     * @return 包含测试数据的二维数组，每个元素是一个Map，代表一行数据
     */
    public static Object[][] getData(String filePath, String sheetName) {
        // 使用List<Map>结构存储测试数据，便于处理和扩展
        List<Map<String, String>> testData = new ArrayList<>();

        // 使用try-with-resources语句确保资源自动关闭
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // 获取指定工作表
            Sheet sheet = workbook.getSheet(sheetName);
            // 获取表头行
            Row headerRow = sheet.getRow(0);
            // 获取列数
            int colCount = headerRow.getLastCellNum();

            // 遍历数据行（从第二行开始，因为第一行是表头）
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                // 使用LinkedHashMap保持列的顺序
                Map<String, String> rowData = new LinkedHashMap<>();

                // 遍历每一列
                for (int j = 0; j < colCount; j++) {
                    // 获取表头
                    String header = headerRow.getCell(j).getStringCellValue();
                    // 获取当前单元格
                    Cell currentCell = currentRow.getCell(j);

                    // 默认值为空字符串
                    String value = "";
                    if (currentCell != null) {
                        // 根据单元格类型获取不同的值
                        switch (currentCell.getCellType()) {
                            case STRING:
                                value = currentCell.getStringCellValue();
                                break;
                            case NUMERIC:
                                // 判断是否是日期格式
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
                    // 将数据存入Map
                    rowData.put(header, value);
                }
                // 将当前行的数据存入List
                testData.add(rowData);
            }
        } catch (IOException e) {
            // 抛出运行时异常，包含详细的错误信息
            throw new RuntimeException("Error reading Excel file: " + filePath, e);
        }

        // 转换为TestNG需要的二维数组格式
        Object[][] dataArray = new Object[testData.size()][1];
        // 将List<Map>转换为二维数组
        for (int i = 0; i < testData.size(); i++) {
            dataArray[i][0] = testData.get(i);
        }
        return dataArray;
    }
}
