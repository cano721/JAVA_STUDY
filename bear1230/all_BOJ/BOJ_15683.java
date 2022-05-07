import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans=Integer.MAX_VALUE;
    static int[] dir;
    static int[][] map, copy;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        copy = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                if ((map[i][j] = Integer.parseInt(st.nextToken()))%6!=0){
                    list.add(new int[]{i,j});
                }
            }      
        }

        dir = new int[list.size()];
        dfs(0);
        System.out.println(ans);
    }
    static void set(int r, int c, int d) {
        int ny = r;
        int nx = c;
        while (map[ny][nx]!=6) {
            copy[ny][nx] = '#';
            ny += dy[d];
            nx += dx[d];
            if (ny<0 || N<=ny || nx<0 || M<=nx) break;
        }
    }

    static void dfs(int index) {
        if (index == list.size()) {
            for (int i=0; i<N; i++){
                for (int j=0; j<M; j++){
                    copy[i][j] = map[i][j]; 
                }
            }
    
            for (int i=0; i<index; i++) {
                int[] p = list.get(i);
                int type = map[p[0]][p[1]];
                set(p[0], p[1], dir[i]);
                if (type==2 || type==5) set(p[0], p[1], (dir[i]+2)%4);
                if (type==3 || type==4 || type==5) set(p[0], p[1], (dir[i]+1)%4);
                if (type==4 || type==5) set(p[0], p[1], (dir[i]+3)%4);
            }
            int count=0;
            
            for (int i=0; i<N; i++){
                for (int j=0; j<M; j++){
                    if (copy[i][j]==0) count++;
                }
            }

            if (ans > count){
                ans = count;
            }
                

            return;
        }
        int[] p = list.get(index);
        
        if (map[p[0]][p[1]]==5) dfs(index+1);
        else for (int i=0; i<4; i++) {
            if (map[p[0]][p[1]]==2 && i>=2) continue;
            dir[index] = i;
            dfs(index+1);
        }

    }

    
}
