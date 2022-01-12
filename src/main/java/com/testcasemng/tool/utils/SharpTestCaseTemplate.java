package com.testcasemng.tool.utils;

import java.util.Date;

public class SharpTestCaseTemplate {
    private String testcaseID;
    private String testScriptLink;
    private Date firstTestDate;
    private String firstTestResults;
    private Date secondTestDate;
    private String secondTestResults;
    private String rom;
    private String note;
    private String issueLink;
    private String sheetName;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getTestcaseID() {
        return testcaseID;
    }

    public void setTestcaseID(String testcaseID) {
        this.testcaseID = testcaseID;
    }

    public String getTestScriptLink() {
        return testScriptLink;
    }

    public void setTestScriptLink(String testScriptLink) {
        this.testScriptLink = testScriptLink;
    }

    public Date getFirstTestDate() {
        return firstTestDate;
    }

    public void setFirstTestDate(Date firstTestDate) {
        this.firstTestDate = firstTestDate;
    }

    public String getFirstTestResults() {
        return firstTestResults;
    }

    public void setFirstTestResults(String firstTestResults) {
        this.firstTestResults = firstTestResults;
    }

    public Date getSecondTestDate() {
        return secondTestDate;
    }

    public void setSecondTestDate(Date secondTestDate) {
        this.secondTestDate = secondTestDate;
    }

    public String getSecondTestResults() {
        return secondTestResults;
    }

    public void setSecondTestResults(String secondTestResults) {
        this.secondTestResults = secondTestResults;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIssueLink() {
        return issueLink;
    }

    public void setIssueLink(String issueLink) {
        this.issueLink = issueLink;
    }

    public SharpTestCaseTemplate() {
        this.testcaseID = "";
        this.testScriptLink = "";
        this.note = "";
        this.issueLink = "";
        this.rom = "";
        this.secondTestResults = "";
        this.firstTestResults = "";
        this.firstTestDate = null;
        this.secondTestDate = null;
    }


}
