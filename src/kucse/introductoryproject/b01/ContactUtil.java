package kucse.introductoryproject.b01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class ContactUtil extends CsvUtil implements Observer {
    private static volatile ContactUtil instance = null;
    public ObservableContactHashSet contactHashSet;

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
        System.out.println(arg);
        System.out.println(contactHashSet);
        super.writeData(contactHashSet.toString());
    }
}
