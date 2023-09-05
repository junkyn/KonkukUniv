package kucse.introductoryproject.b01;

import static kucse.introductoryproject.b01.Main.scanner;

import java.util.ArrayList;
import kucse.introductoryproject.b01.dto.UserInfo;
import kucse.introductoryproject.b01.observer.ObservableUserInfoHashMap;

public class LoginManager {

    ObservableUserInfoHashMap userInfoHashMap;

    public LoginManager(ObservableUserInfoHashMap userInfoHashMap) {
        this.userInfoHashMap = userInfoHashMap;
    }

    public UserInfo show() {
        UserInfo signedInUser = null;
        System.out.println("┌────────────────┐");
        System.out.println("│  KUDRESS BOOK  │");
        System.out.println("└────────────────┘");
        System.out.print("환영합니다. ");
        do {
            System.out.print("login이나 register를 통해 로그인이나 회원가입을 해주세요\n> ");
            String prompt = scanner.nextLine().trim();

            if (prompt.equals("register")) {
                register();
            } else if (prompt.startsWith("login ")) {
                try {
                    signedInUser = login(prompt.split(" ")[1], prompt.split(" ")[2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("비밀번호를 입력해주세요\n\n");
                }
            } else if (prompt.startsWith("exit")) {
                System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다");
                System.exit(0);
            }

        } while (signedInUser == null);

        return signedInUser;
    }

    public void register() {
        UserInfo userInfo = new UserInfo();

        userInfo.setId(userInfoHashMap);
        userInfo.setPassword();

        System.out.println("계정이 생성되었습니다.");
        System.out.println("(Skip을 원하시면 Enter를 눌러주세요. 단, 이름과 전화번호는 필수)\n");

        userInfo.setName();
        userInfo.setPhone();
        userInfo.setAddress();
        userInfo.setBirthday();
        userInfo.setGroupList(new ArrayList<>());

        userInfoHashMap.append(userInfo);
    }

    public UserInfo login(String id, String pw) {
        if (!userInfoHashMap.isIdPresent(id)) {
            System.out.println("존재하지 않는 아이디입니다\n");
            return null;
        }

        if (userInfoHashMap.isUserInfoValid(id, pw)) {
            UserInfo loggedInUser = userInfoHashMap.getUserInfoById(id);
            System.out.println(loggedInUser.getName() + "님 환영합니다\n");
            return loggedInUser;
        } else {
            System.out.println("비밀번호가 틀립니다\n");
            return null;
        }

    }
}
