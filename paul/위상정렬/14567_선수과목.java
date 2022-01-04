import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] indegree, ans;
    static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        indegree = new int[n+1];
        ans = new int[n+1];
        list = new ArrayList[n+1];
        for(int i =0; i<n+1; i++) list[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            indegree[b]++;
            list[a].add(b);
        }
        pro();
    }

    static void pro(){  
        Queue<Integer> q = new LinkedList<>();
        for(int i =1; i<n+1; i++){
            if(indegree[i] == 0 ) {
                q.add(i);
                ans[i] = 1;
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();
            for(int next : list[now]){
                ans[next] = Math.max(ans[next], ans[now] + 1);
                if(--indegree[next] == 0 ) q.add(next);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <n+1; i++) sb.append(ans[i] + " ");

        System.out.println(sb);

    }
}