import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;

public class Main {
    
    static int n;
    static int[][] map;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static boolean[][] visited;
 
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] =  Integer.parseInt(st.nextToken());
            }
        }
 
        visited = new boolean[n][n];
        bfs();
    }
 
    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = true;
        q.offer(new int[] {0, 0});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int count = map[cur[0]][cur[1]];
 
            if(count == -1) {
                System.out.println("HaruHaru");
                return;
            }
 
            for(int i = 0; i < 2; i++) {
                int nx = cur[0] + dx[i] * count;
                int ny = cur[1] + dy[i] * count;
                
                if(nx >= 0 && ny >= 0 && nx < n && ny < n && visited[nx][ny] == false) {
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
    
            }
        }
        System.out.println("Hing");
    }
}