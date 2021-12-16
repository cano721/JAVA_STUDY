import java.util.*;
import java.io.*;


public class Main {
    
    static int[] root;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for(int i =0; i<=n; i++) root[i] =i;

        StringBuilder sb = new StringBuilder();
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(k == 0){
                union(a, b);
            }else{
                if(find(a) == find(b)) sb.append("YES\n");
                else sb.append("NO\n"); 
            }
        }
        System.out.println(sb);
    }

    static int find(int n){
       if(root[n] == n ) return n;
       return root[n] = find(root[n]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if( a == b ) return;
        if( a > b) root[b] = a;
        else root[a] =b;
        return;
    }
       
}


