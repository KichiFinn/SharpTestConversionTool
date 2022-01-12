package com.testcasemng.tool.markdown;

import com.testcasemng.tool.utils.Constants;

public class MarkdownLib {

    public static String createHeader(String value, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++)
            sb.append(Constants.MARKDOWN_HEADER);
        sb.append(" ")
                .append(value)
                .append("\r\n");
        return sb.toString();
    }

    public static String createUnorderedListItem(String value, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++)
            sb.append(Constants.MARKDOWN_LEVEL_SEPARATOR);
        sb.append(Constants.MARKDOWN_LIST_SIGNAL)
                .append(" ")
                .append(value)
                .append("\r\n");
        return sb.toString();
    }

    public static String createUnorderedList(String value, int level) {
        StringBuilder sb = new StringBuilder();
        for (String item : value.split("\n")) {
            sb.append(createUnorderedListItem(item, level));
        }
        return sb.toString();
    }

    public static String createHeaderAndList(String header, int headerLevel, String list, int listLevel) {
        StringBuilder sb = new StringBuilder().
                append(createHeader(header, headerLevel))
                .append(createUnorderedList(list, listLevel));
        return sb.toString();
    }

    public static String createOrderedList(int number, String value, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++)
            sb.append(Constants.MARKDOWN_LEVEL_SEPARATOR);
        sb.append(number)
                .append(". ")
                .append(value)
                .append("\r\n");
        return sb.toString();
    }


}
