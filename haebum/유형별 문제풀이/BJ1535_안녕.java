/*
    dp[현재사람][현재체력] = 현재기쁨
    
    사람들을 돌며 인사했을때랑 안햇을때 저장

    dp[0][100] = 0;
    1번 사람
    인사 -  dp[1][99] = 20;
    인사x - dp[1][100] = 0;
    2번 사람
    인사 -  dp[1][78] = 50; dp[1][79] = 30;
    ....

    현재 사람 인사했을때 체력이 0이하는 인사불가능
    dp[n][0~100] 중 최대기쁨 출력

    탑다운은 좀 귀찮으니 패스..

*/

import java.util.*;
import java.io.*;

public class BJ1535_안녕 {

    public static int n;
    public static int[][] dp;
    public static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        if(n != 0){
            dp = new int[n+1][101];
            arr = new int[n+1][2];
            // 초기값
            dp[0][100] = 0;
            
            // 잃는체력
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                arr[i][0] = Integer.parseInt(st1.nextToken());
            }

            // 얻는기쁨
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                arr[i][1] = Integer.parseInt(st2.nextToken());
            }
            bottomUp();

            int answer = 0;
            // 체력들 돌면서 최대 체력 담기
            for(int i = 1; i<= 100; i++){
                answer = Math.max(answer,dp[n][i]);
            }
            bw.write(answer + "\n");
        }else{
            bw.write(0 +"\n");
        }
        bw.flush();
        bw.close();
    }


    public static void bottomUp(){
        // 사람돌기
        for(int i = 1; i <= n; i++){
            // 체력확인
            for(int j = 1; j <= 100; j++){
                // 인사 안하기
                dp[i][j] = Math.max(dp[i][j],dp[i-1][j]);
                // 인사해도 체력이 남아있을 경우만
                if(j-arr[i][0] > 0){
                    dp[i][j-arr[i][0]] = Math.max(dp[i][j-arr[i][0]],dp[i-1][j] + arr[i][1]);
                }
            }
        }
    }
}

