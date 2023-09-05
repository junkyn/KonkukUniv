package kucse.introductoryproject.b01.dto;

import static kucse.introductoryproject.b01.Main.scanner;

import kucse.introductoryproject.b01.observer.ObservableContactHashSet;
import kucse.introductoryproject.b01.utils.StringUtil;

public class Contact extends UserData {

    private String memo;

    public Contact() {

    }

    public Contact(String name, String phone, String address, String birthday, String memo) {
        super(name, phone, address, birthday);
        this.memo = memo;
    }

    public void setName(ObservableContactHashSet contactHashSet) {
        do {
            System.out.print("이름을 입력하세요\n> ");
        }
        while (!validateName(contactHashSet.countName(scanner.nextLine().trim())));
        notifyObservers();
    }

    public void rename(ObservableContactHashSet contactHashSet) {
        do {
            System.out.print("이름을 입력하세요\n> ");
        }
        while (!validateName(contactHashSet.renameFrom(getName(), scanner.nextLine().trim())));
        notifyObservers();
    }

    public void setPhone(ObservableContactHashSet contactHashSet) {
        do {
            System.out.print("전화번호를 입력하세요\n> ");
        }
        while (!validatePhone(contactHashSet, scanner.nextLine().trim()));
        notifyObservers();
    }

    public boolean validatePhone(ObservableContactHashSet contactHashSet, String phone) {
        if (contactHashSet.isPhoneDuplicated(phone)) {
            System.out.println("이미 존재하는 전화번호입니다");
        } else {
            return super.validatePhone(phone);
        }

        return false;
    }

    public void setMemo() {
        do {
            System.out.print("메모를 입력하세요\n> ");
        }
        while (!validateMemo(scanner.nextLine().trim()));
        notifyObservers();
    }

    public boolean validateMemo(String memo) {
        this.memo = memo;
        return true;
    }

    public String getMemo() {
        return memo;
    }

    @Override
    public String toString() {
        String str;
        str = "\n---------------------\n" +
            "이름 : " + getName() + "\n" +
            "전화번호 : " + getPhone() + "\n";
        if (!getAddress().isEmpty()) {
            str += "주소 : " + getAddress() + "\n";
        }
        if (!getBirthday().isEmpty()) {
            str += "생년월일 : " + getBirthday() + "\n";
        }
        if (!getMemo().isEmpty()) {
            str += "메모 : " + memo + "\n";
        }
        str += "---------------------";
        return str;
    }

    @Override
    public String toCsv() {
        return super.toCsv() + '\t' + memo + '\n';
    }

    public String toSearchableString() {
        StringBuilder searchable = new StringBuilder();
        searchable.append(getName().replaceAll(" ", "").toLowerCase()).append('\t');
        searchable.append(StringUtil.toConsonants(searchable.toString()));
        searchable.append(getPhone().replaceAll("-", "")).append('\t');
        searchable.append(getAddress().replaceAll(" ", "")).append('\t');
        searchable.append(getBirthday()).append('\t');
        searchable.append(getMemo().replaceAll("[ \t]", ""));
        return searchable.toString();
    }

}
