package kucse.introductoryproject.b01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    private Contact OnContact;
    private static Scanner scanner = new Scanner(System.in);

    private HashSet<Contact> contactSet;
    public AddressBook(String userName){
        try {
            File file = new File(userName + ".csv");
            if (!file.exists()) file.createNewFile();
            contactSet = Contact.parseContactsFromCSV(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addContact(){
        Contact contact = new Contact();

        System.out.println("(Skip을 원하시면 Enter을 눌러주세요. 단, 이름과 전화번호는 필수");

        do System.out.print("이름을 입력하세요\n> ");
        while (!contact.setName(renameDuplicatedName(scanner.nextLine().trim())));

        do System.out.print("전화번호를 입력하세요\n> ");
        while (!contact.setPhone(scanner.nextLine().trim(), this));

        do System.out.print("주소를 입력하세요\n> ");
        while (!contact.setAddress(scanner.nextLine().trim()));

        do System.out.print("생년월일을 입력하세요\n> ");
        while (!contact.setBirthday(scanner.nextLine().trim()));

        do System.out.print("메모를 입력하세요\n> ");
        while (!contact.setMemo(scanner.nextLine().trim()));

        contactSet.add(contact);
    }

    public void viewAddressBook() { // view
        viewAddressBook(1);
    }
    public void viewAddressBook(int page) { // view (page)
        List<Contact> list = new ArrayList<>(contactSet);
        int maxPage = (list.size() - 1) / 10 + 1;
        if (list.isEmpty()) {
            System.out.println("주소록이 비어있습니다");
        } else if (maxPage < page) {
            System.out.println("존재하지 않는 페이지 입니다");
        } else {
            System.out.println("---------------------");
            for (int i = (page - 1) * 10; i < (page - 1) * 10 + 10; i++){
                if (list.size() <= i)
                    break;
                else
                    System.out.println(list.get(i));
            }
            System.out.println("--------(" + page + "/" + maxPage + ")--------");
        }
    }

    protected boolean isPhoneDuplicated(String n) { // 중복 전화번호
        return contactSet.stream().anyMatch(it -> getNumbersOnly(it.getPhone()).equals(getNumbersOnly(n)));
    }

    private String getNumbersOnly(String s) { // 정수만 추출
        return s.replaceAll("[^0-9]","");
    }

    private String sameName(String n){ // 동명이인 체크
        int count=0;
        for(Contact c : contactSet){
            if(c.getName().equals(n)){
                count++;
                for(Contact con : contactSet){
                    if(con.getName().equals(n+"("+count+")")){
                        count++;
                    }
                }
            }
        }
        if(count==0)
            return n;
        else
            return n+"("+count+")";
    }

    private String renameDuplicatedName(String name) {
        long count = contactSet.stream().filter(it -> it.getName().equals(name)).count();
        return count == 0 ? name : name + "(" + count + ")";
    }
}
