package kucse.introductoryproject.b01.observer;

public interface Observable {
    void notifyObservers();
    void addObserver(Observer o);

    void removeObserver(Object o);
}

