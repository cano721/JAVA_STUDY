/**
 * 1. bfs 전체 탐색
 * 
 * 2. 탐색해서 먹을 수 있는 곳들을 체크.
 * 
 * 3. 그 중 가장 거리가 가까운 물고기 먹기.
 * 
 * 4. 거리가 가까운것들 중 우선순위 1. 위, 2. 왼쪽
 * 
 * 5. 이동은 1초
 * 
 * 6. 아기 상어가 몇초동안 물고기를 잡아먹을 수 있는지 출력.
 * 
 * ----------------------------------------------
 * 
 * 1. 전체 맵을 bfs 돌면서 잡아 먹을 수 있는 물고기를 만나면 우선순위 큐에 넣기
 * 
 * 2. 우선순위 큐엔 이동거리,좌표를 클래스형태로 넣기.
 * 
 * 3. 클래스 내에 compare 구현
 * 
 * 4. 우선순위에서 맨 첫번째 뽑아서 이동 후 먹기.
 * 
 * 5. 1번부터 반복.
 * 
 * 6. 최종 이동 시간 출력.
 */

import java.util.*;
import java.io.*;

public class 아기상어 {

    public static int time, n, size = 2, eat;
    public static int[][] map, visited;
    public static Queue<Info> q;
    public static int[][] dirXY = {{0,1},{0,-1},{1,0},{-1,0}};

    public static class Info{
        int x;
        int y;
        int move;
        public Info(int x, int y, int move){
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    public static PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) ->
    {
        if(o1.move == o2.move){
            if(o1.x == o2.x){
                return o1.y - o2.y;
            }else{
                return o1.x - o2.x;
            }
        }else{
            return o1.move - o2.move;
        }
    });

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        int[] start = new int[2];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    start[0] = i;
                    start[1] = j;
                    map[i][j] = 0;
                }
            }
        }

        while(true){
            bfs(start);

            if(pq.isEmpty()){
                break;
            }
            Info info = pq.poll();
            time += info.move;
            map[info.x][info.y] = 0;
            start[0] = info.x;
            start[1] = info.y;
            eat++;
            if(size == eat){
                size++;
                eat = 0;
            }
            pq.clear();
        }
        System.out.println(time);
    }

    public static void bfs(int[] pos){
        q = new LinkedList<>();
        q.offer(new Info(pos[0], pos[1], 0));

        visited = new int[n][n];

        while(!q.isEmpty()){
            Info cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dirXY[i][0];
                int ny = cur.y + dirXY[i][1];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n){
                    continue;
                }

                if(visited[nx][ny] == 0){
                    if(map[nx][ny] == size || map[nx][ny] == 0){
                        visited[nx][ny] = 1;
                        q.offer(new Info(nx,ny,cur.move+1));
                    }else if(map[nx][ny] < size){
                        visited[nx][ny] = 1;
                        pq.offer(new Info(nx,ny,cur.move+1));
                    }
                }
            }
        }
    }
}
