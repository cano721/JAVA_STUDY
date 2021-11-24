package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15650_N과M2 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        dfs(1, 0);
    }

    public static void dfs(int st, int cnt) {
        if(cnt == M) {
            for(int n : arr) {
                System.out.print(n + " ");
            }
            System.out.println();
            return;
        }

        for(int i=st; i<=N; i++) { // 숫자가 중복되지 않게 하기위해서 st 부터 시작
                arr[cnt] = i;
                dfs(i+1, cnt+1);
        }
    }
}
