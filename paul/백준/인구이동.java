import java.util.*;
import java.io.*;

public class Main {
    static int n, L, R, gap;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    static int[][] board = new int[51][51];
    static boolean[][] vis ;

    static class Pair{
        int y,x;
        public Pair(int yy ,int xx){
            y = yy;
            x =xx;
        }
    }

    public static void main(String[] args) throws Exception {
       input();
       pro();
    }

    public static void input() throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for(int i =0; i< n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static boolean bfs(int y, int x){
        Queue<Pair> q = new LinkedList<>();
        List<Pair> list = new ArrayList<>();
        q.add(new Pair(y,x));
        vis[y][x] = true;
        int nums = 0;
        while(!q.isEmpty()){
            Pair now = q.poll();
            nums += board[now.y][now.x];
            list.add(now);
            for(int i =0; i<4; i++){
                int yy = now.y + dy[i];
                int xx = now.x + dx[i];
                if(yy < 0 || xx < 0 || yy>=n || xx>=n || vis[yy][xx] == true) continue;
                int gap = Math.abs( board[now.y][now.x] - board[yy][xx]);
                if(gap < L || gap > R) continue;
                vis[yy][xx] =true;
                q.add(new Pair(yy,xx));
                 
            }
        }

        //list 배열에 인구 분배.

        if(list.size() == 1) return false;

        int k = nums/list.size();
        for(Pair i : list){
            board[i.y][i.x] = k;
        }

        return true;

    }

    public static boolean checkMove(){
        vis = new boolean[n][n];
        boolean flag= false;
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(vis[i][j] == true) continue;
                if(bfs(i,j)){
                    flag= true;
                }
            }
        }
        
        return flag;
    }

    public static void show(){
        System.out.println("\n");
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                System.out.print(board[i][j]+ " ");
            }
            System.out.println(" ");
        }
    }
    public static void pro(){
        int days = 0;
        while(true){
            if(checkMove() == false) break;
            //show();
            days++;
        }        

        System.out.println(days);

    }
}
   

