package kucse.introductoryproject.b01;

import java.util.Scanner;
import kucse.introductoryproject.b01.csvhandler.ContactHandler;
import kucse.introductoryproject.b01.csvhandler.GroupHandler;
import kucse.introductoryproject.b01.csvhandler.UserInfoHandler;
import kucse.introductoryproject.b01.dto.UserInfo;
import kucse.introductoryproject.b01.observer.ObservableUserInfoHashMap;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static UserInfo signedInUser = null;
    public static AddressBook addressBook = null;
    public static GroupHandler groupHandler = null;
    public static UserInfoHandler userInfoHandler = null;

    public static void main(String[] args) {
        groupHandler = GroupHandler.getInstance("$groupData");
        userInfoHandler = new UserInfoHandler("$userData");

        do {
            showLoginManager(userInfoHandler.userInfoHashMap);
            executeCommand();
        } while (signedInUser == null);

        System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다");
    }

    public static void showLoginManager(ObservableUserInfoHashMap userInfoHashMap) {
        LoginManager loginManager = new LoginManager(userInfoHashMap);
        signedInUser = loginManager.show();
        if (signedInUser != null) {
            ContactHandler contactHandler = new ContactHandler(signedInUser.getId());
            addressBook = new AddressBook(contactHandler.contactHashSet);
        }
    }

    public static void executeCommand() {
        if (signedInUser == null) {
            return;
        }

        MainPrompt mainPrompt = new MainPrompt(scanner, addressBook);
        signedInUser = mainPrompt.executeCommands(signedInUser);
    }

}
