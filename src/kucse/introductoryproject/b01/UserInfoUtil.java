package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UserInfoUtil {
    private static FileWriter fileWriter;
    private static HashMap<String, UserInfo> userInfoHashMap = new HashMap<>();


    public static void init(String fileName) {
        File userInfoFile;
        try {
            userInfoFile = new File(fileName + ".csv");

            if (!userInfoFile.exists()) userInfoFile.createNewFile();

            fileWriter = new FileWriter(userInfoFile, true);

            parseUserDataFromCSV(userInfoFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseUserDataFromCSV(File file) {
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
    }

    public static boolean isIdPresent(String id) {
        return userInfoHashMap.containsKey(id);
    }

    public static boolean isUserInfoValid(String id, String password) {
        return userInfoHashMap.get(id).isMatchingPassword(password);
    }

    public static UserInfo getUserInfoById(String id) {
        return userInfoHashMap.get(id);
    }

    public static void appendUserData(UserInfo userInfo) {
        userInfoHashMap.put(userInfo.getId(), userInfo);

        try {
            fileWriter.write(userInfo.toCsv());
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
