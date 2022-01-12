package com.testcasemng.tool.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileUtils {

    public static String getFileName(String input) {
        return Paths.get(input).getFileName().toString();
    }

    public static String getFileNameWithoutExtension(String input) {
        String fileName = getFileName(input);
        return fileName.substring(0, fileName.indexOf('.'));
    }

    public static void createFolderIfNotExists (String path) throws IOException {
        File folder = new File(path);
        if(!folder.exists()) {
            Files.createDirectories(Paths.get(path));
        }
    }
}
