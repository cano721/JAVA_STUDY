import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        int map[][] = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        map[r][c] = -1;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int ans = 1;
        int flag = 0;

        while(true) {
            dir = (dir+3) % 4;
            int nr = r + dx[dir];
            int nc = c + dy[dir];
            
            if(map[nr][nc] == 0) {
                map[nr][nc] = -1;
                r = nr;
                c = nc;
                ans += 1;
                flag = 0;
                continue;
            }
            else flag += 1;
            
            if(flag == 4) {
                r -= dx[dir];
                c -= dy[dir];
                if(map[r][c] == 1) break;
                flag = 0;
            }
        }
        System.out.println(ans);
    }
}