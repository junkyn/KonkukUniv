package kucse.introductoryproject.b01;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String, UserInfo> userInfoHashMap = UserInfo.parseUserDataFromCSV(new File("dummyUserData.csv"));

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(StringUtil.getHangulOnly("안녕하세요, 이율원입니다. ^~^"));
        System.out.println(StringUtil.toConsonants("안녕하세요, 이율원입니다. ^~^"));

        for (String key : userInfoHashMap.keySet()) {
            System.out.println(key + " : " + userInfoHashMap.get(key));
        }
//
//        HashSet<Contact> contactArrayList = Contact.parseContactsFromCSV(new File("dummyContacts.csv"));
//
//        for (Contact it : contactArrayList) {
//            System.out.println(it);
//        }

        UserInfo signedInUserInfo = null;

        System.out.print("환영합니다. ");
        do {
            System.out.print("login이나 register를 통해 로그인이나 회원가입을 해주세요\n> ");
            String prompt = scanner.nextLine();

            if (prompt.equals("register"))
                register();
            else if (prompt.startsWith("login "))
                signedInUserInfo = login(prompt.split(" ")[1], prompt.split(" ")[2]);
        } while (signedInUserInfo == null);
        AddressBook addressBook = new AddressBook(signedInUserInfo);
        for (String key : userInfoHashMap.keySet()) {
            System.out.println(key + " : " + userInfoHashMap.get(key));
        }

//        AddressBook addressBook = new AddressBook("test");
//        addressBook.addContact();
//        addressBook.addContact();
//        addressBook.addContact();
//        addressBook.viewAddressBook();

    }

    public static void register() {
        UserInfo userInfo = new UserInfo();

        do System.out.print("아이디를 입력하세요\n> ");
        while (!userInfo.setId(scanner.nextLine().trim(), userInfoHashMap));

        do System.out.print("비밀번호를 입력하세요\n> ");
        while (!userInfo.setPassword(scanner.nextLine().trim()));

        System.out.println("계정이 생성되었습니다.");
        System.out.println("(Skip을 원하시면 Enter를 눌러주세요. 단, 이름과 전화번호는 필수)\n");

        do System.out.print("이름을 입력하세요\n> ");
        while (!userInfo.setName(scanner.nextLine().trim()));

        do System.out.print("전화번호를 입력하세요\n> ");
        while (!userInfo.setPhone(scanner.nextLine().trim()));

        do System.out.print("주소를 입력하세요\n> ");
        while (!userInfo.setAddress(scanner.nextLine().trim()));

        do System.out.print("생년월일을 입력하세요\n> ");
        while (!userInfo.setBirthday(scanner.nextLine().trim()));

        userInfoHashMap.put(userInfo.getId(), userInfo);
    }

    public static UserInfo login(String id, String pw) {
        for (String key : userInfoHashMap.keySet()) {
            if (key.equals(id)) {
                if (userInfoHashMap.get(key).getPassword().equals(pw)) {
                    String name = userInfoHashMap.get(key).getName();
                    System.out.println(name + "님 환영합니다\n");
                    return userInfoHashMap.get(key);
                } else {
                    System.out.println("비밀번호가 틀립니다\n");
                    return null;
                }
            }
        }
        System.out.println("존재하지 않는 아이디입니다\n");
        return null;
    }
}
