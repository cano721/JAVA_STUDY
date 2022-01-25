import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main{
    static int team[][];
    static int n, m;
    static boolean check[];
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        
        n = Integer.parseInt(br.readLine());
        team = new int [n][n];
        
        check = new boolean[n];
        m = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            stk = new StringTokenizer(br.readLine()," ");
            for(int j =0; j<n; j++){
                team[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        DFS(0,0);
        System.out.println(m);
    }
    
    public static void DFS(int start, int depth){
        if(depth == n/2) {
            m = Math.min(m, cal());
            return;
        }

        for(int i=start; i<n; i++) {

            if(!check[i]) {
                check[i] = true;
                DFS(i+1, depth + 1);
                check[i] = false;
            }
        }
    }
    
    public static int cal(){
        int start = 0;
        int idx = 0;
        
        for(int i=0; i<n-1; i++){
            for(int j =i+1; j<n; j++){
                if(check[i] == true && check[j] == true){
                    start += team[i][j] +team[j][i];
                }
                else if(check[i] == false && check[j] == false){
                    idx += team[i][j] + team[j][i];

                } 
            }
        }
        return Math.abs(start-idx);
    }
}
