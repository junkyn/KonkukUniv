package kucse.introductoryproject.b01;

public interface Observable {
    void notifyObservers();
    void addObserver(Observer o);

    void removeObserver(Object o);
}

