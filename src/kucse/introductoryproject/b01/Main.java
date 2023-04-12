package kucse.introductoryproject.b01;

import java.io.File;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<Contact> contactArrayList = Contact.parseContactsFromCSV(new File("dummyContacts.csv"));

        for (Contact it : contactArrayList) {
            System.out.println(it);
        }
    }
}
