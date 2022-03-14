/**
 * 1. 트리 dp문제
 * 
 * 2. 어떤 오두막에서 다른 오두막까지 정상을 찍고 가는 가장 짧은 길의 개수 파악
 * 2-1). i 오두막에서 j정상 찍고 k 오두막 가는 방법.
 * 
 * 3. 위의 뜻은 i->1번 가는 짧은 방법과 1->k까지 가는 방법을 곱한 것.
 * 3-1) i랑 j는 다르며, i보다 j는 무조건 커야함.
 * 3-2) 1 .. 1 .. 2
 *      1 .. 1 .. 3
 *      ......
 *      7 .. 1 .. 8
 * 3-3) i ~ n번부터 1까지의 경로 + i+1 ~ n까찌 1번으로 가는 경로 
 * 3-4) i번에서 1번까지 모두 n-1번
 * 
 * 4. dp[n]은 n에서 1번오두막까지의 경로의 개수
 * 
 * 5. dfs를 돌면서 자식의 dp 구하기
 * 
 */

import java.io.*;
import java.util.*;

public class 등산마니아{

    static ArrayList<Integer>[] tree;
    static int[] dp;
    static int n;
    static long result = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            tree[i] = new ArrayList<>();
        }

        dp = new int[n+1];
        
        StringTokenizer st;
        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dp(1);
    }

    public static int dp(int now){
        dp[now] = 1;

        for(int next : tree[now]){
            if(dp[next] == 0){
                dp[now] += dp(next);
            }
        }

        if(now != 1){
            result += comb(n) - comb(n - dp[now]);
        }

        return dp[now];
    }

    public static long comb(int n){
        return (long)n * (long)(n-1)/2;
    }
}