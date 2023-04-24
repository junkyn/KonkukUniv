package kucse.introductoryproject.b01.observer;

import kucse.introductoryproject.b01.dto.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ObservableUserInfoHashMap extends HashMap<String, UserInfo> implements Observable, Observer {
    private final List<Observer> observers = new ArrayList<>();

    public UserInfo append(UserInfo value) {
        UserInfo added = super.put(value.getId(), value);
        value.addObserver(this);
        notifyObservers();
        return added;
    }

    public boolean isIdPresent(String id) {
        return this.containsKey(id);
    }

    public boolean isUserInfoValid(String id, String password) {
        return this.get(id).isMatchingPassword(password);
    }

    public UserInfo getUserInfoById(String id) {
        return this.get(id);
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

    @Override
    public void update(Observable o, Object arg) {
        notifyObservers();
    }

    @Override
    public String toString() {
        return this.values().stream().map(UserInfo::toCsv).collect(Collectors.joining());
    }
}
