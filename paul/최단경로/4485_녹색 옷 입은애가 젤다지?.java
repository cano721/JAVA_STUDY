import java.util.*;
import java.io.*;

public class Main {
    
    static class Pair{
        int y, x, weight;
        public Pair(int yy, int xx ,int ww){
            y =yy;
            x =xx;
            weight = ww;
        }
    }

    static int n, t;
    static int[][] board = new int[126][126];
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(((n=Integer.parseInt(br.readLine()) )!= 0)){
            t++;
            for(int i =0; i<n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j =0; j<n; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            pro();
        }
        
    }

    static void input() throws Exception{
        
    }

    static void pro(){
        PriorityQueue<Pair> pq = new PriorityQueue<>( (o1,o2)-> o1.weight-o2.weight );
        pq.add(new Pair(0,0, board[0][0]));
        boolean[][] vis = new boolean[n][n];
        vis[0][0] = true;
        int ans =0;
        while(!pq.isEmpty()){
            Pair now = pq.poll();

            if(now.y == n-1 && now.x == n-1){
                ans = now.weight;
                break;
            }

            for(int i =0; i<4; i++){
                int yy = now.y + dy[i];
                int xx = now.x + dx[i];
                if(yy<0 || xx < 0 || yy>=n || xx>=n || vis[yy][xx]) continue;
                vis[yy][xx] = true;
                pq.add(new Pair(yy,xx, now.weight + board[yy][xx]));
            }
        }

        System.out.println("Problem "+ t+": " + ans);

    }
}