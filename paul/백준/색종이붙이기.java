import java.util.*;
import java.io.*;
public class Main {
    
    static int paper[] = {0, 5, 5, 5, 5, 5};
    static int a[][] = new int[10][10];
    static int min = 100;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i=0; i<10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++)
                a[i][j] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);
        System.out.println(min == 100 ? -1 : min);
    }
    
    public static void dfs(int idx, int cnt) {
        if(idx == 100) {
            min = Math.min(min, cnt);
            return;
        }
        int r = idx/10;
        int c = idx%10;
        
        if(min <= cnt) return;
        
        if(a[r][c] == 1) {
            for(int i=5; i>0; i--) {
                if(paper[i] > 0) {
                    if(check(r, c, i)) {
                        fill(r, c, i, 0);
                        paper[i]--;
                        dfs(idx+1, cnt+1);
                        fill(r, c, i, 1);
                        paper[i]++;
                    }
                }
            }
        } else dfs(idx+1, cnt);
    }
    
    public static boolean check(int r, int c, int len) {
        if(r + len > 10 || c + len > 10) return false;
        for(int i=r; i<r + len; i++)
            for(int j=c; j<c + len; j++)
                if(a[i][j] == 0) return false;
        return true;
    }
    
    public static void fill(int r, int c, int len, int status) {
        for(int i=r; i<r + len; i++)
            for(int j=c; j<c + len; j++)
                a[i][j] = status;
    }
}

 