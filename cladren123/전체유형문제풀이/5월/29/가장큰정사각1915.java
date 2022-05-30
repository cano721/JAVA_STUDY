package studyGroup.may.may29;

import java.util.*;
import java.lang.*;
import java.io.*;


public class 가장큰정사각1915 {

    static int n,m;
    static int[][] board;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        dp = new int[n+1][m+1];

        for(int i = 1; i <= n; i++)
        {
            String one = br.readLine();
            for(int j = 1; j <= m; j++)
            {
                dp[i][j] = board[i][j] = (int)(one.charAt(j-1) - '0');
            }
        }

        int answer = 0;

        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= m; j++)
            {
                if(board[i][j] == 1)
                {
                    dp[i][j] = check(i, j);
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        System.out.println(answer * answer);

        br.close();

    }

    public static int check(int y, int x)
    {
        int one = dp[y-1][x-1];
        int two = dp[y][x-1];
        int thr = dp[y-1][x];

        if(one == 0 || two == 0 || thr == 0) return 1;

        return Math.min(one, Math.min(two, thr)) + 1;
    }



}
