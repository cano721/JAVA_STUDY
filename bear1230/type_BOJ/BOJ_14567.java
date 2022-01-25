import java.io.*;
import java.util.*;

/*
위상정렬- 선수과목 (Prerequisite)
*/
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] cnt = new int[n + 1];
        int[] dp = new int[n + 1];
        
        ArrayList<Integer>[] list = new ArrayList[n + 1];        
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());         
            cnt[b]++;
            list[a].add(b);
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 1; i <= n; i++) {
            if (cnt[i] == 0) {
                q.offer(i);
                dp[i] = 1;
            }
        }
        
        for (int i = 1; i <= n; i++) {
            int v = q.poll();            
            for (int next : list[v]) {
                dp[next] = Math.max(dp[v] + 1, dp[next]);
                cnt[next]--;
                if (cnt[next] == 0) {
                    q.offer(next);

                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            System.out.print(dp[i] + " ");
        }
    }
}