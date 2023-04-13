package kucse.introductoryproject.b01;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class addressBook {
    private boolean isView = false;
    Scanner s = new Scanner(System.in);

    private HashSet<Contact> contactArrayList;
    public addressBook(String userName){
        contactArrayList = Contact.parseContactsFromCSV(new File(userName+".csv"));

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

    }
    private boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
