package kucse.introductoryproject.b01.utils;

public class StringUtil {

    private static final String[] CONSONANTS = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ",
        "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

    public static String getHangulOnly(String str) {
        return str.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣]", "");
    }

    public static boolean isHangul(char str) {
        return '가' <= str && str <= '힣';
    }

    public static boolean isNumber(String str) {
        return str.matches("^[0-9]+$");
    }

    public static String getNumbersOnly(String str) { // 정수만 추출
        return str.replaceAll("[^0-9]", "");
    }

    public static String toConsonants(String str) {
        StringBuilder consonants = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (isHangul(str.charAt(i))) {
                consonants.append(CONSONANTS[((str.charAt(i) - 0XAC00) / 28 / 21)]);
            } else {
                consonants.append(str.charAt(i));
            }
        }

        return consonants.toString();
    }

    public static int ordinalIndexOf(String str, String substr, int n) {
        int pos = str.indexOf(substr);
        while (--n > 0 && pos != -1) {
            pos = str.indexOf(substr, pos + 1);
        }
        return pos;
    }
}
