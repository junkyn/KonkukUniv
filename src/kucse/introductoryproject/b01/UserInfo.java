package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Predicate;

public class UserInfo {
    private String id;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String birthday;

    public UserInfo() {

    }

    public UserInfo(String id, String password, String name, String phone, String address, String birthday) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public boolean setId(String id) {
        if (id.length() < 5)
            System.out.println("ID의 길이는 5 이상이어야 합니다.");
        else if (id.matches("^[a-zA-Z0-9]*$"))
            System.out.println("ID는 알파벳 또는 숫자로만 이루어져야 합니다.");
        else {
            this.id = id;
            return true;
        }

        return false;
    }

    public boolean setPassword(String password) {
        if (password.length() < 8)
            System.out.println("암호의 길이는 8 이상이어야 합니다.");
        else if (password.matches(".*\\s.*"))
            System.out.println("암호는 공백 또는 탭을 포함할 수 없습니다.");
        else {
            this.password = password;
            return true;
        }

        return false;
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
        if (phone.isBlank())
            System.out.println("전화번호는 필수 입력입니다");
        else if (phone.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.phone = phone;
            return true;
        }

        return false;
    }

    public boolean setAddress(String address) {
        if (address.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.address = address;
            return true;
        }

        return false;
    }

    public boolean setBirthday(String birthday) {
        if (address.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.birthday = birthday;
            return true;
        }

        return false;
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
