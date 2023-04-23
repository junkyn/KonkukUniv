package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class UserInfoUtil extends CsvUtil {
    private static volatile UserInfoUtil instance = null;
    private HashMap<String, UserInfo> userInfoHashMap;


    protected UserInfoUtil(String fileName) {
        super(fileName);
    }

    public static UserInfoUtil getInstance(String fileName) {
        if (Objects.isNull(instance)) {
            synchronized (UserInfoUtil.class) {
                if (Objects.isNull(instance)) {
                    instance = new UserInfoUtil(fileName);
                }
            }
        }
        return instance;
    }

    public static UserInfoUtil getInstance() {
        return instance;
    }

    public void appendData(UserInfo userInfo) {
        super.appendData(userInfo.toCsv());
        userInfoHashMap.put(userInfo.getId(), userInfo);
    }

    public boolean isIdPresent(String id) {
        return userInfoHashMap.containsKey(id);
    }

    public boolean isUserInfoValid(String id, String password) {
        return userInfoHashMap.get(id).isMatchingPassword(password);
    }

    public UserInfo getUserInfoById(String id) {
        return userInfoHashMap.get(id);
    }

    @Override
    protected void parseDataFromCSV(File file) {
        userInfoHashMap = new HashMap<>();
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
}
