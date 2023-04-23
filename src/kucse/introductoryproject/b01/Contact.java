package kucse.introductoryproject.b01;

public class Contact {
    private String name;
    private String phone;
    private String address;
    private String birthday;
    private int year,month,day;
    private String memo;

    public Contact() {

    }
    public Contact(String name, String phone, String address, String birthday, String memo) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.memo = memo;
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
        else if (ContactUtil.getInstance().isPhoneDuplicated(phone))
            System.out.println("이미 존재하는 전화번호입니다");
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
        if (birthday.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else if (validateBirthday(birthday)) {
            this.birthday = birthday;
            return true;
        }

        return false;
    }

    public boolean setMemo(String memo) {
        this.memo = memo;

        return true;
    }

    public boolean validateBirthday(String birthday) {
        if (birthday.matches("^(19|20)\\d\\d(?:-|\\.|)\\d{1,2}(?:-|\\.|)\\d{1,2}$")) {
            String[] formats = { "yyyyMMdd", "yyyy-M-d", "yyyy.M.d" };
            for (String format : formats) {
                DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat(format);
                if (validator.isValid(birthday)) return true;
            }
        }

        System.out.println("존재하지 않는 날짜입니다. 다시 입력해주세요");
        return false;
    }

    public String getPhone(){
        return this.phone;
    }
    public String getName(){
        return this.name;
    }
    public String getAddress() { return address; }
    public String getBirthday() { return birthday; }
    public String getMemo() { return memo; }
    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    public String toCsv() {
        return name + "\t" + phone + "\t" + address + "\t" + birthday + "\t" + memo + "\n";
    }
}
