package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String DIRECTORY = "data/";

    // Ensure the directory exists
    static {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the folder if it doesn't exist
        }
    }

    // Write data to a file (Append mode)
    public static void writeToFile(String fileName, String data) {
        try (FileWriter writer = new FileWriter(DIRECTORY + fileName, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read data from a file
    public static List<String> readFromFile(String fileName) {
        File file = new File(DIRECTORY + fileName);
        List<String> lines = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return lines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    // Update the contents of a file
    public static boolean updateFile(String fileName, String oldData, String newData) {
        List<String> lines = readFromFile(fileName);
        boolean updated = false;

        try (FileWriter writer = new FileWriter(DIRECTORY + fileName, false)) {
            for (String line : lines) {
                if (line.equals(oldData)) {
                    writer.write(newData + "\n");
                    updated = true;
                } else {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updated;
    }

    // Delete specific data from a file
    public static boolean deleteFromFile(String fileName, String data) {
        List<String> lines = readFromFile(fileName);
        boolean removed = lines.remove(data);

        try (FileWriter writer = new FileWriter(DIRECTORY + fileName, false)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return removed;
    }
}
