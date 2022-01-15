package cindya.bj1701_cubeditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine(), sub;
        int len = s.length(), max = 0, k;

        // 문자열의 시작점 i
        for(int i = 0; i < len; i++){
            // 문자열 앞부분을 자름
            sub = s.substring(i, len);
            int[] pi = new int[sub.length()];
            k = 0;
            // 접두사와 접미사가 같은 부분을 구함
            for(int j = 1; j < sub.length(); j++){
                while (k > 0 && sub.charAt(j) != sub.charAt(k))
                    k = pi[k - 1];
                if(sub.charAt(j) == sub.charAt(k)) {
                    pi[j] = ++k;
                    max = Math.max(max, k); // 접미사와 접두사가 같다면 max와 그 길이 중 긴 것을 선택
                }
            }
        }
        System.out.println(max);
        br.close();
    }
}
