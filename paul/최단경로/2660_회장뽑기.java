import java.util.*;
import java.io.*;

public class Main {
    
    static int n ,INF = (int)1e8;
    static int[][] map= new int[51][51];

    public static void main(String[] args) throws Exception {
       input();
       pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i =0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = INF;
            }
        }

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            if( a < 0 && b < 0) break;
            map[a][b] = 1;
            map[b][a] = 1;
        }
    }

    static void pro(){
       for(int k=0; k<n; k++){
           for(int i =0; i<n; i++){
               for(int j=0; j<n; j++){
                   if(i == j) continue;
                   map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
               }
           }
       }
        int value = Integer.MAX_VALUE;
        int ans = 0;
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<n; i++){
            int cnt=0;
            for(int j=0; j<n; j++){
                if(i == j) continue;
                cnt = Math.max(cnt, map[i][j]);
            }
            if(cnt < value){
                value = cnt;
                ans = 1;
                sb = new StringBuilder();
                sb.append(i+1).append(' ');
            }else if(cnt == value){
                ans++;
                sb.append(i+1).append(' ');
            }
        }
        System.out.println(value + " " + ans);
        System.out.println(sb);
       
    }    
}


