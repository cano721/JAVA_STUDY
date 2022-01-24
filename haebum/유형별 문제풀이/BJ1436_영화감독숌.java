/*
    n은 10000보다 작으므로
    10000666 붙이더라도 1초보다 작음
    완탐으로 풀 예정

    666부터 시작으로 1씩 더해가면서
    그 숫자를 string으로 변환후 666이 있는지 체크할 예정
*/


import java.util.*;
import java.io.*;

public class BJ1436_영화감독숌 {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        int num = 666;
        int cnt = 0;

        while(true){
            if(String.valueOf(num).contains("666")) cnt++;

            if(cnt == n){
                System.out.println(num);
                break;
            }
            num++;
        }
    }

    
}