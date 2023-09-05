package kucse.introductoryproject.b01.csvhandler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import kucse.introductoryproject.b01.dto.Contact;
import kucse.introductoryproject.b01.observer.Observable;
import kucse.introductoryproject.b01.observer.ObservableContactHashSet;
import kucse.introductoryproject.b01.observer.Observer;
import kucse.introductoryproject.b01.utils.StringUtil;

public class ContactHandler extends CsvHandler implements Observer {

    public ObservableContactHashSet contactHashSet;

    public ContactHandler(String fileName) {
        super(fileName);
    }


    @Override
    protected void parseDataFromCSV(File file) {
        contactHashSet = new ObservableContactHashSet();
        try (Scanner fileScanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                String[] contactInfo = str.split("\t");

                Contact contact = new Contact();

                try {
                    if (!contact.validateName(contactInfo[0].trim())) {
                        throw new IllegalArgumentException("이름 형식이 올바르지 않습니다.");
                    }
                    if (contactHashSet.isNameDuplicated(contact.getName())) {
                        throw new IllegalArgumentException("중복된 이름입니다.");
                    }
                    if (!contact.validatePhone(contactInfo[1].trim())) {
                        throw new IllegalArgumentException("전화번호 형식이 올바르지 않습니다.");
                    }
                    if (contactHashSet.isPhoneDuplicated(contact.getPhone())) {
                        throw new IllegalArgumentException("중복된 전화번호입니다.");
                    }
                    if (!contact.validateAddress(
                        contactInfo.length < 3 ? "" : contactInfo[2].trim())) {
                        throw new IllegalArgumentException("주소 형식이 올바르지 않습니다.");
                    }
                    if (!contact.validateBirthday(
                        contactInfo.length < 4 ? "" : contactInfo[3].trim())) {
                        throw new IllegalArgumentException("생일 형식이 올바르지 않습니다.");
                    }
                    if (!contact.validateMemo(contactInfo.length < 5 ? ""
                        : str.substring(StringUtil.ordinalIndexOf(str, "\t", 4)).trim())) {
                        throw new IllegalArgumentException("메모 형식이 올바르지 않습니다.");
                    }

                } catch (IllegalArgumentException e) {
                    System.err.println(file.getName() + " 파일 무결성 에러:\n" + str);
                    System.err.println(e.getMessage());
                    System.exit(0);
                } catch (ArrayIndexOutOfBoundsException e) {

                }

                contactHashSet.add(contact);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        contactHashSet.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        super.writeData(contactHashSet.toString());
    }
}
