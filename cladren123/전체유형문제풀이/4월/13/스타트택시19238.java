package studyGroup.april.april13;

import java.util.*;
import java.lang.*;
import java.io.*;

/*
bfs를 통해 가장 가까운 시민 찾기
클래스를 통해 연료 표시

승객 찾기
같이 탑승 x
최단거리가 가장 짧음
행 번호, 열 번호가 작은 손님

목적지 운반 (승객을 태워 이동하면서 소모한 연료 양의 두배가 충천된다.)

반례 모음
https://www.acmicpc.net/board/view/58112
감사합니다.

문제를 꼼꼼히 읽고 조건을 구현하는 것이 중요하다.
 */

public class 스타트택시19238 {

    static int n; // 활동 영역
    static int m; // 승객의 수
    static int fuel; // 연료의 양

    static int[][] board; // 활동 영역

    static int texiY; // 택시의 현재 y좌표
    static int textX; // 택시의 현재 x좌표

    static HashMap<Integer, client> map;

    // 행 번호, 열 번호가 낮은 순
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    static ArrayList<car> hubo;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i = 1; i < n+1; i++)
        {
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j < n+1; j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        texiY = Integer.parseInt(st.nextToken());
        textX = Integer.parseInt(st.nextToken());

        car texi = new car(texiY, textX, fuel);

        map = new HashMap<>();

        for(int i = 0; i < m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());

            board[sy][sx] = i+2;
            map.put(i + 2, new client(sy, sx, ey, ex));
        }

        int answer = -1;

        int fuelFlag = 0;


        for(int i = 0; i < m; i++)
        {
            hubo = new ArrayList<>();
            findClient(texi);

            if(hubo.isEmpty())
            {
                fuelFlag = 1;
                break;
            }

            // 최단거리의 손님이 여러 명일 경우 행 번호, 열 번호가 작은 승객을 고른다.
            Collections.sort(hubo, new Comparator<car>() {
                @Override
                public int compare(car o1, car o2) {
                    if(o1.y == o2.y)
                    {
                        return o1.x - o2.x;
                    }
                    return o1.y - o2.y;
                }
            });

            car start = hubo.get(0);

            int clientIndex = board[start.y][start.x];
            board[start.y][start.x] = 0;
            car destiny = findDestiny(start, clientIndex);

            if(destiny.fuel == -1)
            {
                fuelFlag = 1;
                break;
            }

            destiny.fuel = destiny.fuel + (start.fuel - destiny.fuel) * 2; // 2배 충전
            texi = destiny;
        }


        if(fuelFlag == 0)
        {
            answer = texi.fuel;
        }

        System.out.println(answer);



    }

    // 목적지를 찾는 함수
    public static car findDestiny(car start, int clientIndex)
    {
        client client = map.get(clientIndex);

        Queue<car> que = new LinkedList<>();
        que.add(start);

        int[][] visited = new int[n+1][n+1];
        visited[start.y][start.x] = 1;

        while(!que.isEmpty())
        {
            car one = que.poll();

            if(one.fuel < 0) continue;

            for(int i = 0; i < 4; i++)
            {
                int ny = one.y + dy[i];
                int nx = one.x + dx[i];

                if(ny >= 1 && ny < n+1 && nx >= 1 && nx < n+1)
                {
                    if(board[ny][nx] != 1 && visited[ny][nx] == 0)
                    {
                        // 목적지에 도착
                        if(ny == client.endY && nx == client.endX)
                        {
                            return new car(ny, nx, one.fuel-1);
                        }

                        visited[ny][nx] = 1;
                        que.add(new car(ny, nx, one.fuel-1));
                    }
                }
            }
        }

        // 연료가 다 떨어졌을 경우
        return new car(0,0,-1);


    }


    // 가까운 손님 찾기
    public static void findClient(car texi)
    {
        // 자기 자신의 위치에 손님 탐색
        if(board[texi.y][texi.x] != 0 && board[texi.y][texi.x] != 1)
        {
            hubo.add(texi);
        }


        Queue<car> que = new LinkedList<>();
        que.add(texi);

        int[][] visited = new int[n+1][n+1];
        visited[texi.y][texi.x] = 1;

        while(!que.isEmpty())
        {
            car one = que.poll();

            if(one.fuel < 0) continue;
            if(!hubo.isEmpty())
            {
                if(hubo.get(0).fuel >= one.fuel) continue;
            }

            for(int i = 0; i < 4; i++)
            {
                int ny = one.y + dy[i];
                int nx = one.x + dx[i];

                if(ny >= 1 && ny < n+1 && nx >= 1 && nx < n+1)
                {
                    if(board[ny][nx] != 1 && visited[ny][nx] == 0)
                    {
                        // 손님일 경우
                        if(board[ny][nx] != 0 && board[ny][nx] != 1)
                        {
                            hubo.add(new car(ny, nx, one.fuel-1));
                        }

                        visited[ny][nx] = 1;
                        que.add(new car(ny, nx, one.fuel-1));
                    }
                }
            }
        }

    }


    public static class car
    {
        int y;
        int x;
        int fuel;

        public car(int y, int x, int fuel)
        {
            this.y = y;
            this.x =x ;
            this.fuel = fuel;
        }
    }

    public static class client
    {
        int startY;
        int startX;
        int endY;
        int endX;

        public client(int startY, int startX, int endY, int endX)
        {
            this.startY = startY;
            this.startX = startX;
            this.endY = endY;
            this.endX = endX;
        }
    }




}
