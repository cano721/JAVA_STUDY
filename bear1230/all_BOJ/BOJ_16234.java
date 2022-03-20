import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int[][] visit;
    static int cnt, sum = 0;

    static boolean is_check = false;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n,l,r;
    static LinkedList<country> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visit = new int[n+1][n+1];
            is_check = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visit[i][j] == 0) {
                        sum = 0;
                        list = new LinkedList<country>();
                        list.add(new country(i, j));
                        visit[i][j] = 1;
                        sum += map[i][j];
                        dfs(i, j);
                        
                        if(list.size() > 1){
                            is_check = true;
                            change();
                        }
                    }
                }
            }

            if(is_check){
                cnt++;
            }
            else{
                System.out.println(cnt);
                return;
            }
        }

    }

    static void dfs(int a, int b) {
        
        for (int i= 0; i< 4; i++) {
            int nx = a +dx[i];
            int ny = b +dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n){
                if(visit[nx][ny] == 0) {
                    int diff = Math.abs(map[a][b] - map[nx][ny]);
                    
                    if (diff >= l && diff <= r) {
                        visit[nx][ny] = 1;
                        list.add(new country(nx, ny));
                        sum += map[nx][ny];
                        dfs(nx, ny);
                    }
                }
            }

        }
    }
   
    static void change(){
        int avg = sum/list.size();
        for(country c_con:list)
            map[c_con.x][c_con.y] = avg;
    }
}

class country{
    int x;
    int y;
    public country(int x,int y){
        this.x = x;
        this.y = y;
    }
}
