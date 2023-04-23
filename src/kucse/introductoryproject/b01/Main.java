package kucse.introductoryproject.b01;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static UserInfo signedInUser = null;
    public static AddressBook addressBook = null;
    public static UserInfoUtil userInfoUtil = null;

    public static void main(String[] args) {
        userInfoUtil = UserInfoUtil.getInstance("dummyUserData");

        do {
            showLoginManager();
            executeCommand();
        } while (signedInUser == null);

        System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다");
        ContactUtil.getInstance().closeWriterStream();
        userInfoUtil.closeWriterStream();
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

        CommandExecutor commandExecutor = new CommandExecutor(scanner, addressBook);
        signedInUser = commandExecutor.executeCommands(signedInUser);
    }

}
