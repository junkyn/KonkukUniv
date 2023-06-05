package kucse.introductoryproject.b01;

import java.util.Scanner;
import kucse.introductoryproject.b01.csvhandler.ContactHandler;
import kucse.introductoryproject.b01.csvhandler.GroupHandler;
import kucse.introductoryproject.b01.dto.Contact;
import kucse.introductoryproject.b01.dto.Group;
import kucse.introductoryproject.b01.dto.UserInfo;
import kucse.introductoryproject.b01.utils.StringUtil;

public class MainPrompt {

    private Scanner scanner;
    private AddressBook addressBook;

    public MainPrompt(Scanner scanner, AddressBook addressBook) {
        this.scanner = scanner;
        this.addressBook = addressBook;
    }

    public UserInfo executeCommands(UserInfo signedInUser) {
        displayHelpList();
        boolean loop = true;
        while (loop) {
            System.out.print("> ");
            String prompt = scanner.nextLine().trim();

            switch (prompt.split(" ")[0]) {
                case "help" -> displayHelpList();
                case "logout" -> {
                    signedInUser = null;
                    loop = false;
                }
                case "exit" -> loop = false;
                case "view" -> handleViewCommand(prompt);
                case "search" -> handleSearchCommand(prompt.split(" "));
                case "add" -> addressBook.addContact();
                case "myprofile" -> myProfile(signedInUser);
                case "group" -> handleGroupCommand(signedInUser);
                default -> {
                    System.out.println("잘못된 입력입니다");
                    displayHelpList();
                }
            }
        }
        return signedInUser;
    }

    public void myProfile(UserInfo signedInUser) {
        String order = "";
        while (!order.equals("exit")) {
            System.out.println(signedInUser);
            System.out.println("수정할 항목을 선택해주세요");
            System.out.println("(name : 이름, num : 전화번호, address : 주소, birth : 생년월일, exit : 나가기)");
            System.out.print(signedInUser.getName() + " > edit > ");
            order = scanner.nextLine().trim();

            System.out.print(order.matches("name|num|address|birth") ? "수정할 " : "");
            switch (order) {
                case "name" -> signedInUser.setName();
                case "num" -> signedInUser.setPhone();
                case "address" -> signedInUser.setAddress();
                case "birth" -> signedInUser.setBirthday();
                case "exit" -> {
                    continue;
                }
                default -> {
                    System.out.println("잘못된 입력입니다.");
                    continue;
                }
            }
            System.out.println("수정되었습니다.");
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
                while (loop) {
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

    private void handleGroupCommand(UserInfo signedInUser) {
        while (true) {
            displayGroupHelpList();
            System.out.println("========== 그룹 목록 ==========");
            signedInUser.getGroupList().stream().map(it -> it.getName() + "#" + it.getTag())
                .forEach(System.out::println);
            System.out.println("=============================");
            System.out.print("group > ");
            String[] commands = scanner.nextLine().trim().split(" ");

            if (commands[0].equals("exit")) {
                displayHelpList();
                break;
            }

            if (commands.length > 1) {
                switch (commands[0]) {
                    case "open" -> openGroup(signedInUser, commands[1]);
                    case "join" -> joinGroup(signedInUser, commands[1]);
                    case "create" -> createGroup(signedInUser, commands[1]);
                    default -> System.out.println("잘못된 입력입니다.");
                }
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void openGroup(UserInfo signedInUser, String nameAndTag) {
        if (!nameAndTag.contains("#")) {
            System.out.println("그룹이름#그룹태그 형식으로 입력해주세요");
            return;
        }
        String name = nameAndTag.substring(0, nameAndTag.indexOf('#'));
        int tag = Integer.parseInt("0" + nameAndTag.substring(nameAndTag.indexOf('#') + 1));

        if (!GroupHandler.getInstance().groupHashMap.isGroupPresent(name, tag)) {
            System.out.println("그런 그룹은 없습니다..");
            return;
        }

        Group group = GroupHandler.getInstance().groupHashMap.getGroupByNameAndTag(name, tag);

        ContactHandler contactHandler = new ContactHandler(group.getId());
        AddressBook groupAddressBook = new AddressBook(contactHandler.contactHashSet);

        GroupPrompt groupPrompt = new GroupPrompt(scanner, group, groupAddressBook, signedInUser);
        groupPrompt.executeCommands();
    }

    private void joinGroup(UserInfo signedInUser, String code) {
        if (!GroupHandler.getInstance().groupHashMap.isCodeDuplicated(code)) {
            System.out.println("유효하지 않은 초대코드입니다.");
            return;
        }

        Group group = GroupHandler.getInstance().groupHashMap.getGroupByCode(code);

        if (signedInUser.getGroupList().contains(group)) {
            System.out.println("이미 가입된 그룹입니다.");
            return;
        }

        signedInUser.joinGroup(group);

        System.out.println(group.getName() + "#" + group.getTag() + "에 가입되었습니다.");
    }

    private void createGroup(UserInfo signedInUser, String name) {
        Group group = new Group(name);
        GroupHandler.getInstance().groupHashMap.append(group);
        signedInUser.joinGroup(group);

        System.out.println(group.getName() + "#" + group.getTag() + "이 생성되었습니다.");
    }

    private void displayHelpList() {
        System.out.println("""
            ========================= 도 움 말 =========================
            - view <value> : 주소록 열람
            \t<value>가 페이지 수라면 그 페이지에 해당하는 주소록을 출력합니다
            \t<value>가 이름이라면 그 이름에 해당하는 연락처를 열람합니다
            - search <value1> <value2> : 주소록 검색
            \t<value1>의 내용을 포함하는 연락처들 중
            \t<value2>에 해당하는 페이지의 연락처들을 출력합니다
            - add 주소록 추가
            - edit : 열람하고 있는 연락처 수정
            - delete : 열람하고 있는 연락처 삭제
            - myprofile : 내 정보 수정
            - logout : 로그아웃
            - help : 도움말
            - exit : 프로그램 종료
            - group: 그룹 프롬프트로 이동
            ===========================================================
            """);
    }
    private void displayGroupHelpList(){
        System.out.println("""
            ========================= 도 움 말 =========================
            - open <value> : <value>에 해당하는 그룹 접속
            - join <value> : <value>의 초대 코드를 가진 그룹 가입
            - create <value> : <value>의 이름을 가진 그룹 생성
            - exit : 그룹 프롬프트 나가기           
            ===========================================================    
            """);
    }
}