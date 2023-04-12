package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserInfo {
    private String id;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String birthday;

    public UserInfo(String id, String password, String name, String phone, String address, String birthday) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }

    public static HashMap<String, UserInfo> parseUserDataFromCSV(File file) {
        HashMap<String, UserInfo> userInfoHashMap = new HashMap<>();

        try (Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                String[] userInfo = str.split("\t");

                userInfoHashMap.put(userInfo[0].trim(), new UserInfo(userInfo[0].trim(), userInfo[1].trim(), userInfo[2].trim(), userInfo[3].trim(), userInfo[4].trim(), userInfo[5].trim()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return userInfoHashMap;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
