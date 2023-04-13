package kucse.introductoryproject.b01;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    static HashMap<String, UserInfo> userInfoHashMap = UserInfo.parseUserDataFromCSV(new File("dummyUserData.csv"));
    public static void main(String[] args) {
        for (String key : userInfoHashMap.keySet()) {
            System.out.println(key + " : " + userInfoHashMap.get(key));
        }

        HashSet<Contact> contactArrayList = Contact.parseContactsFromCSV(new File("dummyContacts.csv"));

        for (Contact it : contactArrayList) {
            System.out.println(it);
        }
    }

    public static UserInfo login(String id, String pw) {
        for (String key : userInfoHashMap.keySet()) {
            if (key.equals(id)) {
                if (userInfoHashMap.get(key).getPassword().equals(pw)) {
                    String name = userInfoHashMap.get(key).getName();
                    System.out.println(name + "님 환영합니다\n");
                    return userInfoHashMap.get(key);
                } else {
                    System.out.println("비밀번호가 틀립니다\n");
                    return null;
                }
            }
        }
        System.out.println("존재하지 않는 아이디입니다\n");
        return null;
    }
}
