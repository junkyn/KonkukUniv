package kucse.introductoryproject.b01.observer;

import kucse.introductoryproject.b01.dto.Contact;
import kucse.introductoryproject.b01.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ObservableContactHashSet extends HashSet<Contact> implements Observable, Observer {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public boolean add(Contact contact) {
        boolean isAdded = super.add(contact);
        contact.addObserver(this);
        notifyObservers();
        return isAdded;
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemoved = super.remove(o);
        notifyObservers();
        return isRemoved;
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this, null);
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Object o) {
        observers.remove(o);
    }

    public ArrayList<Contact> toArrayList() {
        return new ArrayList<>(this);
    }

    public Contact getContactByName(String name) {
        return this.stream().filter(it -> it.getName().equals(name)).findFirst().orElse(null);
    }

    public boolean isNameDuplicated(String name) {
        return this.stream().anyMatch(it -> it.getName().equals(name));
    }

    public boolean isPhoneDuplicated(String phone) {
        return this.stream().anyMatch(it -> StringUtil.getNumbersOnly(it.getPhone()).equals(StringUtil.getNumbersOnly(phone)));
    }

    public String countName(String name) {
        AtomicInteger count = new AtomicInteger();
        if (this.stream().anyMatch(it -> it.getName().equals(name)))
            count.incrementAndGet();

        while (count.get() > 0 && this.stream().anyMatch(it -> it.getName().equals(name + "(" + count.get() + ")")))
            count.incrementAndGet();

        return count.get() == 0 ? name : name + "(" + count.get() + ")";
    }

    public String renameFrom(String fromName, String toName) {
        AtomicInteger count = new AtomicInteger();
        if (this.stream().filter(it -> !it.getName().equals(fromName)).anyMatch(it -> it.getName().equals(toName)))
            count.incrementAndGet();

        while (count.get() > 0 && this.stream().filter(it -> !it.getName().equals(fromName)).anyMatch(it -> it.getName().equals(toName + "(" + count.get() + ")")))
            count.incrementAndGet();

        return count.get() == 0 ? toName : toName + "(" + count.get() + ")";
    }

    @Override
    public String toString() {
        return this.stream().map(Contact::toCsv).collect(Collectors.joining());
    }

    @Override
    public void update(Observable o, Object arg) {
        notifyObservers();
    }
}
