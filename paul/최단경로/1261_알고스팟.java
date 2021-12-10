import java.util.*;
import java.io.*;
/**
 *  벽을 안부수고 이동할 수 있는 곳이 있다면 먼저 탐색,
 *  이동할 때 마다 벽을 부순 개수를 현재위치와 함께 우선순위큐에 등록.
 */
public class Main {
    
    static class Pair{
        int y, x, cnt;
        public Pair(int yy, int xx, int c){
            y =yy;
            x =xx;
            cnt = c;
        }
        
    }

    static int N,M;
    static String[] board;
    static boolean vis[][];
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
       
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new String[N];
        vis = new boolean[N][M];
        for(int i =0; i<N; i++){
            board[i] = br.readLine();
        } 
    }

    static void pro(){
        int ans =0;
        vis[0][0] = true;
        PriorityQueue<Pair> pq = new PriorityQueue<>( (o1,o2) -> o1.cnt - o2.cnt );
        pq.add(new Pair(0,0, 0));
        while(!pq.isEmpty()){
            Pair now = pq.poll();
            if(now.y == N-1 && now.x == M-1){
                ans = now.cnt;
                break;
            }

            for(int i =0; i<4; i++){
                int yy = now.y + dy[i];
                int xx = now.x + dx[i];
                if(yy < 0 || xx < 0 || yy >=N || xx>=M || vis[yy][xx] == true) continue;
                vis[yy][xx] = true;
                int c = board[yy].charAt(xx) - '0';
                pq.add(new Pair(yy,xx, now.cnt +c ));
            }
        }
        System.out.println(ans);
    }
    
}


