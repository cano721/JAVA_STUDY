package studyGroup.april.april7;

import java.awt.print.Pageable;
import java.util.*;
import java.lang.*;
import java.io.*;

// 16% 틀림
// 처음 도달하는게 최소라는 보장이 있어야 한다.
// 한 턴에 움직이는 것을 체크


public class 로봇1726 {

    static int m; // 공장 세로 길이
    static int n; // 공장 가로 길이
    static int factory[][]; // 공장 지도
    static int visited[][][]; // 방문 여부 표시
    static int answer;

    // 4방향 표시 동,서,남,북
    static int[] dx = {0,1,-1,0,0};
    static int[] dy = {0,0,0,1,-1};

    static int sy, sx, sdir;
    static int ey, ex, edir;

    // 동 1, 서 2, 남 3, 북 4


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        factory = new int[m+1][n+1];
        visited = new int[m+1][n+1][5];

        answer = 0;

        for(int i = 0; i < m; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
            {
                factory[i+1][j+1] = Integer.parseInt(st.nextToken());
            }
        }



        st = new StringTokenizer(br.readLine());

        sy = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        sdir = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        ey = Integer.parseInt(st.nextToken());
        ex = Integer.parseInt(st.nextToken());
        edir = Integer.parseInt(st.nextToken());

        answer = bfs();


        System.out.println(answer);

    }

    public static int bfs()
    {
        Queue<robot> que = new LinkedList<>();
        que.add(new robot(sy, sx, sdir, 0));
        visited[sy][sx][sdir] = 1;

        while (!que.isEmpty()) {

            robot r = que.poll();

            // 한 턴에 할 수 있는 행동은 5가지 : 1칸, 2칸, 3칸 이동, 왼쪽, 오른쪽 회전

            // 왼쪽, 오른쪽 회전
            int dir1 = 0;
            int dir2 = 0;

            if(r.dir == 1 || r.dir == 2)
            {
                dir1 = 3;
                dir2 = 4;
            }
            else if (r.dir == 3 || r.dir == 4)
            {
                dir1 = 1;
                dir2 = 2;
            }

            if(visited[r.y][r.x][dir1] == 0)
            {
                visited[r.y][r.x][dir1] = 1;
                robot robot = new robot(r.y, r.x, dir1, r.seq + 1);
                if(check(robot))
                {
                    return robot.seq;
                }
                else
                {
                    que.add(robot);
                }
            }

            if(visited[r.y][r.x][dir2] == 0)
            {
                visited[r.y][r.x][dir2] = 1;
                robot robot = new robot(r.y, r.x, dir2, r.seq + 1);

                if(check(robot))
                {
                    return robot.seq;
                }
                else
                {
                    que.add(robot);
                }


            }
            // 왼쪽 오른쪽 회전

            // 바라보는 방향으로 3칸 이동
            for(int i = 1; i <= 3; i++)
            {
                int ny = r.y + dy[r.dir] * i;
                int nx = r.x + dx[r.dir] * i;

                if(ny >= 1 && ny <= m && nx >= 1 && nx <= n)
                {
                    if(factory[ny][nx] == 1)
                    {
                        break;
                    }

                    if(factory[ny][nx] == 0 && visited[ny][nx][r.dir] == 0)
                    {
                        visited[ny][nx][r.dir] = 1;
                        robot robot = new robot(ny, nx, r.dir, r.seq + 1);
                        if(check(robot))
                        {
                            return robot.seq;
                        }
                        else
                        {
                            que.add(robot);
                        }
                    }
                }
            }
        }

        return 0;
    }

    // 정답인지 체크
    public static boolean check(robot r)
    {
        if(r.y == ey && r.x == ex && r.dir == edir)
        {
            return true;
        }

        return false;

    }

    public static class robot
    {
        int y;
        int x;
        int dir;
        int seq;

        robot(int y, int x, int dir, int seq)
        {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.seq = seq;
        }


    }

}
