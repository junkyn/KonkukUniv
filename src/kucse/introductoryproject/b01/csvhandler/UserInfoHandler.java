package kucse.introductoryproject.b01.csvhandler;

import kucse.introductoryproject.b01.dto.UserInfo;
import kucse.introductoryproject.b01.observer.Observable;
import kucse.introductoryproject.b01.observer.ObservableUserInfoHashMap;
import kucse.introductoryproject.b01.observer.Observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class UserInfoHandler extends CsvHandler implements Observer {
    private static volatile UserInfoHandler instance = null;
    public ObservableUserInfoHashMap userInfoHashMap;


    protected UserInfoHandler(String fileName) {
        super(fileName);
    }

    public static UserInfoHandler getInstance(String fileName) {
        if (Objects.isNull(instance)) {
            synchronized (UserInfoHandler.class) {
                if (Objects.isNull(instance)) {
                    instance = new UserInfoHandler(fileName);
                }
            }
        }
        return instance;
    }

    public static UserInfoHandler getInstance() {
        return instance;
    }

    @Override
    protected void parseDataFromCSV(File file) {
        userInfoHashMap = new ObservableUserInfoHashMap();
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

                userInfoHashMap.append(new UserInfo(id, password, name, phone, address, birthday));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        userInfoHashMap.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        super.writeData(userInfoHashMap.toString());
    }
}
