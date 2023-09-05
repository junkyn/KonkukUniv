package kucse.introductoryproject.b01.csvhandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public abstract class CsvHandler {
    protected String fileName;

    protected File originalFile;

    public CsvHandler(String fileName) {
        this.fileName = fileName;
        try {
            originalFile = new File(fileName + ".csv");
            if (!originalFile.exists()) originalFile.createNewFile();

            parseDataFromCSV(originalFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void parseDataFromCSV(File file);

    protected synchronized void writeData(String data) {
        try {
            Files.move(Path.of(fileName + ".csv"), Path.of(fileName + ".csv.tmp"));

            File newFile = new File(fileName + ".csv");
            if (!newFile.exists()) newFile.createNewFile();

            try (FileOutputStream fos = new FileOutputStream(newFile);
                 OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                 BufferedWriter writer = new BufferedWriter(osw)) {
                writer.write(data);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Files.delete(Path.of(fileName + ".csv.tmp"));
            originalFile = newFile;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
