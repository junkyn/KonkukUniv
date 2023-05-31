package kucse.introductoryproject.b01.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kucse.introductoryproject.b01.dto.Group;

public class ObservableGroupHashMap extends HashMap<String, Group> implements Observable, Observer {

    private final List<Observer> observers = new ArrayList<>();

    public Group append(Group value) {
        Group added = super.put(value.getId(), value);
        value.addObserver(this);
        notifyObservers();
        return added;
    }

    public boolean isGroupPresent(String id) {
        return this.containsKey(id);
    }

    public boolean isGroupPresent(String name, int tag) {
        return this.values().stream()
            .anyMatch(it -> it.getName().equals(name) && it.getTag() == tag);
    }

    public Group getGroupByNameAndTag(String name, int tag) {
        return this.values().stream().filter(it -> it.getName().equals(name) && it.getTag() == tag)
            .findFirst().orElse(null);
    }

    public Group getGroupById(String id) {
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
}
