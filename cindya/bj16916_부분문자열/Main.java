package cindya.bj16916_부분문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(""), p = br.readLine().split("");
        int[] pi = new int[p.length];
        int j = 0, result = 0;

        // p의 부분 문자열의 같은 접두사와 접미사 길이를 pi에 저장
        // i는 접미사 포인터
        for(int i = 1; i < p.length; i++){
            // j는 접두사 포인터
            while (j > 0 & !p[i].equals(p[j])) // p[i]와 p[j]가 다르면
                j = pi[j - 1]; // j는 접두사와 접미사가 같았던 때의 접두사 뒤의 인덱스로 돌아감
            if(p[i].equals(p[j])) // 접두사와 접미가사 같으면
                pi[i] = ++j; // 길이가 i일 때 같은 접미사와 접두사의 길이(j + 1)를 저장하고 j 증가
        }

        // s에서 p 찾기
        j = 0;
        // i는 s의 포인터
        for(int i = 0; i < s.length; i++){
            // j는 p의 포인터
            while (j > 0 && !s[i].equals(p[j])) // s의 i번째 글자와 p의 j번째 글자가 다르면
                j = pi[j - 1]; // j번째 글자가 i와 같을 때까지 앞으로 감
            if(s[i].equals(p[j]) && j++ == (p.length - 1)){ // p의 모든 문자열이 s에 포함되어있으면
                result = 1; // result를 1로 바꾸고
                break; // 탐색 중단
            }
        }
        System.out.println(result);
        br.close();
    }
}
