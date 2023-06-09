package kucse.introductoryproject.b01.dto;

import static kucse.introductoryproject.b01.Main.scanner;
import static kucse.introductoryproject.b01.utils.StringUtil.ALPHANUMERICS;
import static kucse.introductoryproject.b01.utils.StringUtil.GENERATE_CODE_FROM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import kucse.introductoryproject.b01.csvhandler.GroupHandler;
import kucse.introductoryproject.b01.observer.Observable;
import kucse.introductoryproject.b01.observer.Observer;
import kucse.introductoryproject.b01.utils.StringUtil;

public class Group implements Observable {

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
        do {
            this.tag = random.nextInt(1000, 10000);
        } while (GroupHandler.getInstance().groupHashMap.isGroupPresent(this.name, this.tag));

        // Generate ID
        do {
            StringBuilder sb = new StringBuilder("$");
            while (sb.length() < 9) {
                sb.append(ALPHANUMERICS.charAt(random.nextInt(ALPHANUMERICS.length())));
            }
            id = sb.toString();
        } while (GroupHandler.getInstance().groupHashMap.isGroupPresent(id));

        // Generate Code
        generateCode();
    }

    public Group(String name, int tag, String id, String code) {
        this.name = name;
        this.tag = tag;
        this.id = id;
        this.code = code;
    }

    public void setName() {
        do {
            System.out.print("이름을 입력하세요\n> ");
        }
        while (!validateName(scanner.nextLine().trim()));
        notifyObservers();
    }

    public boolean validateName(String name) {
        if (name.contains("[\t\n]")) {
            return false;
        }
        this.name = name;
        return true;
    }

    private void generateCode() {
        Random random = new Random();
        StringBuilder sb;
        do {
            sb = new StringBuilder();
            while (sb.length() < 5) {
                sb.append(GENERATE_CODE_FROM.charAt(random.nextInt(GENERATE_CODE_FROM.length())));
            }
        } while (GroupHandler.getInstance().groupHashMap.isCodeDuplicated(sb.toString()));
        code = sb.toString();
    }

    public void regenerateCode() {
        generateCode();
        notifyObservers();
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

    public static boolean isTagValid(int tag) {
        return 1000 <= tag && tag < 10000;
    }

    public static boolean isIdValid(String id) {
        if (id.length() != 9) {
            return false;
        }

        if (id.charAt(0) != '$') {
            return false;
        }

        return StringUtil.isAlphanumeric(id.substring(1));
    }

    public static boolean isCodeValid(String code) {
        if (code.length() != 5) {
            return false;
        }

        return code.matches("^[2-9A-HJ-NP-Z]+$");

    }

    public String toCsv() {
        return name + "\t" + tag + "\t" + id + "\t" + code + "\n";
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
