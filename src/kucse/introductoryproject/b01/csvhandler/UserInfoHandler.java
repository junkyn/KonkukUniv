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
                String[] userInfoStr = str.split("\t");

                UserInfo userInfo = new UserInfo();

                try {
                    if (!userInfo.validateId(userInfoStr[0].trim()))
                        throw new IllegalArgumentException("아이디 형식이 올바르지 않습니다.");
                    if (!userInfo.validatePassword(userInfoStr[1].trim()))
                        throw new IllegalArgumentException("비밀번호 형식이 올바르지 않습니다.");
                    if (!userInfo.validateName(userInfoStr[2].trim()))
                        throw new IllegalArgumentException("이름 형식이 올바르지 않습니다.");
                    if (!userInfo.validatePhone(userInfoStr[3].trim()))
                        throw new IllegalArgumentException("전화번호 형식이 올바르지 않습니다.");
                    if (!userInfo.validateAddress(userInfoStr.length < 5 ? "" : userInfoStr[4].trim()))
                        throw new IllegalArgumentException("주소 형식이 올바르지 않습니다.");
                    if (!userInfo.validateBirthday(userInfoStr.length < 6 ? "" : userInfoStr[5].trim()))
                        throw new IllegalArgumentException("생일 형식이 올바르지 않습니다.");

                } catch (IllegalArgumentException e) {
                    System.err.println(file.getName() + " 파일 무결성 에러: " + str);
                    System.err.println(e.getMessage());
                    System.exit(0);
                }

                userInfoHashMap.append(userInfo);
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
