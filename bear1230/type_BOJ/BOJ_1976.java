import java.util.*;
import java.io.*;
 
public class Main {
 
    static int n, m;
    static int parent[];
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        int[] plan = new int[m];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                // 두 도시 연결
                if(tmp == 1) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            plan[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        

        for (int i = 0; i < m - 1; i++) {
            // 두 도시가 연결X 일때
            if(find(plan[i]) != find(plan[i + 1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
 
    private static boolean union(int a, int b) {
        
        a = find(a);
        b = find(b);
        
        if(a == b) return false;
        parent[b] = a;       
        return true;
        
    }
 
    private static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
    
}

