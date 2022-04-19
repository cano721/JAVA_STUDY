package studyGroup.april.april20;


/*

dp문제



*/

import java.util.*;
import java.lang.*;
import java.io.*;

public class LCS9251 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int n = s1.length();
        int m = s2.length();

        int[][] board = new int[n+1][m+1];

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(s1.charAt(i) == s2.charAt(j))
                {
                    board[i+1][j+1] = board[i][j] + 1;
                }
                else
                {
                    board[i + 1][j + 1] = Math.max(board[i + 1][j], board[i][j + 1]);
                }
            }
        }

        System.out.println(board[n][m]);






    }
}
