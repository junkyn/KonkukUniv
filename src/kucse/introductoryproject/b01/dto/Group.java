package kucse.introductoryproject.b01.dto;

import kucse.introductoryproject.b01.observer.Observable;
import kucse.introductoryproject.b01.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static kucse.introductoryproject.b01.Main.scanner;

public class Group implements Observable {
    private static final String GENERATE_CODE_FROM = "23456789QWERTYUPASDFGHJKLZXCVBNM";
    private static final String ALPHANUMERICS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random random = new Random();
    private final List<Observer> observers = new ArrayList<>();
    private String name;
    private int tag;
    private String id;
    private String code;

    private Group() {

    }

    public Group(String name) {
        this.name = name;

        // Generate Tag
        this.tag = random.nextInt(1000, 10000);

        // Generate ID
        do {
            StringBuilder sb = new StringBuilder("$");
            while (sb.length() < 9) {
                sb.append(ALPHANUMERICS.charAt(random.nextInt(ALPHANUMERICS.length())));
            }
            id = sb.toString();
        } while (false);

        // Generate Code
        regenerateCode();
    }

    public void setName() {
        do System.out.print("이름을 입력하세요\n> ");
        while (!validateName(scanner.nextLine().trim()));
        notifyObservers();
    }

    public boolean validateName(String name) {
        return !name.contains("[\t\n]");
    }

    public void regenerateCode() {
        Random random = new Random();
        do {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < 5) {
                sb.append(GENERATE_CODE_FROM.charAt(random.nextInt(GENERATE_CODE_FROM.length())));
            }
            code = sb.toString();
        } while (false);

    }

    public String getName() {
        return name;
    }

    public int getTag() {
        return tag;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String toCsv() {
        return name + "\t" + tag + "\t" + id + "\t" + code;
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
}
