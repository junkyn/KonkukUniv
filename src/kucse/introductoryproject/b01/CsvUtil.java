package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public abstract class CsvUtil {
    protected String fileName;

    protected File originalFile;

    public CsvUtil(String fileName) {
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

            FileWriter fileWriter1 = new FileWriter(newFile);
            fileWriter1.write(data);
            fileWriter1.flush();
            fileWriter1.close();

            Files.delete(Path.of(fileName + ".csv.tmp"));
            originalFile = newFile;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
