package kucse.introductoryproject.b01.dto;

import kucse.introductoryproject.b01.observer.Observable;
import kucse.introductoryproject.b01.observer.Observer;
import kucse.introductoryproject.b01.utils.DateValidatorUsingDateFormat;

import java.text.SimpleDateFormat;
import java.util.*;

import static kucse.introductoryproject.b01.Main.scanner;

public abstract class UserData implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    private String name;
    private String phone;
    private String address;
    private String birthday;
    private Date birthdayDate;

    public UserData() {

    }

    public UserData(String name, String phone, String address, String birthday) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        if (birthday.isBlank()) {
            this.birthday = birthday;
        } else {
            isBirthdayPresent(birthday);
        }
    }

    public void setName() {
        do System.out.print("이름을 입력하세요\n> ");
        while (!validateName(scanner.nextLine().trim()));
        notifyObservers();
    }

    public boolean validateName(String name) {
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
        else if(!checkPhoneFormat(phone)){
            System.out.println("올바른 전화번호가 아닙니다");
        }else{
            return true;
        }

        return false;
    }
    private boolean checkPhoneFormat(String number){
        if(number.matches("^[0-9-]*$")){
            if(number.contains("--"))return false;
            String check = number.replaceAll("-","");
            if(check.startsWith("010")){
                if(check.length()==11) {
                    phone = check.substring(0, 3) + "-" + check.substring(3, 7) + "-" + check.substring(7, 11);
                    return true;
                }
                else return false;
            }else if(check.startsWith("02")){
                if(check.length()==9){
                    phone = check.substring(0,2)+"-"+check.substring(2,5)+"-"+check.substring(5,9);
                    return true;
                }else if(check.length()==10){
                    phone = check.substring(0,2)+"-"+check.substring(2,6)+"-"+check.substring(6,10);
                    return true;
                }else{
                    return false;
                }
            }else if(check.length()==10){
                phone = check.substring(0,3)+"-"+check.substring(3,6)+"-"+check.substring(6,10);
                return true;
            }else if(check.length()==11){
                phone = check.substring(0,3)+"-"+check.substring(3,7)+"-"+check.substring(7,11);
                return true;
            }

            return true;
        }
        else return false;
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
        else if (birthday.isBlank()) {
            this.birthday = birthday;
            return true;
        } else return isBirthdayPresent(birthday);

        return false;
    }

    private boolean isBirthdayPresent(String birthday) {
        if (8 <= birthday.length() && birthday.length() <= 10 && birthday.matches("^(19|20)\\d\\d[-./]?(0[1-9]|1[0-2]|[1-9])[-./]?(([0-2][1-9]|3[01]|[1-9])|(1[0-9]|2[0-8])|(29(?!-02-29|/02/29|\\.02\\.29)))$")) {
            String[] formats = { "yyyyMMdd", "yyyy-M-d", "yyyy.M.d", "yyyy/M/d" };
            for (String format : formats) {
                DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat(format);
                if (validator.isValid(birthday)) {
                    birthdayDate = validator.getDate(birthday);
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(birthdayDate);

                    this.birthday = new SimpleDateFormat("yyyy-MM-dd").format(birthdayDate);
                    return true;
                }
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
