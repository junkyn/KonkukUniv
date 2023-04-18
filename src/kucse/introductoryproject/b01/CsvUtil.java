package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvUtil {
    private static FileWriter fileWriter;

    public static void init(String fileName) {
        File contactFile;
        try {
            contactFile = new File(fileName + ".csv");

            if (!contactFile.exists()) contactFile.createNewFile();

            fileWriter = new FileWriter(contactFile, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeContact(Contact contact) {
        try {
            fileWriter.write(contact.toCsv());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeWriterStream() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
