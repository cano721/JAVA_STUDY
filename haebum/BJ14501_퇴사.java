/*
    n범위 15

    1. 백트래킹으로 상담하거나 안하거나 골라서 넘어가기
    넘어갈때 금액과 기간에따른 날짜로 넘어가기

    2. DP로 저장하며 풀기


*/


import java.util.*;
import java.io.*;

public class BJ14501_퇴사 {

    public static int n,maxProfit;
    public static int[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 퇴사날짜-1
        n = Integer.parseInt(br.readLine());
        
        // 배열크기 생성
        arr = new int[n][2];
        
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int day = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            arr[i][0] = day;
            arr[i][1] = money;
        }


        // solve(0,0);
        maxProfit = dp();
        System.out.println(maxProfit);
    }

    // 백트래킹
    public static void solve(int day,int profit){
        // 날짜를 넘기면 그냥 종료
        if(day > n) return;

        // 퇴사일이면 현재 이익이 최대치인지 확인
        if(day == n){
            maxProfit = Math.max(maxProfit, profit);
            return;
        }

        // 상담을 했을때
        solve(day+arr[day][0],profit+arr[day][1]);
        // 상담을 안했을때
        solve(day+1,profit);
    }


    // dp
    public static int dp(){
        int[] dpArr = new int[n+1];


        for(int i = 0; i < n; i++){
            
            // 범위를 벗어나지 않았을 경우
            if(i + arr[i][0] <= n){
                // 상담한 경우
                dpArr[i+arr[i][0]] = Math.max(dpArr[i] + arr[i][1],dpArr[i+arr[i][0]]);
            }
            // 상담안한 경우
            dpArr[i+1] = Math.max(dpArr[i+1],dpArr[i]);
        }

        return dpArr[n];
    }

}
