package 백트래킹;

import java.util.Scanner;

public class Boj1182_부분수열의합 {
    static int N, S, ans;
    static int[] num;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        S = in.nextInt();

        num = new int[N];
        for(int i=0; i<num.length; i++) {
            num[i] = in.nextInt();
        }

        dfs(0, 0);

        // 공집합인 경우에도 조건에 맞다고 처리하고 개수를 +1 하므로
        // S가 0인 경우엔 개수에서 -1을 해줌
        if(S == 0) ans--;
        System.out.println(ans);
    }

    public static void dfs(int sum, int idx) {
        if(idx == N) {
            if(sum == S) ans++;
            return;
        }

        dfs(sum+num[idx], idx+1); // 해당 인덱스 포함
        dfs(sum, idx+1); // 해당 인덱스 포함 X
    }
}
