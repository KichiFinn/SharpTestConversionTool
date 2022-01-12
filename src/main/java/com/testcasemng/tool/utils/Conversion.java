package com.testcasemng.tool.utils;

import com.testcasemng.tool.excel.ExcelTestCaseTemplate;
import com.testcasemng.tool.markdown.MarkdownTestCaseTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Conversion {

    public static void convertSharpAutomationExcelToMarkdown(String input, String output) throws IOException {
        List<SharpTestCaseTemplate> sharpTestCases = ExcelTestCaseTemplate.parseSharpAutomationExcelFile(new File(input));
        for (SharpTestCaseTemplate sharpTestCase : sharpTestCases) {
            TestCaseTemplate template = convertSharpTestCaseTemplate(sharpTestCase, 1);
            MarkdownTestCaseTemplate.writeTemplateToFile(template.getTestcaseName(),output + File.separator + sharpTestCase.getSheetName(), template);
        }
    }

    public static TestCaseTemplate convertSharpTestCaseTemplate(SharpTestCaseTemplate sharpTemplate, int testNo) {
        TestCaseTemplate template = new TestCaseTemplate();
        String description = "";
        if (testNo == 1) {
            template.setTestDate(sharpTemplate.getFirstTestDate());
            template.setTestResults(convertSharpResult(sharpTemplate.getFirstTestResults()));
        } else {
            template.setTestDate(sharpTemplate.getSecondTestDate());
            template.setTestResults(convertSharpResult(sharpTemplate.getSecondTestResults()));
        }

        template.setPreCondition("N/A");
        template.setPostCondition("N/A");
        template.setTestcaseID(sharpTemplate.getTestcaseID());
        template.setTestcaseName(FileUtils.getFileNameWithoutExtension(sharpTemplate.getTestScriptLink()));
        template.setTestScriptLink(Constants.SHARP_REPO_PREFIX +  convertSharpDir(sharpTemplate.getSheetName()) + "/"+ sharpTemplate.getTestScriptLink());

        // Build test description
        description += "Device: " + Constants.SHARP_DEVICE_NAME;
        description += "\nModel: " + Constants.SHARP_DEVICE_MODEL;
        description += "\nROM: " + sharpTemplate.getRom();
        template.setTestcaseDesc(description);

        // Build test steps
        List<TestStep> steps = new ArrayList<>();
        TestStep step = new TestStep();
        step.setData("N/A");
        step.setDetails("N/A");
        step.setExpectedResults("Test passes without error.");
        step.setActualResults(buildResult(template.getTestResults(), sharpTemplate.getNote(), sharpTemplate.getIssueLink()));
        step.setFinalResults(template.getTestResults());

        steps.add(step);
        template.setTestSteps(steps);
        return template;
    }

    public static String convertSharpResult(String sharpResult) {
        switch (sharpResult) {
            case Constants.SHARP_PASS:
                return Constants.TEST_RESULT_PASS;
            case Constants.SHARP_FAIL:
                return Constants.TEST_RESULT_FAIL;
            case Constants.SHARP_NOT_EXECUTED:
                return Constants.TEST_RESULT_NOT_EXECUTED;
            default:
                return Constants.TEST_RESULT_SUSPENDED;
        }
    }

    public static String buildResult(String result, String note, String issueLink) {
        String ret= "";
        switch (result) {
            case Constants.TEST_RESULT_PASS:
                ret += "As expected";
                break;
            case Constants.TEST_RESULT_FAIL:
            case Constants.TEST_RESULT_NOT_EXECUTED:
                ret += "";
                break;
            case Constants.TEST_RESULT_SUSPENDED:
                ret += "Not support on this device";
                break;
        }
        if (!note.equals("")) {
            ret += "\n" +note;
        }
        if (!issueLink.equals("")) {
            ret += "\n" +issueLink;
        }

        return ret.trim();
    }

    public static String convertSharpDir(String sharpSheetName) {
        switch (sharpSheetName) {
            case Constants.SHARP_GAME_SETTING:
                return "AQUOSUseful/" + sharpSheetName + "/TestScripts";
            case Constants.SHARP_AUTO_ZOOM:
                return "SHVideoPlayer/" + sharpSheetName;
            case Constants.SHARP_REGRESSION:
                return "Devices/TestScript";
            default:
                return sharpSheetName + "/TestScripts";
        }
    }
}
