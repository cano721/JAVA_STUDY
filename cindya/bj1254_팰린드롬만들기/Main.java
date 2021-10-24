package cindya.bj1254_팰린드롬만들기;

import java.io.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int answer = 0;

        for(int i = (int)Math.ceil(s.length() / 2.0); i < s.length(); i++){ // i = s의 길이 반(길이가 홀수일 경우 + 1)
            String front = s.substring(0, i); // i 기준으로 앞쪽
            String back = new StringBuffer(s.substring(i)).reverse().toString(); // i 기준 뒤쪽

            if(Pattern.matches("^.*" + back + ".?$", front)){ // regex로 팰린드롬 가능한지 확인
                answer = front.length() * 2; // front를 뒤집어 붙여야 팰린드롬이 되므로 front 길이 * 2
                if(Pattern.matches("^.*" + back + ".$", front)) answer--; // 가운데 문자 기준으로 팰린드롬일 경우 -1
                break;
            }
        }
        if(answer == 0) answer = (s.length() * 2) - 1; // 문자 내부에서 팰린드롬이 전혀 없는 경우
        System.out.println(answer);

        br.close();
    }
}
