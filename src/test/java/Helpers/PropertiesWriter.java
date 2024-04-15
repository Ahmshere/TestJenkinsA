package Helpers;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesWriter {

    public static void main(String[] args) {
        writeProperties("myKey", "myValue", true);
    }

    private static final String PROPERTIES_FILE_PATH = "src/test/resources/data.properties";

    public static void writeProperties(String key, String value, boolean cleanFIle) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            if (!Files.exists(Paths.get(PROPERTIES_FILE_PATH))) {
                Files.createFile(Paths.get(PROPERTIES_FILE_PATH));
            }
            fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH);

            properties.load(fileInputStream);
            if (cleanFIle) {
                properties.clear();
            }
            properties.setProperty(key, value);
            fileOutputStream = new FileOutputStream(PROPERTIES_FILE_PATH);
            properties.store(fileOutputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}