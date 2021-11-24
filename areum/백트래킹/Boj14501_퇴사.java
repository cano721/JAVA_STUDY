package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14501_퇴사 {
    static int N, answer=0;
    static int[] T,P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        T = new int[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        System.out.println(answer);

    }

    static void dfs(int index, int value) {
        if(index>=N) {
            answer = Math.max(answer, value);
            return;
        }

        // 해당 index를 포함
        if (index + T[index] <= N) {
            dfs(index + T[index], value + P[index]);
        } else {
            dfs(index + T[index], value); // n을 넘어가면 value 합치지 않음
        }

        dfs(index+1, value); // 해당 index 미포함
    }
}
