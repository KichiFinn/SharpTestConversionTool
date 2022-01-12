package com.testcasemng.tool.markdown;

import com.testcasemng.tool.utils.*;

import java.io.*;
import java.util.List;

public class MarkdownTestCaseTemplate {

    public static void writeTemplateToFile(String fileName, String outFolder, TestCaseTemplate template) throws IOException {
        FileUtils.createFolderIfNotExists(outFolder);
        String markdownFileName = outFolder + File.separator + fileName + ".MD";
        StringBuilder sb = new StringBuilder()
                //.append(MarkdownLib.createHeaderLink(template.getTestcaseID(), "", 1))TEST_CASE_SCRIPT_LOCATION
                .append(MarkdownLib.createHeaderAndList(Constants.TEST_CASE_ID, 2, template.getTestcaseID(), 0))
                .append(MarkdownLib.createHeaderAndList(Constants.TEST_CASE_SCRIPT_LOCATION, 2, template.getTestScriptLink(), 0))
                .append(MarkdownLib.createHeaderAndList(Constants.TEST_CASE_NAME, 2, template.getTestcaseName(), 0))
                .append(MarkdownLib.createHeaderAndList(Constants.TEST_CASE_DESCRIPTION, 2, template.getTestcaseDesc(), 0))
                .append(MarkdownLib.createHeaderAndList(Constants.TEST_CASE_PRE_CONDITION, 2, template.getPreCondition(), 0))
                .append(MarkdownLib.createHeaderAndList(Constants.TEST_CASE_POST_CONDITION, 2, template.getPostCondition(), 0))
                .append(MarkdownLib.createHeaderAndList(Constants.TEST_CASE_DATE_TESTED, 2, DateUtils.getDateFormat(template.getTestDate(), Constants.DATE_FORMAT), 0))
                .append(MarkdownLib.createHeaderAndList(Constants.TEST_CASE_RESULTS, 2, template.getTestResults(), 0))
                .append("---\r\n")
                .append(MarkdownLib.createHeader(Constants.TEST_CASE_STEPS, 2))
                .append(buildTestSteps(template.getTestSteps()))
                .append("---\r\n");

        BufferedWriter writer = new BufferedWriter(new FileWriter(markdownFileName));
        writer.write(sb.toString());
        writer.close();
    }

    public static String buildTestSteps(List<TestStep> steps) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < steps.size(); i++) {
            TestStep step = steps.get(i);
            sb.append(MarkdownLib.createOrderedList(step.getNo(), Constants.TEST_CASE_STEP + step.getNo(), 0))
                    .append(MarkdownLib.createUnorderedList(Constants.TEST_CASE_STEP_DETAILS, 1))
                    .append(MarkdownLib.createUnorderedList(step.getDetails(), 2))
                    .append(MarkdownLib.createUnorderedList(Constants.TEST_CASE_STEP_DATA, 1))
                    .append(MarkdownLib.createUnorderedList(step.getData(), 2))
                    .append(MarkdownLib.createUnorderedList(Constants.TEST_CASE_STEP_EXPECTED_RESULTS, 1))
                    .append(MarkdownLib.createUnorderedList(step.getExpectedResults(), 2))
                    .append(MarkdownLib.createUnorderedList(Constants.TEST_CASE_STEP_ACTUAL_RESULTS, 1))
                    .append(MarkdownLib.createUnorderedList(step.getActualResults(), 2))
                    .append(MarkdownLib.createUnorderedList(Constants.TEST_CASE_STEP_FINAL_RESULTS, 1))
                    .append(MarkdownLib.createUnorderedList(step.getFinalResults(), 2));
        }
        return sb.toString();
    }


}
