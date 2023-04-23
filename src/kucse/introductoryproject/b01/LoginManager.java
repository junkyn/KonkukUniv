package kucse.introductoryproject.b01;

import static kucse.introductoryproject.b01.Main.scanner;

public class LoginManager {
    UserInfoUtil userInfoUtil;
    public LoginManager() {
        userInfoUtil = UserInfoUtil.getInstance();
    }

    public UserInfo show() {
        UserInfo signedInUser = null;

        System.out.print("환영합니다. ");
        do {
            System.out.print("login이나 register를 통해 로그인이나 회원가입을 해주세요\n> ");
            String prompt = scanner.nextLine();

            if (prompt.equals("register"))
                register();
            else if (prompt.startsWith("login "))
                signedInUser = login(prompt.split(" ")[1], prompt.split(" ")[2]);
        } while (signedInUser == null);

        return signedInUser;
    }

    public void register() {
        UserInfo userInfo = new UserInfo();

        userInfo.setId();
        userInfo.setPassword();

        System.out.println("계정이 생성되었습니다.");
        System.out.println("(Skip을 원하시면 Enter를 눌러주세요. 단, 이름과 전화번호는 필수)\n");

        userInfo.setName();
        userInfo.setPhone();
        userInfo.setAddress();
        userInfo.setBirthday();

        userInfoUtil.appendData(userInfo);
    }

    public UserInfo login(String id, String pw) {
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
}
