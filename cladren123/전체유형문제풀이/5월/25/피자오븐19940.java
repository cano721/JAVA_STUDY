package studyGroup.may.may25;

import java.util.*;
import java.io.*;

public class 피자오븐19940 {

    static int testcase; // 테스트케이스의 갯수수
    static int time; // 설정해야하는 시간

    static int[] board;
    static count[] countList;

    static int[] d = {-1, 1, -10, 10, 60};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        testcase = Integer.parseInt(br.readLine());
        bfs();

        for(int i = 0; i < testcase; i++)
        {
            time = Integer.parseInt(br.readLine());
            int addh = time / 60;
            count c = countList[time%60];
            bw.write(addh + c.addh + " " + c.addt + " " + c.mint + " " + c.addo + " " + c.mino + "\n");
        }





        br.close();
        bw.close();
    }

    // 0분부터 60분까지의 결과를 저장
    public static void bfs()
    {
        board = new int[121];
        countList = new count[121];

        Arrays.fill(board, -1);
        board[0] = 0;
        countList[0] = new count(0, 0,0, 0, 0,0);

        Queue<count> que = new LinkedList<>();
        que.add(new count(0, 0,0, 0, 0, 0));

        while (!que.isEmpty())
        {
            count c = que.poll();

            for(int i = 0; i < 5; i++)
            {
                int nn = c.number + d[i];

                if(nn >= 0 && nn < 61)
                {
                    if(board[nn] == -1)
                    {
                        board[nn] = board[c.number] + 1;
                        if(i == 0) {
                            que.add(new count(nn, c.addh, c.addt, c.mint, c.addo, c.mino+1));
                            countList[nn] = new count(nn, c.addh,c.addt , c.mint, c.addo, c.mino+1);
                        }
                        else if(i == 1) {
                            que.add(new count(nn, c.addh,c.addt , c.mint, c.addo+1, c.mino));
                            countList[nn] = new count(nn, c.addh, c.addt , c.mint, c.addo+1, c.mino);
                        }
                        else if(i == 2) {
                            que.add(new count(nn, c.addh, c.addt, c.mint+1, c.addo, c.mino));
                            countList[nn] = new count(nn, c.addh,  c.addt, c.mint+1, c.addo, c.mino);
                        }
                        else if(i == 3) {
                            que.add(new count(nn, c.addh, c.addt+1, c.mint, c.addo, c.mino));
                            countList[nn] = new count(nn, c.addh, c.addt+1, c.mint, c.addo, c.mino);
                        }
                        else if(i == 4) {
                            que.add(new count(nn, c.addh+1, c.addt, c.mint, c.addo, c.mino));
                            countList[nn] = new count(nn, c.addh+1, c.addt, c.mint, c.addo, c.mino);
                        }
                    }
                }
            }
        }
    }

    public static class count
    {
        int number;
        int addh;
        int addt;
        int mint;
        int addo;
        int mino;

        count(int number, int addh, int addt, int mint, int addo, int mino)
        {
            this.number = number;
            this.addh = addh;
            this.addt = addt;
            this.mint = mint;
            this.addo = addo;
            this.mino = mino;
        }
    }

}
