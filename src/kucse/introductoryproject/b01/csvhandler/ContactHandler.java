package kucse.introductoryproject.b01.csvhandler;

import kucse.introductoryproject.b01.dto.Contact;
import kucse.introductoryproject.b01.observer.Observable;
import kucse.introductoryproject.b01.observer.ObservableContactHashSet;
import kucse.introductoryproject.b01.observer.Observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class ContactHandler extends CsvHandler implements Observer {
    private static volatile ContactHandler instance = null;
    public ObservableContactHashSet contactHashSet;

    private ContactHandler(String fileName) {
        super(fileName);
    }

    public static ContactHandler getInstance(String fileName) {
        if (Objects.isNull(instance)) {
            synchronized (ContactHandler.class) {
                if (Objects.isNull(instance)) {
                    instance = new ContactHandler(fileName);
                }
            }
        }

        return instance;
    }

    public static ContactHandler getInstance() {
        return instance;
    }

    public synchronized static void destroyInstance() {
        instance = null;
    }

    @Override
    protected void parseDataFromCSV(File file) {
        contactHashSet = new ObservableContactHashSet();
        try (Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                String[] contactInfo = str.split("\t");

                Contact contact = new Contact();

                try {
                    if (!contact.validateName(contactInfo[0].trim()))
                        throw new IllegalArgumentException("이름 형식이 올바르지 않습니다.");
                    if (contactHashSet.isNameDuplicated(contact.getName()))
                        throw new IllegalArgumentException("중복된 이름입니다.");
                    if (!contact.validatePhone(contactInfo[1].trim()))
                        throw new IllegalArgumentException("전화번호 형식이 올바르지 않습니다.");
                    if (contactHashSet.isPhoneDuplicated(contact.getPhone()))
                        throw new IllegalArgumentException("중복된 전화번호입니다.");
                    if (!contact.validateAddress(contactInfo.length < 3 ? "" : contactInfo[2].trim()))
                        throw new IllegalArgumentException("주소 형식이 올바르지 않습니다.");
                    if (!contact.validateBirthday(contactInfo.length < 4 ? "" : contactInfo[3].trim()))
                        throw new IllegalArgumentException("생일 형식이 올바르지 않습니다.");
                    if (!contact.validateMemo(contactInfo.length < 5 ? "" : contactInfo[4].trim()))
                        throw new IllegalArgumentException("메모 형식이 올바르지 않습니다.");

                } catch (IllegalArgumentException e) {
                    System.err.println(file.getName() + " 파일 무결성 에러:\n" + str);
                    System.err.println(e.getMessage());
                    System.exit(0);
                }

                contactHashSet.add(contact);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        contactHashSet.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        super.writeData(contactHashSet.toString());
    }
}
