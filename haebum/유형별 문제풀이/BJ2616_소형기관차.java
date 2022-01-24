
/**
 * 누적합 풀이
 * 
 * 누적합 배열을 만들고,
 * 3번의 선택을 함.(n과 m 사용 예정) - 실패 50만 * 50만 *50만.. 문제 있음.
 * 
 * nCk방법 중 시간복잡도를 줄일 수 있는 방법.
 * 1. n과 m n^3 (실패)
 * 2. dp[n][k] 풀이
 *  dp[소형기관차의 수][선택한 객차들의 마지막idx] = 현재 손님 수
 *  
 * 
 * 그때 최대 운용 수 출력
 */
import java.util.*;
import java.io.*;

public class BJ2616_소형기관차 {

    public static int[] sumArr;
    public static int[][] dp;
    public static int n,carriage,answer;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        
        dp = new int[4][n+1];

        st = new StringTokenizer(br.readLine());
        sumArr = new int[n+1];
        
        for(int i = 1; i <= n; i++){
            int person = Integer.parseInt(st.nextToken());
            sumArr[i] = sumArr[i-1]+ person;
        }
        
        carriage = Integer.parseInt(br.readLine());
        dp();
        System.out.println(dp[3][n]);
    }



    // dp풀이 n*3으로 성공
    public static void dp(){
        // 현재 소형기관차
        for(int i = 1; i < 4; i++){
            // 고른 객차들의 마지막 인덱스
            for(int j = carriage; j <= n; j++){
                // 이전에 고른 것과 현재 고른 것 비교
                dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j-carriage] + sumArr[j]-sumArr[j-carriage]);
            }
        }
    }


    // n과 m형태의 재귀풀이( 시간초과로 실패)
    // public static void choice(int stage,int pnum,int curSum){
    //     // 3개 다 골랐을때 현재 정답보다 크면 변경
    //     if(stage == 3){
    //         answer = Math.max(answer,curSum);
    //         return;
    //     }

    //     // 객차 고르기
    //     for(int i = pnum; i <= n-carriage+1; i++){
    //         // 다음소형기관차,현재고른객차 다음꺼부터 시작, 현재고른객차의 손님수
    //         choice(stage+1,i+carriage,curSum+sumArr[i+carriage-1]-sumArr[i-1]);
    //     }
    // }
}
