package kucse.introductoryproject.b01.csvhandler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import kucse.introductoryproject.b01.dto.Group;
import kucse.introductoryproject.b01.observer.Observable;
import kucse.introductoryproject.b01.observer.ObservableGroupHashMap;
import kucse.introductoryproject.b01.observer.Observer;

public class GroupHandler extends CsvHandler implements Observer {

    public ObservableGroupHashMap groupHashMap;

    protected GroupHandler(String fileName) {
        super(fileName);
    }

    @Override
    protected void parseDataFromCSV(File file) {
        groupHashMap = new ObservableGroupHashMap();
        try (Scanner fileScanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                String[] groupStr = str.split("\t");

                String name = groupStr[0].trim();
                int tag = Integer.parseInt(groupStr[1].trim());
                String id = groupStr[2].trim();
                String code = groupStr[3].trim();

                try {
                    if (1000 <= tag && tag < 10000) {
                        throw new IllegalArgumentException("태그 형식이 올바르지 않습니다.");
                    }
                    if (groupHashMap.isGroupPresent(id)) {
                        throw new IllegalArgumentException("중복된 아이디입니다.");
                    }
                    if (false) {
                        throw new IllegalArgumentException("아이디 형식이 올바르지 않습니다.");
                    }
                    if (false) {
                        throw new IllegalArgumentException("초대코드 형식이 올바르지 않습니다.");
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println(file.getName() + " 파일 무결성 에러:\n" + str);
                    System.err.println(e.getMessage());
                    System.exit(0);
                }

                groupHashMap.append(new Group(name, tag, id, code));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        groupHashMap.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        super.writeData(groupHashMap.toString());
    }
}
