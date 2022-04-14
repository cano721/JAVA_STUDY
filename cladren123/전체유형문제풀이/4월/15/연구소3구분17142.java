package studyGroup.april.april15;

/*
DFS를 통해 바이러스 m개 선택
BFS를 통해 바이러스 퍼뜨리기
모든 빈 칸에 바이러스를 퍼뜨릴 수 없을 때 -1을 출력

바이러스를 퍼뜨리는 종단조건은 모든 길에 바이러스가 있을 때이다.
 */


import java.util.*;
import java.lang.*;
import java.io.*;

public class 연구소3구분17142 {

    static int n; // 연구소의 크기
    static int m; // 바이러스의 개수
    static int[][] board; // 연구소

    static ArrayList<ArrayList<Integer>> virus;
    static int virusNum;

    static ArrayList<ArrayList<Integer>> virusStore;

    static int answer;

    static int[] dy = {0,-1,0,1};
    static int[] dx = {1,0,-1,0};

    static int count; // 길을 갯수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        virus = new ArrayList<>();

        count = 0;


        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 2)
                {
                    ArrayList<Integer> virusOne = new ArrayList<>();
                    virusOne.add(i);
                    virusOne.add(j);
                    virus.add(virusOne);
                }
                if(board[i][j] != 1)
                {
                    count += 1;
                }

            }
        }

        // 이미 바이러스로 가득 차 있으면 0을 리턴
        virusNum = virus.size();
        if(count == virusNum)
        {
            answer = 0;
        }
        else
        {
            answer = Integer.MAX_VALUE;
            virusStore = new ArrayList<>();
            select(0,0);
            if(answer == Integer.MAX_VALUE)
            {
                answer = -1;
            }
        }

        System.out.println(answer);

    }

    // m개의 바이러스를 고르는 함수
    public static void select(int stage, int start)
    {

        if(stage == m)
        {
            spread();
            return;
        }

        for(int i = start; i < virusNum; i++)
        {
            virusStore.add(virus.get(i));
            select(stage+1, i+1);
            virusStore.remove(virus.get(i));
        }
    }

    // 퍼뜨리는 함수
    public static void spread()
    {
        int flag = 0;
        int virusNumber = virusNum;

        int[][] visited = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(board[i][j] == 1)
                {
                    visited[i][j] = 0;
                }
                else
                {
                    visited[i][j] = -1;
                }
            }
        }

        Queue<dot> que = new LinkedList<>();

        for(ArrayList<Integer> one : virusStore)
        {
            que.add(new dot(one.get(0), one.get(1), 0));
            visited[one.get(0)][one.get(1)] = 0;
        }

        while(!que.isEmpty())
        {
            dot d = que.poll();

            // 바이러스가 가득찼다면 중단
            if(virusNumber == count)
            {
                break;
            }

            for(int i = 0; i < 4; i++)
            {
                int ny = d.y + dy[i];
                int nx = d.x + dx[i];

                if(ny >= 0 && ny < n && nx >= 0 && nx < n)
                {
                    if(board[ny][nx] == 0 && visited[ny][nx] == -1)
                    {
                        visited[ny][nx] = d.time + 1;
                        que.add(new dot(ny,nx,d.time+1));
                        virusNumber += 1;
                    }
                    // 바이러스를 만날경우
                    if(board[ny][nx] == 2 && visited[ny][nx] == -1)
                    {
                        visited[ny][nx] = d.time+1;
                        que.add(new dot(ny, nx, d.time+1));
                    }
                }
            }
        }

        int maxTime = 0;



        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                maxTime = Math.max(maxTime, visited[i][j]);
            }
        }

        if(virusNumber == count)
        {
            answer = Math.min(answer, maxTime);
        }

    }

    public static class dot
    {
        int y;
        int x;
        int time;

        public dot(int y, int x, int time)
        {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

}
