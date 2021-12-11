import java.io.*;
import java.util.*;

public class Main {
    
    static int INF = 100_000_000;
    static int n;
    static LinkedList<Node> list[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        while(tc --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            list = new LinkedList[n+1];
            
            for(int i = 1; i <= n; i++)
                list[i] = new LinkedList<>();
            
            for(int i = 0; i < m+w; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                
                if(i < m) {
                    list[s].add(new Node(e, t));
                    list[e].add(new Node(s, t));
                }
                else
                    list[s].add(new Node(e, -t));
            }
            
            boolean check = false;
            
            for(int i = 1; i <= n; i++) {
                if(bellmanFord(i)) {
                    check = true;
                    sb.append("YES\n");
                    break;
                }
            }
            
            if(!check)
                sb.append("NO\n");
        }
    
        System.out.println(sb.toString());
    }
    
    public static boolean bellmanFord(int start) {
        int dist[] = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        boolean update = false;
        
        for(int i = 1; i < n; i++) {
            update = false;
            
            for(int j = 1; j <= n; j++) {
                for(Node node : list[j]) {
                    if(dist[j] != INF && dist[node.to] > dist[j] + node.time) {
                        dist[node.to] = dist[j] + node.time;
                        update = true;
                    }
                }
            }
            
            if(!update)
                break;
        }
        
        if(update) {
            for(int i = 1; i <= n; i++) {
                for(Node node : list[i]) {
                    if(dist[i] != INF && dist[node.to] > dist[i] + node.time)
                        return true;
                }
            }
        }
        
        return false;
    }
    
    static class Node{
        int to, time;
        
        Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }
}