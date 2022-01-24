/*
    n의 범위가 백만까지고 생성자는 n보다 작으므로 브루트포스로 가능
    1부터 돌면서 n전까지 돌면서 분해합 만들기

    분해합이 n일경우 i가 정답

*/


import java.util.*;
import java.io.*;

public class BJ2231_분해합 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //생성자 없을경우를 확인하기위한 boolean 변수
        boolean check = true;

        for(int i = 1; i < n; i++){
            // 분해합 sum
            int sum = i;

            // 임시숫자(각자리 만들용도)
            int temp = i;
            while(temp > 0){
                //자리수 더하기
                sum += temp%10;
                //다음자리수 구하기
                temp/= 10;
            }

            //원하는 n이 구해졌으면 탈출
            if(sum == n){
                System.out.println(i);
                check = false;
                break;
            }
        }
        if(check) System.out.println(0);

    }
}