import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int s, ans = Integer.MAX_VALUE;
    static int[][] a;
    static boolean[] visit;
    
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
        
        a = new int[n+1][n+1];
        visit = new boolean[n+1];
        
        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                a[i][j] =  Integer.parseInt(st.nextToken());
            }
                
        }         
      
        for(int i=1; i<=n; i++) {
        	s = i;
        	visit[i] = true;
            travel(i, 1, 0);
            visit[i] = false;
        }
        
        System.out.println(ans);
        
    }
    
    public static void travel(int pre, int cnt, int cost){
        if(cnt==n){
        	if(a[pre][s]==0) return;
            ans = Math.min(cost+a[pre][s], ans); return;
        }
        
        for(int i=1; i<=n; i++){
            if(!visit[i] && a[pre][i]!=0) {
                visit[i] = true;
                travel(i, cnt+1, cost+a[pre][i]);
                visit[i] = false;
            }
        }
    }
}
