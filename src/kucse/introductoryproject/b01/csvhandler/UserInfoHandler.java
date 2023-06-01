package kucse.introductoryproject.b01.csvhandler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import kucse.introductoryproject.b01.dto.Group;
import kucse.introductoryproject.b01.dto.UserInfo;
import kucse.introductoryproject.b01.observer.Observable;
import kucse.introductoryproject.b01.observer.ObservableUserInfoHashMap;
import kucse.introductoryproject.b01.observer.Observer;

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
        try (Scanner fileScanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                String[] userInfoStr = str.split("\t");

                UserInfo userInfo = new UserInfo();

                try {
                    if (userInfoHashMap.isIdPresent(userInfoStr[0].trim())) {
                        throw new IllegalArgumentException("중복된 아이디입니다.");
                    }
                    if (!userInfo.validateId(userInfoStr[0].trim())) {
                        throw new IllegalArgumentException("아이디 형식이 올바르지 않습니다.");
                    }
                    if (!userInfo.validatePassword(userInfoStr[1].trim())) {
                        throw new IllegalArgumentException("비밀번호 형식이 올바르지 않습니다.");
                    }
                    if (!userInfo.validateName(userInfoStr[2].trim())) {
                        throw new IllegalArgumentException("이름 형식이 올바르지 않습니다.");
                    }
                    if (!userInfo.validatePhone(userInfoStr[3].trim())) {
                        throw new IllegalArgumentException("전화번호 형식이 올바르지 않습니다.");
                    }
                    if (!userInfo.validateAddress(
                        userInfoStr.length < 5 ? "" : userInfoStr[4].trim())) {
                        throw new IllegalArgumentException("주소 형식이 올바르지 않습니다.");
                    }
                    if (!userInfo.validateBirthday(
                        userInfoStr.length < 6 ? "" : userInfoStr[5].trim())) {
                        throw new IllegalArgumentException("생일 형식이 올바르지 않습니다.");
                    }

                    ArrayList<Group> groupList = new ArrayList<>();
                    if (userInfoStr.length == 7) {
                        List<String> idList = new ArrayList<>(
                            Set.of(userInfoStr[6].trim().split(" ")));
                        if (idList.stream().anyMatch(it -> !Group.isIdValid(it)
                            || !GroupHandler.getInstance().groupHashMap.isGroupPresent(it))) {
                            throw new IllegalArgumentException("그룹 ID 형식이 올바르지 않거나, 존재하지 않습니다.");
                        }
                        groupList.addAll(idList.stream()
                            .map(it -> GroupHandler.getInstance().groupHashMap.getGroupById(it))
                            .toList());
                    }
                    userInfo.setGroupList(groupList);

                } catch (IllegalArgumentException e) {
                    System.err.println(file.getName() + " 파일 무결성 에러:\n" + str);
                    System.err.println(e.getMessage());
                    System.exit(0);
                }

                userInfoHashMap.append(userInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        userInfoHashMap.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        super.writeData(userInfoHashMap.toString());
    }
}
