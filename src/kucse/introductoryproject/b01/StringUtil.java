package kucse.introductoryproject.b01;

public class StringUtil {
    private static final String[] CONSONANTS = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

    public static String getHangulOnly(String str) {
        StringBuilder hanguls = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.toString(str.charAt(i)).matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
                hanguls.append(str.charAt(i));
        }

        return hanguls.toString();
    }

    public static String getNumbersOnly(String str) { // 정수만 추출
        return str.replaceAll("[^0-9]","");
    }

    public static String toConsonants(String str) {
        StringBuilder consonants = new StringBuilder();
        str = getHangulOnly(str);
        for (int i = 0; i < str.length(); i++) {
            consonants.append(CONSONANTS[((str.charAt(i) - 0XAC00) / 28 / 21)]);
        }

        return consonants.toString();
    }
}
