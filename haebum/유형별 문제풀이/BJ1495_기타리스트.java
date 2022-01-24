/*
    바텀업으로 노래 리스트 돌리기

    dp[현재노래][볼륨크기] = 가능여부

    곡과 볼륨크기를 돌아가며

    이전 곡에서의 해당 볼륨크기가 있었다면

    해당조건안에 해당하면(0 <= , <= m)
    현재곡에 더한값과 뺀 볼륨값으로 저장
    [현재곡][이전볼륨+현재곡볼륨] = 1
    [현재곡][이전볼륨-현재곡볼륨] = 1
    
    

*/

import java.util.*;
import java.io.*;

public class BJ1495_기타리스트 {

    public static long[][] dp;
    public static int[] arr;
    public static int n,s,m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new long[n+1][m+1];
        dp[0][s]++;

        arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bottomUp();
        for(int i = m; i >= 0; i--){
            if(dp[n][i] != 0){
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
    //바텀업방식
    public static void bottomUp(){
        for(int i = 1; i <= n; i++){
            for( int j = 0; j <= m; j++){
                // 해당 가능한게 없으면
                if(dp[i-1][j] == 0) continue;
                // 현재곡 더한게 m보다 작거나 같으면
                if(j + arr[i] <= m){
                    dp[i][j+arr[i]] = 1;
                }
                // 현재곡 볼륨뺀게 0과 같거나 크면
                if(j - arr[i] >= 0){
                    dp[i][j-arr[i]] = 1;
                }
            }
        }
    }
}