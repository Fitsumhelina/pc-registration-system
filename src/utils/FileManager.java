package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static final String DIRECTORY = "data/";

    // Ensure the directory exists
    static {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the folder if it doesn't exist
        }
    }

    public static void saveData(String filename, String data) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readData(String fileName) {
        File file = new File(DIRECTORY + fileName);
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
