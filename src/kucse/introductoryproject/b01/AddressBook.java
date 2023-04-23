package kucse.introductoryproject.b01;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;

public class AddressBook {
    private Contact onContact;
    private static Scanner scanner = new Scanner(System.in);
    private UserInfo userInfo;
    private ContactUtil contactUtil;

    public AddressBook(UserInfo signedInUserInfo){
        userInfo = signedInUserInfo;
        contactUtil = ContactUtil.getInstance(userInfo.getId());
    }


    public void addContact(){
        Contact contact = new Contact();

        System.out.println("(Skip을 원하시면 Enter을 눌러주세요. 단, 이름과 전화번호는 필수)");

        do System.out.print("이름을 입력하세요\n> ");
        while (!contact.setName(contactUtil.renameDuplicatedName(scanner.nextLine().trim())));

        do System.out.print("전화번호를 입력하세요\n> ");
        while (!contact.setPhone(scanner.nextLine().trim()));

        do System.out.print("주소를 입력하세요\n> ");
        while (!contact.setAddress(scanner.nextLine().trim()));

        do System.out.print("생년월일을 입력하세요\n> ");
        while (!contact.setBirthday(scanner.nextLine().trim()));

        do System.out.print("메모를 입력하세요\n> ");
        while (!contact.setMemo(scanner.nextLine().trim()));

        contactUtil.appendData(contact);
        System.out.println("주소록에 추가되었습니다");
    }

    public void viewAddressBook() { // view
        viewAddressBook(1);
    }
    public void viewAddressBook(int page) { // view (page)
        resetOnContact();
        printList(contactUtil.getContactList(), page);
    }
    public void viewAddressBook(String name) {
        onContact = contactUtil.getContactByName(name);
        if (onContact == null) {
            System.out.println("존재하지 않는 연락처입니다");
        } else {
            System.out.println(onContact);
            System.out.println();
            System.out.print(onContact.getName());
        }

    }
    public void searchContact(String query) {
        searchContact(query, 1);
    }
    public void searchContact(String query, int page) {
        List<Contact> searchResult = contactUtil.getContactList()
                .stream()
                .filter(it -> it.toSearchableString().contains(query))
                .toList();

        printList(searchResult, page);
    }

    public void printList(List<Contact> list, int page) {
        int maxPage = (list.size() - 1) / 10 + 1;
        if (list.isEmpty()) {
            System.out.println("결과가 없습니다.");
        } else if (page < 1 || maxPage < page) {
            System.out.println("존재하지 않는 페이지입니다.");
        } else {
            System.out.println("\n---------------------");
            List<Contact> pageList = list.subList((page - 1) * 10, min((page - 1) * 10 + 10, list.size()));
            int maxNameLength = pageList.stream().mapToInt(it -> it.getName().length()).max().getAsInt();
            for (Contact contact : pageList) {
                System.out.printf("%-" + maxNameLength + "s\t%s\n", contact.getName(), contact.getPhone());
            }

            System.out.println("--------(" + page + "/" + maxPage + ")--------");
        }
    }

    public void editContact() {
        if (onContact == null) {
            System.out.println("수정할 연락처를 열람해주세요");
        } else {
            String order;
            do {
                System.out.println("수정할 부분을 입력해주세요");
                System.out.println("(name : 이름, num : 전화번호, address : 주소, birth : 생년월일, memo : 메모, cancel : 취소");
                System.out.print(onContact.getName()+">edit>");
                order = scanner.nextLine();
                if(order.equals("name")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+">edit>name>");
                    while (!onContact.setName(contactUtil.renameDuplicatedName(scanner.nextLine().trim())));
                }
                else if(order.equals("num")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+">edit>num>");
                    while (!onContact.setPhone(scanner.nextLine().trim()));
                }
                else if(order.equals("address")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+">edit>address>");
                    while (!onContact.setAddress(scanner.nextLine().trim()));
                }
                else if(order.equals("birth")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+">edit>birth>");
                    while (!onContact.setBirthday(scanner.nextLine().trim()));
                }
                else if(order.equals("memo")){
                    do System.out.println("수정할 내용을 입력해주세요\n"+onContact.getName()+">edit>memo>");
                    while (!onContact.setMemo(scanner.nextLine().trim()));
                }
            } while(!order.equals("cancel"));
            System.out.println("수정되었습니다");
            System.out.println();
        }
    }
    public void deleteContact() {
        if (onContact == null) {
            System.out.println("삭제할 연락처를 열람해주세요");
        } else {
            String answer;
            System.out.print("정말 삭제하시겠습니까? 삭제를 원하시면 '삭제'를 입력해주세요\n"+onContact.getName()+">delete>");
            answer = scanner.nextLine();
            if (answer.equals("삭제")) {
                contactUtil.removeContact(onContact);
                onContact = null;
                System.out.println("삭제되었습니다.");
            } else {
                System.out.println("삭제되지 않았습니다");
            }
            System.out.println();
        }

    }

    public void myProfile() {
        String order;
        do {
            System.out.println(userInfo);
            System.out.println("수정할 부분을 입력해주세요");
            System.out.println("(name : 이름, num : 전화번호, address : 주소, birth : 생년월일, cancel : 취소");
            System.out.println(userInfo.getName()+">edit>");
            order = scanner.nextLine();
            if(order.equals("name")){
                do System.out.println("수정할 내용을 입력해주세요\n"+userInfo.getName()+">name>");
                while (!userInfo.setName(scanner.nextLine()));
            }
            else if(order.equals("num")){
                do System.out.println("수정할 내용을 입력해주세요\n"+userInfo.getName()+">num>");
                while (!userInfo.setPhone(scanner.nextLine()));
            }
            else if(order.equals("address")){
                do System.out.println("수정할 내용을 입력해주세요\n"+userInfo.getName()+">address>");
                while (!userInfo.setAddress(scanner.nextLine()));
            }
            else if(order.equals("birth")){
                do System.out.println("수정할 내용을 입력해주세요\n"+userInfo.getName()+">birth>");
                while (!userInfo.setBirthday(scanner.nextLine()));
            }

        } while (!order.equals("cancel"));
        System.out.println("수정되었습니다");
        System.out.println();
    }

    public void resetOnContact(){
        onContact = null;
    }

}
