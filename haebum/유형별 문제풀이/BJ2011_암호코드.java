/*
    2 5 1 1 4
    2 5 11 4
    2 5 1 14
    25 1 1 4
    25 11 4
    25 1 14

    dp[현재자리] = 경우의수

    dp[1] = 1 (2)
    dp[2] = 2 (2 5, 25)
    dp[3] = 2 (2 5 1, 25 1) -예외조건에 의해 다른결과가 나옴
    dp[4] = 4 (2 5 1 1, 2 5 11, 25 1 1,25 11)
    dp[5] = 6 (2 5 1 1 4, 2 5 1 14, 2 5 11 4, 25 1 1 4, 25 1 14, 25 11 4)

    dp[n] = dp[n-1] + dp[n-2]

    맨 첫번째가 0 이면 0출력
    현재자리수가 0이면 앞의 숫자 확인
    앞의숫자가 1 또는 2면 1개의 해석만 가능하므로 
    dp[n] = dp[n-2]
    그 외의 숫자일 경우 0 출력
    
    현재 숫자 앞이 0또는 3~9일 경우
    dp[n] = dp[n-1]

    현재 숫자 앞이 1,2 일경우
    dp[n] = dp[n-2] + dp[n-1]
*/

import java.util.*;
import java.io.*;

public class BJ2011_암호코드 {

    public static int length,div;
    public static Long[] dp;
    public static String scode;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        div = 1_000_000;
        scode = br.readLine();
        length = scode.length();
        dp = new Long[length+1];
        
        // 첫번째 숫자 0 출력
        if(scode.charAt(0) == '0'){
            bw.write(0+"\n");
        }else{
            // 초기 설정
            dp[0] = dp[1] = 1l;
            bottomUp();
            // 안구해졌어도 0 출력
            if(dp[length] == null){
                bw.write(0+"\n");
            }else{
                bw.write(dp[length] +"\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static void bottomUp(){
        for(int i = 2; i <= length; i++){
            char cur = scode.charAt(i-1);
            char prev = scode.charAt(i-2);
            // 현재 숫자 0일때
            if(cur == '0'){
                // 앞의 숫자 1,2만 가능
                if(prev == '1' || prev == '2'){
                    dp[i] = dp[i-2];
                }else{
                    break;
                }
            }else{
                // 앞의숫자+현재숫자 = 2자리 수 확인 
                long temp = (prev-'0')*10 + (cur-'0');
                // 26아래 && 앞의 숫자가 0이 아닐 시에만
                if(temp <= 26 && prev != '0'){
                    dp[i] = (dp[i-1] + dp[i-2])%div;
                // 그외의 숫자이면 이전 경우의수랑 동일
                }else{
                    dp[i] = dp[i-1];
                }
            }
        }
    }
}
