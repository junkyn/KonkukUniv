package kucse.introductoryproject.b01;

import java.io.File;
import java.util.HashSet;

public class AdressBook {
    private HashSet<Contact> contactArrayList;
    public AdressBook(String userName){
        contactArrayList = Contact.parseContactsFromCSV(new File(userName+".csv"));

    }
    public void addContact(Contact cont){
        contactArrayList.add(cont);
    }
}
