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
        contactHashSet.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        super.writeData(contactHashSet.toString());
    }
}
