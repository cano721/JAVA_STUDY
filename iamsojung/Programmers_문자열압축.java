package Feburary;

public class Programmers_문자열압축 {
    public int solution(String s) {
        int min = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            int compLeng = compression(s, i).length();
            min = Math.min(min, compLeng);
        }

        return min;
    }

    private String compression(String str, int i) {

        int count = 1;
        String compression = "";
        String pattern = "";

        for (int j = 0; j <= str.length() + i; j += i) {

            String nowStr;

            if (j >= str.length()) {
                nowStr = "";
            } else if (str.length() < j + i) {
                nowStr = str.substring(j);
            } else {
                nowStr = str.substring(j, j + i);
            }

            if (j != 0) {
                if (nowStr.equals(pattern)) {
                    count++;
                } else if (count >= 2) {
                    compression += count + pattern;
                    count = 1;
                } else {
                    compression += pattern;
                }
            }
            pattern = nowStr;
        }

        return compression;
    }


}
