/*
   1차원 dp

   2칸 뛴것과 1칸 뛴것으로 n칸에 온것을 확인

   2칸 뛴것은 2칸전의 최대값에서 온것(dp[n-2])

   1칸 뛴것은 3칸은 뛸수 없으니 2칸전은 안밟았어야함.
   3칸전의 최대값과 전의 값을 더한곳에서 올라온것!(dp[n-3] + arr[n-1])

   이를 바탕으로 점화식 생성
   dp[n] = Math.max(dp[n-2],dp[n-3]+arr[n-1]) + arr[n];

    
*/

import java.util.*;
import java.io.*;

public class BJ2579_계단오르기 {

    public static Integer[] dp = new Integer[301];
    public static int [] arr = new int[301];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 배열 넣기
        int n = Integer.parseInt(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }


        // 초기값 넣기
        dp[0] = 0;
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        topdown(n);
        // 마지막 값 중 최대값 출력
        int answer = dp[n];
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    //탑다운방식
    public static int topdown(int n){
        // 이미 구한적이 있다면 구한것을 반환
        if(dp[n] != null){
            return dp[n];
        }
        for(int j = 3; j <= n; j++){
            dp[n] = Math.max(topdown(n-2),topdown(n-3)+arr[n-1]) + arr[n];
        }
        return dp[n];
    }

    //바텀업방식
    public static void bottomUp(int n){
        for(int i = 3; i <= n; i++){
            dp[i] = Math.max(dp[i-2],dp[i-3]+arr[i-1]) + arr[i];
        }
    }
}
