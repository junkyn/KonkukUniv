package kucse.introductoryproject.b01;

import java.util.Scanner;
import kucse.introductoryproject.b01.dto.Contact;
import kucse.introductoryproject.b01.dto.Group;
import kucse.introductoryproject.b01.dto.UserInfo;
import kucse.introductoryproject.b01.utils.StringUtil;

public class GroupPrompt {

    private Scanner scanner;
    private AddressBook addressBook;
    private Group groupInfo;
    private UserInfo userInfo;

    public GroupPrompt(Scanner scanner, Group groupInfo, AddressBook addressBook,
        UserInfo userInfo) {
        this.scanner = scanner;
        this.groupInfo = groupInfo;
        this.addressBook = addressBook;
        this.userInfo = userInfo;
    }

    public void executeCommands() {
        displayHelpList();
        boolean loop = true;
        while (loop) {
            System.out.print(groupInfo.getName() + "#" + groupInfo.getTag() + " > ");
            String prompt = scanner.nextLine().trim();

            switch (prompt.split(" ")[0]) {
                case "search" -> handleSearchCommand(prompt.split(" "));
                case "view" -> handleViewCommand(prompt);
                case "add" -> addressBook.addContact();
                case "invite" -> System.out.println("초대코드: " + groupInfo.getCode());
                case "regen" -> {
                    groupInfo.regenerateCode();
                    System.out.println("초대코드: " + groupInfo.getCode());
                }
                case "quit" -> {
                    userInfo.leaveGroup(groupInfo);
                    System.out.println("탈퇴했습니다.");
                    loop = false;
                }
                case "exit" -> loop = false;
                case "help" -> displayHelpList();
                case "edit" -> System.out.println("수정할 연락처를 열람해주세요");
                case "delete" -> System.out.println("삭제할 연락처를 열람해주세요");
                default -> {
                    System.out.println("잘못된 입력입니다");
                    displayHelpList();
                }
            }
        }
    }

    private void handleSearchCommand(String[] input) {
        if (input.length < 2) {
            System.out.println("입력 오류");
        } else if (input.length < 3) {
            addressBook.searchContact(input[1]);
        } else if (!StringUtil.isNumber(input[2])) {
            System.out.println("입력 오류");
        } else {
            addressBook.searchContact(input[1], Integer.parseInt(input[2]));
        }
    }

    private void handleViewCommand(String input) {
        if (input.equals("view")) {
            addressBook.viewAddressBook();
        } else {
            String query = input.substring(5);

            if (StringUtil.isNumber(query)) {
                addressBook.viewAddressBook(Integer.parseInt(query));
            } else {
                Contact contact = addressBook.viewAddressBook(query);
                var loop = true;
                while (loop && contact != null) {
                    System.out.print(contact.getName() + " > ");

                    String command = scanner.nextLine().trim();
                    switch (command) {
                        case "edit" -> addressBook.editContact(contact);
                        case "delete" -> {
                            addressBook.deleteContact(contact);
                            loop = false;
                        }
                        case "exit" -> loop = false;
                        default -> System.out.println("잘못된 입력입니다.");
                    }
                }
            }

        }
    }

    private void displayHelpList() {
        System.out.println("""
            ========================= 도 움 말 =========================
            - add: 연락처 추가
            - search <query>: <query> 검색
            - view <name>: 이름이 <name>인 연락처 열람
                - edit: 연락처 수정
                - delete: 연락처 삭제
                - exit: 그룹 프롬프트로 나가기
            - add: 연락처 추가
            - invite: 초대 코드 보기
            - regen: 초대 코드 재생성
            - quit: 그룹 나가기
            - exit: 메인 프롬프트로 나가기
            - help: 도움말 보기
            ===========================================================
            """);

    }
}