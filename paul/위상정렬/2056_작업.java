import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] list;
    static int[] indegree;
    static int[] times;
    static int[] ans;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       list = new ArrayList[n+1];
       for(int i =0; i<=n; i++) list[i] = new ArrayList<>();
       indegree = new int[n+1];
       times = new int[n+1];
       ans = new int[n+1];
       for(int i =1; i<=n; i++){
           StringTokenizer st = new StringTokenizer(br.readLine());
           times[i] = Integer.parseInt(st.nextToken());
           int linked = Integer.parseInt(st.nextToken());
           indegree[i] = linked; 
           for(int j = 0; j<linked; j++){
               int from = Integer.parseInt(st.nextToken());
               list[from].add(i);
           }
       }
       
       
       pro();
 
    }

    static void pro(){
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i< n+1; i++){
            if (indegree[i] == 0) {
                q.add(i);
                ans[i] = times[i];
            }
        }

        int answer = 0;
        while(!q.isEmpty()){
            int now = q.poll();
            answer = Math.max(answer, ans[now]);
            for(int next : list[now]){
                ans[next] = Math.max(ans[next], times[next]+ans[now]);
                if(--indegree[next] == 0 ) q.add(next);
            }
        }

        System.out.println(answer);
    }
}