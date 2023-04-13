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

                String name = contactInfo[0].trim();
                String phone = contactInfo[1].trim();
                String address = contactInfo.length < 3 ? "" : contactInfo[2].trim();
                String birthday = contactInfo.length < 4 ? "" : contactInfo[3].trim();
                String memo = contactInfo.length < 5 ? "" : contactInfo[4].trim();

                contactList.add(new Contact(name, phone, address, birthday, memo));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return contactList;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getName(){
        return this.name;
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

    public String toCsv() {
        return name + "\t" + phone + "\t" + address + "\t" + birthday + "\t" + memo + "\n";
    }
}
