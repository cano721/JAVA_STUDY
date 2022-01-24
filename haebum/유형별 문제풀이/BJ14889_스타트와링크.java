/*
    n과m 문제 풀듯이
    절반의 인원을 뽑았을때
    각팀의 능력치 합계를 계산하여
    정답을 변경

    최대경우의수 20개중에 10개를 고르는거지만
    기존에 고른숫자의 위에서만 찾게 설정 했기때문에
    최대 O(20)임
    고른 후에 20*20으로 각 팀별 능력치를 합산하므로
    최대 시간복잡도는 20*20*20 = 2^3 *10^3
*/


import java.util.*;
import java.io.*;

public class BJ14889_스타트와링크 {

    //인원수
    public static int n;

    //능력치 차이 최소(초기 맥스값 지정)
    public static int answer = Integer.MAX_VALUE;
    
    // 능력치 배열
    public static int[][] stat;

    // 숫자 배열
    public static int[] num;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());

        num = new int[n];
        stat = new int[n][n];

        // 능령치 담기
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j <n; j++){
                stat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0,0);

        System.out.println(answer);
    }

    public static int solve(int stage,int pnum){

        // 다 골랐을때(절반씩 팀임)
        if(stage == n/2){
            // 능력치 합산 함수
            check();
            return 0;
        }
        
        // 숫자를 돌면서 고르기
        for(int i = pnum; i < n; i++){
            // 고르지 않은 사람일때
            if(num[i] == 0){
                // 팀설정
                num[i] = 1;
                // 다음 팀 고르러 가기
                solve(stage+1,i);
                // 초기화
                num[i] = 0;
            }
        }
        return 0;
    }

    public static void check(){

        // 1팀 합계
        int sum1 = 0;
        // 2팀 합계
        int sum2 = 0;

        // 사람들 돌면서 같은 팀이면 더하기
        for(int a = 0; a < n; a++){
            for(int b = a+1; b < n; b++){
                if(num[a] == 0 && num[b] == 0){
                    sum1 += stat[a][b];
                    sum1 += stat[b][a];
                }else if(num[a] == 1 && num[b] == 1){
                    sum2 += stat[a][b];
                    sum2 += stat[b][a];
                }
            }
        }

        // 최소값일 시 정답 변경
        answer = Math.min(answer,Math.abs(sum1-sum2));
    }
}

