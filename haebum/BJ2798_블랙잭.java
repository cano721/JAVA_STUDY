import java.util.*;
import java.io.*;

public class BJ2798_블랙잭 {
    public static void main(String[] args) throws IOException {
        
        // 입력값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문자 나누기
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        
        // 카드 개수
        int cardNum = Integer.parseInt(st.nextToken());
        // 딜러의숫자(원하는 카드 합)
        int wantNum = Integer.parseInt(st.nextToken());
        
        // 카드덱
        StringTokenizer st2 = new StringTokenizer(br.readLine()," ");

        // 덱 생성(배열)
        int[] arr = new int[cardNum];
        // 배열에 집어넣기
        for(int i = 0; i < cardNum; i++){
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        // 정렬(오름차순)
        Arrays.sort(arr);

        int answer = 0;

        // 3중포문을 통해 3개의 카드 선택
        // 중복 선택 안하기위해 조건을 다 별도로 설정
        // ex) int j = i+1; j < cardNum-1; j++
        stop:for(int i = 0; i < cardNum-2; i++){
            for(int j = i+1; j < cardNum-1; j++){
                for(int k = j+1; k < cardNum; k++){
                    // 세개의 카드합
                    int checkNum = arr[i]+arr[j]+arr[k];

                    // 카드합이 원하는 조건이면 다 중단
                    if(checkNum == wantNum){
                        answer = checkNum;
                        break stop;
                    // 원하는 조건보다 아래이면서 현재 저장되어있는 구한값보다 크면 변경
                    }else if(checkNum < wantNum && checkNum > answer){
                        answer = checkNum;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
