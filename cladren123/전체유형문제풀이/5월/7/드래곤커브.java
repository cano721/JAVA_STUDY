package studyGroup.may.may7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 출저 : https://velog.io/@hammii/%EB%B0%B1%EC%A4%80-15685-%EB%93%9C%EB%9E%98%EA%B3%A4-%EC%BB%A4%EB%B8%8C-java

public class 드래곤커브 {

    static boolean[][] board = new boolean[101][101];
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};

    static int n;
    static int answer = 0;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragonCurve(x,y,d,g);
        }

        for(int i = 0; i < 100; i++)
        {
            for(int j = 0; j < 100; j++)
            {
                // 정사각형이면 정답 + 1;
                if(board[i][j] && board[i][j+1] && board[i+1][j] && board[i+1][j+1])
                {
                    answer++;
                }
            }
        }

        System.out.println(answer);


    }

    public static void dragonCurve(int x, int y, int d, int g)
    {
        List<Integer> dlist = new ArrayList<>();
        dlist.add(d);

        // 방향을 기입
        for(int i = 1; i <= g; i++)
        {
            // 세대는 기존의 것을 90도로 회전시킨 것을 다시 넣는 것
            for(int j = dlist.size()-1; j >= 0; j--)
            {
                dlist.add((dlist.get(j) + 1) % 4);
            }
        }

        // 드래곤커브가 지난간 길을 표시
        board[y][x] = true;
        for (Integer direction : dlist) {
            x += dx[direction];
            y += dy[direction];
            board[y][x] = true;
        }
    }


}
