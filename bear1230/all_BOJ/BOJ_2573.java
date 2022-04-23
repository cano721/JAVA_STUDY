import java.io.*;
import java.util.*;

public class Main {

    public static class node{
        int x; 
        int y;

        node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int year = 0;

        while(true){
            visited = new boolean[N][M];
            int cnt = 0;
            boolean flag = false;

            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(map[i][j] != 0 && !visited[i][j]){
                        cnt++;
                        if(cnt == 2){
                            flag = true;
                            break;
                        }
                        bfs(i, j);
                    }
                }
            }
            if(flag)
                break;

            if(cnt == 0){
                year = 0;
                break;
            }

            dfs();
            year++;
        }

        System.out.println(year);
    }
    private static void dfs() {
        int[][] copyMap = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            copyMap[i] = Arrays.copyOf(map[i], M);
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(copyMap[i][j] != 0){
                    int zeroCnt = 0;

                    for(int d = 0 ; d < 4; d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(nx < 0 || N <= nx || ny < 0 || M <= ny)
                            continue;
                        
                        if(copyMap[nx][ny] == 0)
                            zeroCnt++;
                    }

                    map[i][j] = Math.max(0, copyMap[i][j] - zeroCnt);
                }
            }
        }
    }
    private static void bfs(int x, int y) {
        Queue<node> que = new LinkedList<>();
        visited[x][y] = true;
        que.offer(new node(x, y));

        while(!que.isEmpty()){
            node now = que.poll();

            for(int i = 0 ; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || N <= nx || ny < 0 || M <= ny)
                    continue;

                if(map[nx][ny] != 0 && !visited[nx][ny]){
                    que.offer(new node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }
}