package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ContactUtil extends CsvUtil {
    private static volatile ContactUtil instance = null;
    private HashSet<Contact> contactHashSet;

    private ContactUtil(String fileName) {
        super(fileName);
    }

    public static ContactUtil getInstance(String fileName) {
        if (Objects.isNull(instance)) {
            synchronized (ContactUtil.class) {
                if (Objects.isNull(instance)) {
                    instance = new ContactUtil(fileName);
                }
            }
        }

        return instance;
    }

    public static ContactUtil getInstance() {
        return instance;
    }

    public synchronized static void destroyInstance() {
        instance = null;
    }

    @Override
    protected void parseDataFromCSV(File file) {
        contactHashSet = new HashSet<>();
        try (Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                String[] contactInfo = str.split("\t");

                String name = contactInfo[0].trim();
                String phone = contactInfo[1].trim();
                String address = contactInfo.length < 3 ? "" : contactInfo[2].trim();
                String birthday = contactInfo.length < 4 ? "" : contactInfo[3].trim();
                String memo = contactInfo.length < 5 ? "" : contactInfo[4].trim();

                contactHashSet.add(new Contact(name, phone, address, birthday, memo));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void appendData(Contact contact) {
        super.appendData(contact.toCsv());
        contactHashSet.add(contact);
    }

    public ArrayList<Contact> getContactList() {
        return new ArrayList<>(contactHashSet);
    }

    public Contact getContactByName(String name) {
        return contactHashSet.stream().filter(it -> it.getName().equals(name)).findFirst().orElse(null);
    }

    public void removeContact(Contact contact) {
        contactHashSet.remove(contact);
    }

    public boolean isPhoneDuplicated(String phone) {
        return contactHashSet.stream().anyMatch(it -> StringUtil.getNumbersOnly(it.getPhone()).equals(StringUtil.getNumbersOnly(phone)));
    }

    public String renameDuplicatedName(String name) {
        AtomicInteger count = new AtomicInteger();
        if (contactHashSet.stream().anyMatch(it -> it.getName().equals(name)))
            count.incrementAndGet();

        while (count.get() > 0 && contactHashSet.stream().anyMatch(it -> it.getName().equals(name + "(" + count.get() + ")")))
            count.incrementAndGet();

        return count.get() == 0 ? name : name + "(" + count.get() + ")";
    }
}
