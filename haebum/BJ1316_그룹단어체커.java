/*
    동일한 알파벳이 연속해서 나오는건 1개로 침.
    그 이후에 나왔던 알파벳이 또 나오면 그건 그룹단어가 아님!

    알파벳 배열을 만들어서 체크하고
    기존 알파벳만 변수로 만들어서 체크하여 연속된건 무시하기.

*/


import java.util.*;
import java.io.*;

public class BJ1316_그룹단어체커 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        // 정답을 단어의 개수로 지정
        int answer = n;

        // 단어 돌기
        for(int i = 0; i < n; i++){

            //알파벳 수 만큼 배열생성
            int[] arr = new int[26];

            //단어받기
            String alpa = br.readLine();

            //첫문자 체크
            char check = alpa.charAt(0);
            arr[check-97] = 1;

            //두번째문자부터 돌기
            for(int j = 1; j < alpa.length(); j++){
                //문자뽑기
                char c = alpa.charAt(j);

                //기존 문자랑 동일할경우는 다음 포문 돌기
                if(check == c) continue;

                //아니면 체크문자변경
                check = c;

                //기존에 나왔던 문자면
                if(arr[c-97] == 1){
                    //정답 -1 포문 탈출
                    answer--;
                    break;
                }
                //기존에 안나왔으면 나온걸로 배열에 체크
                else arr[c-97] = 1;
            }
        }
        System.out.println(answer);
        
        
    }

}
