package kucse.introductoryproject.b01;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    private Contact OnContact;
    Scanner s = new Scanner(System.in);

    private HashSet<Contact> contactSet;
    public AddressBook(String userName){
        contactSet = Contact.parseContactsFromCSV(new File(userName+".csv"));

    }
    public void addContact(){
        String name,phone,address,birthday,memo;
        System.out.println("(Skip을 원하시면 Enter을 눌러주세요. 단, 이름과 전화번호는 필수");
        while(true){
            System.out.println("이름을 입력하세요");
            System.out.print(">");
            name=s.nextLine();
            if(name.equals("\n"))
                System.out.println("이름은 필수 입력입니다");
            else if(isInteger(name))
                System.out.println("정수로 이뤄진 이름은 불가능합니다");
            else if(name.contains("\t"))
                System.out.println("탭(tab)은 사용하실 수 없습니다");
            else
                break;
        }
        while(true){
            System.out.println("전화번호를 입력하세요");
            System.out.print(">");
            phone=s.nextLine();
            if(phone.equals("\n"))
                System.out.println("전화번호는 필수 입력입니다");
            else if(phone.contains("\t"))
                System.out.println("탭(tab)은 사용하실 수 없습니다");
            else if(isDuplicated(phone)){
                System.out.println("이미 존재하는 전화번호입니다");
            }
            else
                break;
        }
        while(true){
            System.out.println("주소를 입력하세요");
            System.out.print(">");
            address=s.nextLine();
            if(address.contains("\t"))
                System.out.println("탭(tab)은 사용하실 수 없습니다");
            else break;
        }
        while(true){
            System.out.println("생년월일을 입력하세요");
            System.out.print(">");
            birthday=s.nextLine();
            if(birthday.contains("\t"))
                System.out.println("탭(tab)은 사용하실 수 없습니다");
            else break;
        }
        while(true){
            System.out.println("메모를 입력하세요");
            System.out.print(">");
            memo=s.nextLine();
            if(memo.contains("\t"))
                System.out.println("탭(tab)은 사용하실 수 없습니다");
            else break;
        }
        contactSet.add(new Contact(sameName(name),phone,address,birthday,memo));
    }

    public void viewAddressBook(){ // view
        List<Contact> list = new ArrayList<>(contactSet);
        int maxpage = (list.size()-1)/10+1;
        if(list.isEmpty()){
            System.out.println("주소록이 비어있습니다");
        }
        else{
            System.out.println("---------------------");
            for(int i = 0;i<10;i++){
                if(list.get(i)==null)
                    break;
                else{
                    System.out.println(list.get(i));
                }
            }
            System.out.println("--------(1/"+maxpage+")--------");
        }
    }
    public void viewAddressBook(int page){ // view (page)
        List<Contact> list = new ArrayList<>(contactSet);
        int maxpage = (list.size()-1)/10+1;
        if(list.isEmpty()){
            System.out.println("주소록이 비어있습니다");
        }
        else if(maxpage<page){
            System.out.println("존재하지 않는 페이지 입니다");
        }
        else{
            System.out.println("---------------------");
            for(int i = (page-1)*10;i<(page-1)*10+10;i++){
                if(list.get(i)==null)
                    break;
                else{
                    System.out.println(list.get(i));
                }
            }
            System.out.println("--------("+page+"/"+maxpage+")--------");
        }
    }

    private boolean isInteger(String s){ // 정수로만 이뤄졌는지 체크
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    private boolean isDuplicated(String n){ // 중복 전화번호
        for(Contact c : contactSet){
            if(exportInt(c.getPhone()).equals(exportInt(n)))
                return true;
        }
        return false;
    }
    private String exportInt(String s){ // 정수만 추출
        return s.replaceAll("[^0-9]","");
    }
    private String sameName(String n){ // 동명이인 체크
        int count=0;
        for(Contact c : contactSet){
            if(c.getName().equals(n))
                count++;
        }
        if(count==0)
            return n;
        else
            return n+"("+count+")";
    }
}
