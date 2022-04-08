
/**
 * 1. 100*100 맵 크기.
 * 
 * 2. 로봇의 위치와 방향, 도착위치와 도착방향이 주어짐.
 * 
 * 3. 몇번의 명령만에 도착할 수 있는지 체크하여 반환.
 * 
 * ---------------------------------------------
 * 
 * 1. bfs로 행할 수 있는 모든 명령 실행.(총 5개)
 * 
 * 2. 해당좌표에 해당방향으로 방문했었는지 체크.
 * 
 * 3. 방문안했었고 이동 가능한 좌표면 이동.
 * 
 * 4. 이동한 좌표와 방향이 원하는 도착지점과 방향이면 출력.
 * 
 * 예외처리 할 것들.
 * 해당방향 이동가능한가?( 벽!으로인해 3칸뒤는 이동 가능하고 1칸뒤는 이동불가하면 3칸뒤도 이동불가)
 * 
 */

import java.util.*;
import java.io.*;

public class 로봇 {

    public static int m,n;
    public static long answer;
    public static int[][] map;
    public static int[] start;
    public static int[] end;
    public static class Point{
        int x;
        int y;
        int d;
        long cnt;
        public Point(int x, int y, int d, long cnt){
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        start = new int[3];
        end = new int[3];

        st = new StringTokenizer(br.readLine());
        // 인덱스 0부터 처리하기위한 -1
        start[0] = Integer.parseInt(st.nextToken())-1;
        start[1] = Integer.parseInt(st.nextToken())-1;
        start[2] = changeDir(Integer.parseInt(st.nextToken())); // 방향 원하는 조건으로 변경

        st = new StringTokenizer(br.readLine());
        end[0] = Integer.parseInt(st.nextToken())-1;
        end[1] = Integer.parseInt(st.nextToken())-1;
        end[2] = changeDir(Integer.parseInt(st.nextToken()));
        bfs();

        System.out.println(answer);
    }

    public static int changeDir(int d){
        if(d == 1){
            return 0;
        }else if(d == 2){
            return 2;
        }else if(d == 3){
            return 1;
        }else{
            return 3;
        }
    }

    public static void bfs(){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(start[0],start[1],start[2],0));

        //3차원이나 2차원에 비트마스킹으로 방문처리(비트마스킹 쓰겠음)
        int[][] visited = new int[m][n];
        visited[start[0]][start[1]] |= 1<<start[2];
        //방향에 따른 이동 처리(0 동쪽 1 남쪽 2 서쪽 3 북쪽)
        int[][] dirXY = {{0,1},{1,0},{0,-1},{-1,0}};

        while(!q.isEmpty()){
            Point cur = q.poll();

            // 현재좌표와 방향이 정답이면 종료
            if(cur.x == end[0] && cur.y == end[1] && cur.d == end[2]){
                answer = cur.cnt;
                return;
            }

            // 방향 전환
            int ld = (cur.d + 1)%4;
            if((visited[cur.x][cur.y] & (1<<ld)) == 0){
                visited[cur.x][cur.y] |= 1<<ld;
                q.offer(new Point(cur.x, cur.y, ld, cur.cnt+1));
            }
            int rd = (cur.d +3) %4;
            if((visited[cur.x][cur.y] & (1<<rd)) == 0){
                visited[cur.x][cur.y] |= 1<<rd;
                q.offer(new Point(cur.x, cur.y, rd, cur.cnt+1));
            }

            //1~3까지 이동
            for(int i = 1; i <= 3; i++){
                int nx = cur.x + dirXY[cur.d][0]*i;
                int ny = cur.y + dirXY[cur.d][1]*i;
                
                // 맵 벗어났으면 탈출
                if(nx < 0 || nx >= m || ny < 0 || ny >= n){
                    continue;
                }

                // 현재 방향이 막혀있으면 현재방향은 불가능.
                if(map[nx][ny] == 1){
                    break;
                }

                // 해당좌표와 방향이 가봤으면 탈출
                if((visited[nx][ny] & (1<<cur.d)) != 0){
                    continue;
                }

                visited[nx][ny] |= 1<<cur.d;
                q.offer(new Point(nx, ny, cur.d, cur.cnt+1));
            }
        }
    }
}
