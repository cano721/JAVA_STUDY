package studyGroup.may.may24;

import java.util.*;
import java.io.*;

/*

같은 곳을 방문하지 않고 N번 동안 이동할 때의 확률을 구하는 것

https://moonsbeen.tistory.com/173

 */

public class 미친로봇1405 {

    static int n;// 로봇의 행동 n <= 14
    static double[] percent;

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    static boolean[][] visited;
    static double answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        percent = new double[4];

        percent[0] = Integer.parseInt(st.nextToken()) * 0.01;
        percent[1] = Integer.parseInt(st.nextToken()) * 0.01;
        percent[2] = Integer.parseInt(st.nextToken()) * 0.01;
        percent[3] = Integer.parseInt(st.nextToken()) * 0.01;

        visited = new boolean[30][30];
        answer = 0;

        dfs(15, 15, 0, 1);

        System.out.println(answer);


    }

    public static void dfs(int y, int x, int index, double total)
    {
        if(index == n)
        {
            answer += total;
            return;
        }

        visited[y][x] = true;

        for(int i = 0; i < 4; i++)
        {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny >= 0 && ny < 30 && nx >= 0 && nx < 30)
            {
                if(visited[ny][nx] == false)
                {
                    visited[ny][nx] = true;
                    dfs(ny, nx, index+1, total * percent[i]);
                    visited[ny][nx] = false;
                }
            }

        }

    }



}
