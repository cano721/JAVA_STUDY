import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        List<Integer>[] list = (List<Integer>[]) new List[n+1];

        int indegree[] = new int[n+1];
        int time[] = new int[n+1];
        int dp[] = new int[n+1];

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<Integer>();
        }

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            for(int j =1 ; j<=m; j++){
                int idx = Integer.parseInt(st.nextToken());
                list[idx].add(i);
                indegree[i] +=1;
            }
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=1; i<=n; i++){
            if(indegree[i] ==0){
                q.add(i);
                dp[i] = time[i];
            }
        }
        
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : list[cur]){
            	indegree[next] -=1 ;
                if(dp[next] < time[next] + dp[cur]){
                    dp[next] = time[next] + dp[cur];
                }
                if(indegree[next] == 0){
                    q.add(next);
                }
            }
        }

        int ans =0;
        for(int i=1 ; i<=n; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}