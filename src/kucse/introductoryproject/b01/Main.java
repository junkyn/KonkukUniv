package kucse.introductoryproject.b01;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static UserInfo signedInUserInfo = null;

    public static AddressBook addressBook = null;

    public static UserInfoUtil userInfoUtil = null;

    public static void main(String[] args) {
        System.out.println(StringUtil.getHangulOnly("안녕하세요, 이율원입니다. ^~^"));
        System.out.println(StringUtil.toConsonants("안녕하세요, 이율원입니다. ^~^"));

        userInfoUtil = UserInfoUtil.getInstance("dummyUserData");

        do {
            loginPage();
            promptPage();
        } while (signedInUserInfo == null);

        System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다");
        ContactUtil.getInstance().closeWriterStream();
        userInfoUtil.closeWriterStream();
    }

    private static void promptPage() {
        String prompt;
        String[] value = new String[3];
        boolean loop = true;
        do {
            System.out.print(">");
            prompt = scanner.nextLine();
            int i = 0;
            try{
                for(i=0;i<3;i++){
                    value[i] = prompt.split(" ")[i];
                }
            }catch(ArrayIndexOutOfBoundsException e){
                value[i] = null;

            }

            switch(value[0]){
                case "help": helpList(); break;
                case "logout" : logOut(); loop = false; break;
                case "exit" : loop = false; break;
                case "view" :
                    if(value[1]==null) addressBook.viewAddressBook();
                    else{
                        try{
                            int page = Integer.parseInt(value[1]);
                            addressBook.viewAddressBook(page);
                        }
                        catch(NumberFormatException e){
                            addressBook.viewAddressBook(value[1]);
                        }
                    }
                    break;
                case "search":
                    if (value[1]==null) System.out.print("입력 오류\n>");
                    else {
                        if (value[2]==null) {
                            addressBook.searchContact(value[1]);
                        } else {
                            int page = Integer.parseInt(value[2]);
                            addressBook.searchContact(value[1], page);
                        }
                    }
                    break;
                case "add":addressBook.addContact();break;
                case "delete" : addressBook.deleteContact();break;
                case "edit" : addressBook.editContact();break;
                case "myprofile" : addressBook.myProfile();break;
                default:
                    System.out.println("잘못된 입력입니다");
                    helpList();
                    break;
            }
        }while(loop);
    }

    public static void loginPage(){


        System.out.print("환영합니다. ");
        do {
            System.out.print("login이나 register를 통해 로그인이나 회원가입을 해주세요\n> ");
            String prompt = scanner.nextLine();

            if (prompt.equals("register"))
                register();
            else if (prompt.startsWith("login "))
                signedInUserInfo = login(prompt.split(" ")[1], prompt.split(" ")[2]);
        } while (signedInUserInfo == null);

        addressBook = new AddressBook(signedInUserInfo);

        helpList();

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

        userInfoUtil.appendData(userInfo);
    }

    public static UserInfo login(String id, String pw) {
        if (!userInfoUtil.isIdPresent(id)) {
            System.out.println("존재하지 않는 아이디입니다\n");
            return null;
        }

        if (userInfoUtil.isUserInfoValid(id, pw)) {
            UserInfo loggedInUser = userInfoUtil.getUserInfoById(id);
            System.out.println(loggedInUser.getName() + "님 환영합니다\n");
            return loggedInUser;
        } else {
            System.out.println("비밀번호가 틀립니다\n");
            return null;
        }

    }
    public static void helpList(){
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
    public static void logOut(){
        signedInUserInfo = null;
    }
}
