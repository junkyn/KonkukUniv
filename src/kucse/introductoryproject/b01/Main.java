package kucse.introductoryproject.b01;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(StringUtil.getHangulOnly("안녕하세요, 이율원입니다. ^~^"));
        System.out.println(StringUtil.toConsonants("안녕하세요, 이율원입니다. ^~^"));

        UserInfoUtil.init("dummyUserData");

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

//        AddressBook addressBook = new AddressBook("test");
//        addressBook.addContact();
//        addressBook.addContact();
//        addressBook.addContact();
//        addressBook.viewAddressBook();
        UserInfoUtil.closeWriterStream();
    }

    public static void register() {
        UserInfo userInfo = new UserInfo();

        do System.out.print("아이디를 입력하세요\n> ");
        while (!userInfo.setId(scanner.nextLine().trim()));

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

        UserInfoUtil.appendUserData(userInfo);
    }

    public static UserInfo login(String id, String pw) {
        if (!UserInfoUtil.isIdPresent(id)) {
            System.out.println("존재하지 않는 아이디입니다\n");
            return null;
        }

        if (UserInfoUtil.isUserInfoValid(id, pw)) {
            UserInfo loggedInUser = UserInfoUtil.getUserInfoById(id);
            System.out.println(loggedInUser.getName() + "님 환영합니다\n");
            return loggedInUser;
        } else {
            System.out.println("비밀번호가 틀립니다\n");
            return null;
        }

    }
}
