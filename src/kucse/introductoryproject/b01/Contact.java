package kucse.introductoryproject.b01;

public class Contact {
    private String name;
    private String phone;
    private String address;
    private String birthday;
    private int year,month,day;
    private String memo;

    public Contact() {

    }
    public Contact(String name, String phone, String address, String birthday, String memo) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.memo = memo;
    }

    public boolean setName(String name) {
        if (name.isBlank())
            System.out.println("이름은 필수 입력입니다");
        else if (name.chars().allMatch(Character::isDigit))
            System.out.println("정수로만 이루어진 이름은 사용할 수 없습니다");
        else if (name.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.name = name;
            return true;
        }

        return false;
    }

    public boolean setPhone(String phone) {
        if (phone.isBlank())
            System.out.println("전화번호는 필수 입력입니다");
        else if (phone.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else if (ContactUtil.getInstance().isPhoneDuplicated(phone))
            System.out.println("이미 존재하는 전화번호입니다");
        else {
            this.phone = phone;
            return true;
        }

        return false;
    }

    public boolean setAddress(String address) {
        if (address.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            this.address = address;
            return true;
        }

        return false;
    }

    public boolean setBirthday(String birthday) {
        if (birthday.contains("\t"))
            System.out.println("탭(tab)은 사용하실 수 없습니다");
        else {
            if(splitBirthday(birthday)){
                if(month>=10){
                    if(day>=10) this.birthday = year+"-"+month+"-"+day;
                    else this.birthday = year+"-"+month+"-0"+day;
                }
                else{
                    if(day>=10) this.birthday = year+"-0"+month+"-"+day;
                    else this.birthday = year+"-0"+month+"-0"+day;
                }
                return true;
            }

            else
                return false;

        }

        return false;
    }

    public boolean setMemo(String memo) {
        this.memo = memo;

        return true;
    }
    public boolean splitBirthday(String birth){
        String[] birthsplitted;
        try{
            System.out.println(birth.length());
            if(birth.length()==8){
                year = Integer.parseInt(birth.substring(0,4));
                month = Integer.parseInt(birth.substring(4,6));
                day = Integer.parseInt(birth.substring(6,8));
            }
            else if(birth.length()==10){
                birthsplitted = birth.split("/|.|-");
                // 이거 왜 안되는데!!!!!!!!!!!!!!!
                year = Integer.parseInt(birthsplitted[0]);
                month = Integer.parseInt(birthsplitted[1]);
                day = Integer.parseInt(birthsplitted[2]);
            }
            else{
                //throw(new Exception());
            }

            if(year < 1000 || year > 2999){
                System.out.println("존재하지 않는 날짜입니다. 다시 입력해주세요");
                return false;}

            if(month< 1 || month > 12) {
                System.out.println("존재하지 않는 날짜입니다. 다시 입력해주세요");
                return false;}
            if((year % 400==0 || (year%4==0 && year%100!=0))&& month == 2){
                if(day<1 || day > 29) {
                    System.out.println("존재하지 않는 날짜입니다. 다시 입력해주세요");
                    return false;}
            }else if(month == 2){
                if(day<1 || day > 28) {
                    System.out.println("존재하지 않는 날짜입니다. 다시 입력해주세요");
                    return false;}
            }else{
                switch(month){
                    case 1: case 3: case 5: case 7: case 8: case 10: case 12: if(day<1 || day>31) {
                        System.out.println("존재하지 않는 날짜입니다. 다시 입력해주세요");
                        return false;
                    }break;
                    default : if(day < 1 || day>30){
                        System.out.println("존재하지 않는 날짜입니다. 다시 입력해주세요");
                        return false;
                    }break;

                }
            }
            return true;
        }catch(NumberFormatException e) {
            System.out.println("옳지 않은 입력입니다. 다시 입력해주세요");
            return false;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("옳지 않은 입력입니다. 다시 입력해주세요");
            return false;
        }


    }

    public String getPhone(){
        return this.phone;
    }
    public String getName(){
        return this.name;
    }
    public String getAddress() { return address; }
    public String getBirthday() { return birthday; }
    public String getMemo() { return memo; }
    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    public String toCsv() {
        return name + "\t" + phone + "\t" + address + "\t" + birthday + "\t" + memo + "\n";
    }
}
