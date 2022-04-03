import java.util.*;
import java.io.*;

public class p21611 {

    static class Pair{
        int y,x;
        public Pair(int yy, int xx){
            y = yy;
            x = xx;
        }
    }

    static int n, m;
    static int[][] board;
    static int[] d, s;
    static int[][] dydx = { {0,0}, {-1,0}, {1,0}, {-1,0}, {0,1} }; // 위 아래 좌 우  1,2,3,4
    static List<Pair> mList = new ArrayList<>();
    static Pair center;
    static int bcnt[] = new int[4]; // 터진 구술 갯수

    public static void main(String[] args) throws Exception {
        input();
        makeMapList();
        print();
        pro();
    }

    public static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        d = new int[m];
        s = new int[m];
        center = new Pair(n/2, n/2);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            d[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void print(){
        System.out.println("-------------------");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(board[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }

    public static void pro() {

        makeMapList();

        for(int i =0; i<m; i++){
            destroy(i);
            while(explore() != 0) ;
            move();
            change();
        }
        System.out.println(bcnt[1] + 2*bcnt[2] + 3*bcnt[3]);
    }

    static boolean isValid(int y, int x){
        return (y>=0 && y<n && x>=0 && x<n);
    }

    static void makeMapList(){
        int ny = 0, nx =0;
        int[] dy= {0,1,0,-1};
        int[] dx = {1,0,-1,0};
        int dd =0;
        boolean[][] vis = new boolean[n][n];
        int cy=0, cx= 0;
        while(true){
            if(ny == n/2 && nx == n/2) break;
            
            mList.add(new Pair(ny,nx));
            vis[ny][nx] = true;
            cy = ny+dy[dd]; cx = nx+dx[dd];
            if( !isValid(cy, cx) || vis[cy][cx] ){
                dd = (dd+1)%4;
            }

            ny = dy[dd] + ny;
            nx = dx[dd] + nx;
        }
        
        Collections.reverse(mList);
    }

    //k번째 방향 거리 부수기.
    static void destroy(int k){
        Pair pos = center;
        for(int i= 1; i<= s[k]; i++){
             pos.y += dydx[d[k]][0];
             pos.x += dydx[d[k]][1];
             if( !isValid(pos.y, pos.x)) break;
             board[pos.y][pos.x] = 0;
        }
    }

    static int explore(){
        int total =0, sCnt =0, prev =-1;
        List<Pair> list = new ArrayList<>();
        for(Pair pair : mList){
            int now = board[pair.y][pair.x];
            if(now == 0) continue;
            if(now == prev) {
                sCnt++;
                list.add(pair);
            }
            else{
                if(sCnt >= 4){
                    for(Pair target : list){
                        bcnt[board[target.y][target.x]]++;
                        board[target.y][target.x] = 0;
                        total++;
                    }
                }
                sCnt =0;
                prev = now;
                list = new ArrayList<>();
            }
        }

        if(sCnt>=4){
            for(Pair target : list){
                board[target.y][target.x] = 0;
                total++;
            }
        }
        
        return total;
    }

    static void move(){
        List<Integer> bead = new ArrayList<>();
        //구슬 찾기.
        for(Pair pos : mList){
            if(board[pos.y][pos.x] != 0) {
                bead.add(board[pos.y][pos.x]);
            }
        }
        
        board = new int[n][n];
        for(int i =0; i< bead.size(); i++){
            Pair pos = mList.get(i);
            board[pos.y][pos.x] = bead.get(i);
        }

    }

    static void change(){
        int[][] nboard = new int[n][n];
        int nidx =0, prev = -1, scnt =1;
        for(Pair pos : mList){
            int now = board[pos.y][pos.x];
            if(now == prev) {
                scnt++;
            }else{
                if(nidx >= n*n ) break;
                Pair A = mList.get(nidx++);
                Pair B = mList.get(nidx++);
                nboard[A.y][A.x] = scnt;
                nboard[B.y][B.x] = prev;

                prev = now;
                scnt = 1;
            }
        }

        if (nidx < n * n && scnt > 1) {
            Pair A = mList.get(nidx++);
            Pair B = mList.get(nidx++);
            nboard[A.y][A.x] = scnt;
            nboard[B.y][B.x] = prev;
        }
        board= nboard;
    }
}
