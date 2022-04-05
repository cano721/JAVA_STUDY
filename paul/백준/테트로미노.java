import java.io.*;
import java.util.*;

public class 테트로미노{
    static int n,m, ans;
    static int[][] board;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    static boolean[][] vis;

    //5번째 모양
    static int[][][] block = {
        { {0,0}, {0,1}, {0, -1}, {1,0} } // ㅜ
        ,{ {0,0}, {1,0}, {-1,0}, {0,-1} } // ㅓ
        , { {0,0}, {0,1}, {0, -1}, {-1,0} } // ㅗ
        , { {0,0}, {1,0}, {-1,0}, {0,1} } // ㅏ
    };

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        vis = new boolean[n][m];

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro(){
        for(int i =0; i<n; i++){
            for(int j=0; j<m; j++){
                vis[i][j] = true;
                dfs(i,j, 0, board[i][j]);
                vis[i][j] = false;

                checkFifthModel(i,j);
            }
        }

        System.out.println(ans);
    }

    static void dfs(int y, int x, int len, int sum){
        if(len == 3){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i =0; i<4; i++){
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if(!isValid(ny, nx)) continue;
            if(vis[ny][nx]) continue;
            vis[ny][nx] = true;
            dfs(ny, nx, len+1, sum+board[ny][nx]);
            vis[ny][nx] = false;
        }
    }

    static void checkFifthModel(int y, int x){
        
        for(int k=0; k< block.length; k++){
            int sum =0;
            for(int i =0; i< block[0].length; i++){
                int ny = block[k][i][0] + y;
                int nx = block[k][i][1] + x;
                if(isValid(ny, nx)) {
                    sum += board[ny][nx];
                }
                else {
                    sum =0;
                    break;
                }
                ans = Math.max(ans, sum);
            }
        }
    }

    static boolean isValid(int y, int x){
        return (y>=0 && y <n && x >= 0 && x <m);
    }
}