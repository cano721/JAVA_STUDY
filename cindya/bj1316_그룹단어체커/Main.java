package cindya.bj1316_그룹단어체커;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int answer = 0;

        MainLoop:
        for(int n = 0; n < num; n++){
            String s = br.readLine();
            Set<Character> characters = new HashSet<>(); // 나온 그룹들 저장
            char c = s.charAt(0); // 앞 문자와 같은 그룹인지 확인하기 위한 변수
            for(int i = 1; i < s.length(); i++){
                char next = s.charAt(i);
                if(c != next){ // 그룹이 바뀌는 지점
                    if(characters.contains(next)) // 이미 앞에 나온 단어일 경우
                        continue MainLoop; // 다음 단어로 넘어감
                    characters.add(c); // 새로운 단어일 경우, c를 characters에 추가하고
                    c = next; // next 값을 c에 대입
                }
            }
            answer++; // for 문이 제대로 돌았을 때만 answer 증가
        }

        System.out.println(answer);

        br.close();
    }
}
