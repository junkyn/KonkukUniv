package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class CsvUtil {
    protected FileWriter fileWriter;
    protected String fileName;

    protected CsvUtil(String fileName) {
        this.fileName = fileName;
        File csvFile;
        try {
            csvFile = new File(fileName + ".csv");

            if (!csvFile.exists()) csvFile.createNewFile();

            fileWriter = new FileWriter(csvFile, true);

            parseDataFromCSV(csvFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void parseDataFromCSV(File file);

    public void appendData(String csvString) {
        try {
            fileWriter.write(csvString);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWriterStream() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
