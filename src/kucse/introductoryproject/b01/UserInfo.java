package kucse.introductoryproject.b01;

public class UserInfo {
    private String id;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String birthday;

    public UserInfo() {

    }

    public UserInfo(String id, String password, String name, String phone, String address, String birthday) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }

    public boolean isMatchingPassword(String password) {
        return this.password.equals(password);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public boolean setId(String id) {
        if (UserInfoUtil.getInstance().isIdPresent(id)) {
            System.out.println("이미 존재하는 아이디입니다.");
        } else if (id.length() < 5)
            System.out.println("ID의 길이는 5 이상이어야 합니다.");
        else if (!id.matches("^[a-zA-Z0-9]*$"))
            System.out.println("ID는 알파벳 또는 숫자로만 이루어져야 합니다.");
        else {
            this.id = id;
            return true;
        }

        return false;
    }

    public boolean setPassword(String password) {
        if (password.length() < 8)
            System.out.println("암호의 길이는 8 이상이어야 합니다.");
        else if (password.matches(".*\\s.*"))
            System.out.println("암호는 공백 또는 탭을 포함할 수 없습니다.");
        else {
            this.password = password;
            return true;
        }

        return false;
    }

    public boolean setName(String name) {
        if (name.isBlank())
            System.out.println("이름은 필수 입력입니다");
        else if (name.chars().allMatch(Character::isDigit))
            System.out.println("정수로만 이루어진 이름은 사용할 수 없습니다");
        else if (name.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.name = name;
            return true;
        }

        return false;
    }

    public boolean setPhone(String phone) {
        if (phone.isBlank())
            System.out.println("전화번호는 필수 입력입니다");
        else if (phone.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.phone = phone;
            return true;
        }

        return false;
    }

    public boolean setAddress(String address) {
        if (address.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.address = address;
            return true;
        }

        return false;
    }

    public boolean setBirthday(String birthday) {
        if (address.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.birthday = birthday;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }

    public String toCsv() {
        return id + "\t" + password + "\t" + name + "\t" + phone + "\t" + address + "\t" + birthday + "\n";
    }
}

