package BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1254_팰린드롬만들기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.print(solution(str));
    }
    public static int solution(String str) { // 글자수 만큼 반복
        for(int i=0; i<str.length(); i++) {
            if(isPalindrome(str, i, str.length()-1))
                return i+str.length();
        }
        return 1;
    }
    public static boolean isPalindrome(String string, int s, int e) {
        while (s < e) {
            if (string.charAt(s++) != string.charAt(e--)) // 뒤에서 시작한 인덱스와 앞에서 시작한 인덱스가 같은지 비교
                return false;
        }
        return true;
    }
}
