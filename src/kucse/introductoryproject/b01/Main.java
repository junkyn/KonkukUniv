package kucse.introductoryproject.b01;

import kucse.introductoryproject.b01.csvhandler.UserInfoHandler;
import kucse.introductoryproject.b01.dto.UserInfo;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static UserInfo signedInUser = null;
    public static AddressBook addressBook = null;
    public static UserInfoHandler userInfoUtil = null;

    public static void main(String[] args) {
        userInfoUtil = UserInfoHandler.getInstance("userData");

        do {
            showLoginManager();
            executeCommand();
        } while (signedInUser == null);

        System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다");
    }

    public static void showLoginManager() {
        LoginManager loginManager = new LoginManager();
        signedInUser = loginManager.show();
        if (signedInUser != null) {
            addressBook = new AddressBook(signedInUser);
        }
    }

    public static void executeCommand() {
        if (signedInUser == null) return;

        AddressBookHandler addressBookHandler = new AddressBookHandler(scanner, addressBook);
        signedInUser = addressBookHandler.executeCommands(signedInUser);
    }

}
