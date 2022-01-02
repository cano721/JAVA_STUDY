import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] indegree;
    static int[] times;
    static int[] ans;
    static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n+1];
        for(int i =0; i< n+1; i++) list[i] = new ArrayList<>();
        indegree = new int[n+1];
        times = new int[n+1];
        ans = new int[n+1];
        for(int i =1; i<n+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            times[i] = time;
            while(true){
                int k = Integer.parseInt(st.nextToken());
                if(k == -1) break;
                indegree[i]++;
                list[k].add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i =1; i< n+1; i++){
            if(indegree[i] == 0) q.add(i); 
        }

        while(!q.isEmpty()){
            int now = q.poll();
            ans[now] += times[now];
            for(int next : list[now]){
                ans[next] = ans[next] > ans[now] ? ans[next] : ans[now];
                indegree[next]--;
                if(indegree[next] == 0 ) q.add(next);
            }
        }

        StringBuilder answer = new StringBuilder();
        for(int i =1; i< n+1; i++){
            answer.append(ans[i] + "\n");
        }
        System.out.println(answer);
    }

    
}