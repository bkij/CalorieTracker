package agh.edu.util;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static boolean fileExsts(String fileDir, String fileName) {
        Path configFilePath = FileSystems.getDefault().getPath(fileDir, fileName);
        return Files.isReadable(configFilePath);
    }
}
