package kucse.introductoryproject.b01;

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
    protected boolean validatePhone(String phone) {
        if (ContactUtil.getInstance().contactHashSet.isPhoneDuplicated(phone))
            System.out.println("이미 존재하는 전화번호입니다");
        else {
            return super.validatePhone(phone);
        }

        return false;
    }

    public void setMemo() {
        System.out.print("메모를 입력하세요\n> ");
        this.memo = scanner.nextLine().trim();
        notifyObservers();
    }
    public String getMemo() { return memo; }

    @Override
    public String toString() {
        return "Contact{" +
                super.toString() + '\'' +
                "memo='" + memo + '\'' +
                '}';
    }

    @Override
    public String toCsv() {
        return super.toCsv() + '\t' + memo + '\n';
    }

    public String toSearchableString() {
        return toCsv() + StringUtil.toConsonants(toCsv());
    }

}
