package 구현;

import java.util.Scanner;

public class Boj1316_그룹단어체커 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int cnt = T;
        String text=  "";

        for (int i = 0 ; i < T ; i ++) {
            text = sc.next();
            int temp = -1; //직전 문자의 인덱스
            int index = -1; //배열의 인덱스
            boolean[] array = new boolean[26];
            for (int j = 0 ; j < text.length(); j ++) {
                if(text.length() == 1 ) { // 1글자면 반복문을 탈출
                    break;
                }

                index = (int) text.charAt(j) - (int)'a';  // 배열의 인덱스에 맞게 조정

                if( index != temp ) { // 새로운 문자가 나오면
                    temp = index;
                    if(array[temp] == true) { // 새로 검사하는 문자를 이전에 검사했으면
                        cnt--;
                        break;
                    }
                    array[temp] = true;  // 인덱스에 true 저장
                }
            }
        }
        System.out.println(cnt);
    }

}
