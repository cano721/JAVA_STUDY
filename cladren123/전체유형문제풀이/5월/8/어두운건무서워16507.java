package studyGroup.may.may8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 어두운건무서워16507 {

    static int r;
    static int c;
    static int q;
    static int[][] board;
    static int[][] boardSum;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        board = new int[r+1][c+1];
        boardSum = new int[r+1][c+1];

        for(int i = 1; i < r+1; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < c+1; j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < r+1; i++)
        {
            for(int j = 1; j < c+1; j++)
            {
                boardSum[i][j] = board[i][j] + boardSum[i-1][j] + boardSum[i][j-1] - boardSum[i-1][j-1];
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();

        for(int i = 0; i < q; i++)
        {
            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            answer.add(bright(r1,c1,r2,c2));
        }

        for(Integer one : answer)
        {
            System.out.println(one);
        }
    }

    public static int bright(int r1, int c1, int r2, int c2)
    {
        int result = 0;
        result = boardSum[r2][c2] - boardSum[r2][c1-1] - boardSum[r1-1][c2] + boardSum[r1-1][c1-1];
        int allPart = (r2-r1+1) * (c2-c1+1);
        result = result / allPart;
        return result;
    }

}
