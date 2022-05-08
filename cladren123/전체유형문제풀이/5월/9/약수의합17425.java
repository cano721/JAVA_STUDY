package studyGroup.may.may9;


import java.util.*;
import java.io.*;


public class 약수의합17425 {

    static int t;
    static long[] board;
    static long[] boardSum;

    public static void main(String[] args) throws IOException {

        // fx
        board = new long[1000001];
        Arrays.fill(board, 1);
        for(int i = 2; i < board.length; i++)
        {
            for(int j = 1; j * i < board.length; j++)
            {
                board[i*j] += i;
            }
        }

        // gx
        boardSum = new long[board.length];
        for(int i = 1; i < board.length; i++)
        {
            boardSum[i] += board[i] + boardSum[i-1];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());


        // 시간 감소
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < t; i++) {
            int number = Integer.parseInt(br.readLine());
            sb.append(boardSum[number]).append("\n");
        }
        System.out.println(sb);


    }







}
