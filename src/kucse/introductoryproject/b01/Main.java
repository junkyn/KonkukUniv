package kucse.introductoryproject.b01;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashMap<String, UserInfo> userInfoHashMap = UserInfo.parseUserDataFromCSV(new File("dummyUserData.csv"));

        for (String key : userInfoHashMap.keySet()) {
            System.out.println(key + " : " + userInfoHashMap.get(key));
        }

        HashSet<Contact> contactArrayList = Contact.parseContactsFromCSV(new File("dummyContacts.csv"));

        for (Contact it : contactArrayList) {
            System.out.println(it);
        }
    }
}
