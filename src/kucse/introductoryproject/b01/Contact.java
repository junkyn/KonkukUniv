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

    public Contact() {

    }
    public Contact(String name, String phone, String address, String birthday, String memo) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.memo = memo;
    }

    public boolean setName(String name) {
        if (name.isBlank())
            System.out.println("이름은 필수 입력입니다");
        else if (name.chars().allMatch(Character::isDigit))
            System.out.println("정수로만 이루어진 이름은 사용할 수 없습니다");
        else if (name.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.name = name;
            return true;
        }

        return false;
    }

    public boolean setPhone(String phone) {
        this.phone = phone;

        return true;
    }

    public boolean setAddress(String address) {
        if(address.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.address = address;
            return true;
        }

        return false;
    }

    public boolean setBirthday(String birthday) {
        if(address.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.birthday = birthday;
            return true;
        }

        return false;
    }

    public boolean setMemo(String memo) {
        this.memo = memo;

        return true;
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
