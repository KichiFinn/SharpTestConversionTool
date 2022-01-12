package com.testcasemng.tool.excel;

import com.testcasemng.tool.utils.*;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelTestCaseTemplate {

    public static List<SharpTestCaseTemplate> parseSharpAutomationExcelFile(File file) throws IOException {
        List<SharpTestCaseTemplate> templates = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file);
        for (Sheet sheet : workbook) {
            if (!sheet.getSheetName().equals("Total")) {
                parseSharpAutomationExcelSheet(sheet, templates);
            }
        }
        workbook.close();
        return templates;
    }

    public static void parseSharpAutomationExcelSheet(Sheet sheet, List<SharpTestCaseTemplate> templates) {
        String sheetName = sheet.getSheetName();
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String id = getCellStringValue(row, 1);

            if (id.contains("Test")) {
                String file = getCellStringValue(row, 2);
                Date firstTestDate = getCellDateValue(row, 3);
                String firstTestResults = getCellStringValue(row, 4);
                Date secondTestDate = getCellDateValue(row, 5);
                String secondTestResults = getCellStringValue(row, 6);
                String note = getCellStringValue(row, 7);
                String rom = getCellStringValue(row, 8);
                String issueLink = getCellStringValue(row, 9);

                SharpTestCaseTemplate template = new SharpTestCaseTemplate();
                template.setTestcaseID(id);
                template.setTestScriptLink(file);
                template.setFirstTestDate(firstTestDate);
                template.setFirstTestResults(firstTestResults);
                template.setSecondTestDate(secondTestDate);
                template.setSecondTestResults(secondTestResults);
                template.setRom(rom);
                template.setNote(note);
                template.setIssueLink(issueLink);
                template.setSheetName(sheetName);
                templates.add(template);
            }

        }
    }

    public static String getCellStringValue(Row row, int col) {
        if (row.getCell(col) != null) {
            return row.getCell(col).getStringCellValue();
        } else {
            return "";
        }
    }

    public static Date getCellDateValue(Row row, int col) {
        if (row.getCell(col) != null) {
            return row.getCell(col).getDateCellValue();
        } else {
            return new Date();
        }
    }
}
