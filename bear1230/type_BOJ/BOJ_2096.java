import java.io.*;
import java.util.*;

public class Main {
 
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[n + 1][3];
        
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        int[][] mindp = new int[n + 1][3];
        int[][] maxdp = new int[n + 1][3];
        
        for(int i = 1; i <= n; i++) {
            maxdp[i][0] += Math.max(maxdp[i - 1][0], maxdp[i - 1][1]) + map[i][0];
            maxdp[i][1] += Math.max(Math.max(maxdp[i - 1][0], maxdp[i - 1][1]), maxdp[i - 1][2]) + map[i][1];
            maxdp[i][2] += Math.max(maxdp[i - 1][1], maxdp[i - 1][2]) + map[i][2];
            
            mindp[i][0] += Math.min(mindp[i - 1][0], mindp[i - 1][1]) + map[i][0];
            mindp[i][1] += Math.min(Math.min(mindp[i - 1][0], mindp[i - 1][1]), mindp[i - 1][2]) + map[i][1];
            mindp[i][2] += Math.min(mindp[i - 1][1], mindp[i - 1][2]) + map[i][2];
        }
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(int i = 0; i < 3; i++) {
            min = Math.min(min, mindp[n][i]);
            max = Math.max(max, maxdp[n][i]);
        }
        System.out.println(max + " " + min);
    }
}
