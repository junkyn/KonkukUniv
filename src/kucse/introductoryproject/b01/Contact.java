package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Contact {
    private String name;
    private String phone;
    private String address;
    private String birthday;
    private String memo;

    public Contact(String name, String phone, String address, String birthday, String memo) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.memo = memo;
    }

    public static HashSet<Contact> parseContactsFromCSV(File file) {
        HashSet<Contact> contactList = new HashSet<>();

        try (Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                String[] contactInfo = str.split("\t");

                contactList.add(new Contact(contactInfo[0].trim(), contactInfo[1].trim(), contactInfo[2].trim(), contactInfo[3].trim(), contactInfo[4].trim()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return contactList;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
