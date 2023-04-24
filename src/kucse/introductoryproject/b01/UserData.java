package kucse.introductoryproject.b01;

import java.util.ArrayList;
import java.util.List;

import static kucse.introductoryproject.b01.Main.scanner;

public class UserData implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    private String name;
    private String phone;
    private String address;
    private String birthday;
    private int birthdayYear, birthdayMonth, birthdayDay;

    public UserData() {

    }

    public UserData(String name, String phone, String address, String birthday) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }

    public void setName() {
        do System.out.print("이름을 입력하세요\n> ");
        while (!validateName(scanner.nextLine().trim()));
        notifyObservers();
    }

    protected boolean validateName(String name) {
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

    public void setPhone() {
        do System.out.print("전화번호를 입력하세요\n> ");
        while (!validatePhone(scanner.nextLine().trim()));
        notifyObservers();
    }

    protected boolean validatePhone(String phone) {
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

    public void setAddress() {
        do System.out.print("주소를 입력하세요\n> ");
        while (!validateAddress(scanner.nextLine().trim()));
        notifyObservers();
    }

    private boolean validateAddress(String address) {
        if (address.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.address = address;
            return true;
        }

        return false;
    }

    public void setBirthday() {
        do System.out.print("생일을 입력하세요\n> ");
        while (!validateBirthday(scanner.nextLine().trim()));
        notifyObservers();
    }

    private boolean validateBirthday(String birthday) {
        if (birthday.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else if (isBirthdayPresent(birthday)) {
            this.birthday = birthday;
            return true;
        }

        return false;
    }

    private boolean isBirthdayPresent(String birthday) {
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

    public String getName(){
        return this.name;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getAddress() { return address; }

    public String getBirthday() { return birthday; }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }

    public String toCsv() {
        return name + "\t" + phone + "\t" + address + "\t" + birthday;
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this, null);
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Object o) {
        observers.remove(o);
    }
}
