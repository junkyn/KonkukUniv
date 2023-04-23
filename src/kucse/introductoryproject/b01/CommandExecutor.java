package kucse.introductoryproject.b01;

import java.util.Scanner;

public class CommandExecutor {
    private Scanner scanner;
    private AddressBook addressBook;

    public CommandExecutor(Scanner scanner, AddressBook addressBook) {
        this.scanner = scanner;
        this.addressBook = addressBook;
    }

    public UserInfo executeCommands(UserInfo signedInUser) {
        displayHelpList();
        boolean loop = true;
        while (loop) {
            System.out.print("> ");
            String prompt = scanner.nextLine();

            switch (prompt.split(" ")[0]) {
                case "help" -> displayHelpList();
                case "logout" -> {
                    signedInUser = null;
                    loop = false;
                }
                case "exit" -> loop = false;
                case "view" -> handleViewCommand(prompt.split(" "));
                case "search" -> {
                    // Handle search command
                }
                case "add" -> addressBook.addContact();
                case "delete" -> addressBook.deleteContact();
                case "edit" -> addressBook.editContact();
                case "myprofile" -> addressBook.myProfile();
                default -> {
                    System.out.println("잘못된 입력입니다");
                    displayHelpList();
                }
            }
        }
        return signedInUser;
    }

    private void handleViewCommand(String[] input) {
        if (input.length == 1) {
            addressBook.viewAddressBook();
        } else {
            try {
                int page = Integer.parseInt(input[1]);
                addressBook.viewAddressBook(page);
            } catch (NumberFormatException e) {
                addressBook.viewAddressBook(input[1]);
            }
        }
    }

    private void displayHelpList() {
        System.out.println();
        System.out.println("-- view <value> : 주소록 열람");
        System.out.println("\t<value>가 페이지 수라면 그 페이지에 해당하는 주소록을 출력합니다");
        System.out.println("\t<value>가 이름이라면 그 이름에 해당하는 연락처를 열람합니다");
        System.out.println("-- search <value1><value2> : 주소록 검색");
        System.out.println("\t<value1>의 내용을 포함하는 연락처들 중");
        System.out.println("\t<value2>에 해당하는 페이지의 연락처들을 출력합니다");
        System.out.println("-- add 주소록 추가");
        System.out.println("-- edit : 열람하고 있는 연락처 수정");
        System.out.println("-- delete : 열람하고 있는 연락처 삭제");
        System.out.println("-- myprofile : 내 정보 수정");
        System.out.println("-- logout : 로그아웃");
        System.out.println("-- help : 도움말");
        System.out.println("-- exit : 프로그램 종료");
    }
}