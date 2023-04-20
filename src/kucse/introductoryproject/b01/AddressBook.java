package kucse.introductoryproject.b01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    private Contact onContact;
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
        resetOnContact();
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
    public  void viewAddressBook(String name){
        onContact = contactSet.stream().filter(it -> it.getName().equals(name)).findFirst().orElseGet(null);
        if(onContact==null){
            System.out.println("존재하지 않는 연락처입니다");
            return;
        }
        else{
            System.out.println(onContact);
            System.out.println();
            System.out.println(onContact.getName()+">");
        }

    }
    public void editContact(){
        if(onContact==null){
            System.out.println("수정할 연락처를 열람해주세요");
            return;
        }else{
            String order;
            String value;
            do{
                System.out.println("수정할 부분을 입력해주세요");
                System.out.println("(name : 이름, num : 전화번호, address : 주소, birth : 생년월일, memo : 메모, cancel : 취소");
                System.out.println(onContact.getName()+">edit>");
                order = scanner.nextLine();
                if(order.equals("name")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+"edit>name>");
                    while (!onContact.setName(renameDuplicatedName(scanner.nextLine().trim())));
                }
                else if(order.equals("num")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+"edit>num>");
                    while (!onContact.setPhone(scanner.nextLine().trim(), this));
                }
                else if(order.equals("address")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+"edit>address>");
                    while (!onContact.setAddress(scanner.nextLine().trim()));
                }
                else if(order.equals("birth")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+"edit>birth>");
                    while (!onContact.setBirthday(scanner.nextLine().trim()));
                }
                else if(order.equals("memo")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+"edit>memo>");
                    while (!onContact.setMemo(scanner.nextLine().trim()));
                }
            }while(order.equals("cancel"));
        }
    }
    public void deleteContact(){
        if(onContact==null){
            System.out.println("삭제할 연락처를 열람해주세요");
            return;
        }
        else{
            String answer;
            System.out.println("정말 삭제하시겠습니까? 삭제를 원하시면 '삭제'를 입력해주세요\n"+onContact.getName()+">delete>");
            answer = scanner.nextLine();
            if(answer.equals("삭제")){
                contactSet.remove(onContact);
                onContact = null;
                System.out.println("삭제되었습니다.");
            }else{
                System.out.println("삭제되지 않았습니다");
            }
        }

    }
    public void resetOnContact(){
        onContact = null;
    }
    protected boolean isPhoneDuplicated(String n) { // 중복 전화번호
        return contactSet.stream().anyMatch(it -> getNumbersOnly(it.getPhone()).equals(getNumbersOnly(n)));
    }

    private String getNumbersOnly(String s) { // 정수만 추출
        return s.replaceAll("[^0-9]","");
    }

    private String renameDuplicatedName(String n){ // 동명이인 체크
        int count = 0;
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
}
