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

                String id = userInfo[0].trim();
                String password = userInfo[1].trim();
                String name = userInfo[2].trim();
                String phone = userInfo[3].trim();
                String address = userInfo.length < 5 ? "" : userInfo[4].trim();
                String birthday = userInfo.length < 6 ? "" : userInfo[5].trim();

                userInfoHashMap.put(id, new UserInfo(id, password, name, phone, address, birthday));
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
