package kucse.introductoryproject.b01.dto;

import kucse.introductoryproject.b01.csvhandler.ContactHandler;
import kucse.introductoryproject.b01.utils.StringUtil;

import static kucse.introductoryproject.b01.Main.scanner;

public class Contact extends UserData {
    private String memo;

    public Contact() {

    }
    public Contact(String name, String phone, String address, String birthday, String memo) {
        super(name, phone, address, birthday);
        this.memo = memo;
    }

    @Override
    public void setName() {
        do System.out.print("이름을 입력하세요\n> ");
        while (!validateName(ContactHandler.getInstance().contactHashSet.renameDuplicatedName(scanner.nextLine().trim())));
        notifyObservers();
    }

    @Override
    public boolean validatePhone(String phone) {
        if (ContactHandler.getInstance() != null && ContactHandler.getInstance().contactHashSet.isPhoneDuplicated(phone))
            System.out.println("이미 존재하는 전화번호입니다");
        else {
            return super.validatePhone(phone);
        }

        return false;
    }

    public void setMemo() {
        do System.out.print("메모를 입력하세요\n> ");
        while(!validateMemo(scanner.nextLine().trim()));
        notifyObservers();
    }
    public boolean validateMemo(String memo){
        if (memo.contains("\t")) {
            System.out.println("탭(tab)은 사용하실 수 없습니다");
            return false;
        }
        this.memo = memo;
        return true;
    }
    public String getMemo() { return memo; }

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
        searchable.append(getMemo().replaceAll(" ", ""));
        return searchable.toString();
    }

}
