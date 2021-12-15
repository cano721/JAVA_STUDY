import java.util.*;
import java.io.*;

public class Main {
    
    static int n,m;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parent = new int[n+1];

        for(int i =1; i<=n; i++) parent[i] =i;

        for(int i =1; i<=n; i++){
            StringTokenizer st =new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                int k = Integer.parseInt(st.nextToken());
                if(k == 1) union(i, j);
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(st.nextToken()));
        for(int i = 1; i<m; i++){
            if (start != find(Integer.parseInt(st.nextToken()))){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    } 

    static int find(int n){
        if(parent[n] ==  n) return n;
        parent[n] = find(parent[n]);
        return parent[n];
    }
    
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if( a == b ) return;
        if(a < b) parent[a] = b;
        else parent[b] = a;
    }
}


