package kucse.introductoryproject.b01;

import static java.lang.Integer.parseInt;
import static java.lang.Math.min;
import static kucse.introductoryproject.b01.Main.scanner;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import kucse.introductoryproject.b01.dto.Contact;
import kucse.introductoryproject.b01.observer.ObservableContactHashSet;

public class AddressBook {

    private Contact onContact;
    private ObservableContactHashSet contactHashSet;

    public AddressBook(ObservableContactHashSet contactHashSet) {
        this.contactHashSet = contactHashSet;
    }


    public void addContact() {
        this.clearOnContact();
        Contact contact = new Contact();

        System.out.println("(Skip을 원하시면 Enter을 눌러주세요. 단, 이름과 전화번호는 필수)");

        contact.setName(contactHashSet);
        contact.setPhone(contactHashSet);
        contact.setAddress();
        contact.setBirthday();
        contact.setMemo();

        contactHashSet.add(contact);
        System.out.println("주소록에 추가되었습니다");
    }

    public void viewAddressBook() { // view
        viewAddressBook(1);
    }

    public void viewAddressBook(int page) { // view (page)
        clearOnContact();
        if (page == 1) {
            printBirthday();
        }
        printList(contactHashSet.toArrayList(), page);
    }

    public void viewAddressBook(String name) {
        onContact = contactHashSet.getContactByName(name);
        if (onContact == null) {
            System.out.println("존재하지 않는 연락처입니다");
        } else {
            System.out.println(onContact);
            System.out.println();
        }

    }

    public void printBirthday() {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<String> list = contactHashSet.stream()
            .filter(it -> it.getBirthday().length() == 10)
            .sorted(Comparator.comparing(Contact::getName))
            .filter(it -> it.getBirthday().substring(5).equals(today.substring(5)))
            .filter(it ->
                (parseInt(today.substring(0, 4)) + 1 - parseInt(it.getBirthday().substring(0, 4)))
                    > 0)
            .map(it -> "- " + it.getName() + "("
                + (parseInt(today.substring(0, 4)) + 1 - parseInt(it.getBirthday().substring(0, 4)))
                + "살)\t"
                + it.getPhone() + '\t'
            )
            .toList();
        if (list.isEmpty()) {
            return;
        }
        System.out.println("""
            ┌───────────────────────────────────────┐                
            │                 ┌ iii ┐               │
            │ └ ( ˇ 3ˇ)┘     ┌       ┐    └ (^∀^*@) │
            │              ┌─  [HBD]  ─┐            │
            │              └───────────┘            │""");
        System.out.println("├──────────── 오늘 생일인 친구 ────────────┤");
        list.forEach(System.out::println);

        System.out.println("└───────────────────────────────────────┘");
    }

    public void searchContact(String query) {
        searchContact(query, 1);
    }

    public void searchContact(String query, int page) {
        this.clearOnContact();
        String finalQuery = query.toLowerCase();
        if (finalQuery.isEmpty()) {
            System.out.println("입력 오류");
            return;
        }
        List<Contact> searchResult = contactHashSet.toArrayList()
            .stream()
            .filter(it -> it.toSearchableString().contains(finalQuery))
            .toList();

        printList(searchResult, page);
    }

    public void printList(List<Contact> list, int page) {
        list = list.stream().sorted(Comparator.comparing(Contact::getName)).toList();
        int maxPage = (list.size() - 1) / 10 + 1;
        if (list.isEmpty()) {
            System.out.println("결과가 없습니다.");
        } else if (page < 1 || maxPage < page) {
            System.out.println("존재하지 않는 페이지입니다.");
        } else {
            System.out.println("\n---------------------");
            List<Contact> pageList = list.subList((page - 1) * 10,
                min((page - 1) * 10 + 10, list.size()));
            int maxNameLength = pageList.stream().mapToInt(it -> it.getName().length()).max()
                .getAsInt();
            for (Contact contact : pageList) {
                System.out.printf("%-" + maxNameLength + "s\t%s\n", contact.getName(),
                    contact.getPhone());
            }

            System.out.println("--------(" + page + "/" + maxPage + ")--------");
        }
    }

    public void editContact() {
        if (onContact == null) {
            System.out.println("수정할 연락처를 열람해주세요.");
        } else {
            String order = "";
            while (!order.equals("exit")) {
                System.out.println(onContact);
                System.out.println("수정할 항목을 선택해주세요.");
                System.out.println(
                    "(name : 이름, num : 전화번호, address : 주소, birth : 생년월일, memo : 메모, exit : 나가기)\n");
                System.out.print(onContact.getName() + " > edit > ");
                order = scanner.nextLine().trim();

                System.out.print(order.matches("name|num|address|birth|memo") ? "수정할 " : "");
                switch (order) {
                    case "name" -> onContact.rename(contactHashSet);
                    case "num" -> onContact.setPhone(contactHashSet);
                    case "address" -> onContact.setAddress();
                    case "birth" -> onContact.setBirthday();
                    case "memo" -> onContact.setMemo();
                    case "exit" -> {
                        continue;
                    }
                    default -> {
                        System.out.println("잘못된 입력입니다.");
                        continue;
                    }
                }
                System.out.println("수정되었습니다.");
            }
        }
    }

    public void deleteContact() {
        if (onContact == null) {
            System.out.println("삭제할 연락처를 열람해주세요");
        } else {
            String answer;
            System.out.print(
                "정말 삭제하시겠습니까? 삭제를 원하시면 '삭제'를 입력해주세요\n" + onContact.getName() + " > delete > ");
            answer = scanner.nextLine();
            if (answer.equals("삭제")) {
                contactHashSet.remove(onContact);
                clearOnContact();
                System.out.println("삭제되었습니다.");
            } else {
                System.out.println("삭제되지 않았습니다");
            }
            System.out.println();
        }

    }

    public Contact getOnContact() {
        return onContact;
    }

    public void clearOnContact() {
        onContact = null;
    }
}
