package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static kucse.introductoryproject.b01.StringUtil.getNumbersOnly;

public class ContactUtil {
    private static FileWriter fileWriter;
    private static HashSet<Contact> contactHashSet = new HashSet<>();

    public static void init(String fileName) {
        File contactFile;
        try {
            contactFile = new File(fileName + ".csv");

            if (!contactFile.exists()) contactFile.createNewFile();

            fileWriter = new FileWriter(contactFile, true);

            parseContactsFromCSV(contactFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseContactsFromCSV(File file) {
        try (Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                String[] contactInfo = str.split("\t");

                String name = contactInfo[0].trim();
                String phone = contactInfo[1].trim();
                String address = contactInfo.length < 3 ? "" : contactInfo[2].trim();
                String birthday = contactInfo.length < 4 ? "" : contactInfo[3].trim();
                String memo = contactInfo.length < 5 ? "" : contactInfo[4].trim();

                contactHashSet.add(new Contact(name, phone, address, birthday, memo));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Contact> getContactList() {
        return new ArrayList<>(contactHashSet);
    }

    public static Contact getContactByName(String name) {
        return contactHashSet.stream().filter(it -> it.getName().equals(name)).findFirst().orElse(null);
    }

    public static void removeContact(Contact contact) {
        contactHashSet.remove(contact);
    }

    public static boolean isPhoneDuplicated(String phone) {
        return contactHashSet.stream().anyMatch(it -> getNumbersOnly(it.getPhone()).equals(getNumbersOnly(phone)));
    }

    public static String renameDuplicatedName(String name) {
        AtomicInteger count = new AtomicInteger();
        if (contactHashSet.stream().anyMatch(it -> it.getName().equals(name)))
            count.incrementAndGet();

        while (count.get() > 0 && contactHashSet.stream().anyMatch(it -> it.getName().equals(name + "(" + count.get() + ")")))
            count.incrementAndGet();

        return count.get() == 0 ? name : name + "(" + count.get() + ")";
    }

    public static void appendContact(Contact contact) {
        contactHashSet.add(contact);

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
