import java.util.*;
import java.io.*;
/**
 * 유니온파인드 기본문제
 */
public class BJ1717_집합의표현 {

    public static int n, m;
    public static int[] parent,rank;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        rank = new int[n+1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
            rank[i] = 0;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(c == 0){
                union(a,b);
            }else{
                if(find(a) == find(b)){
                    bw.write("YES\n");
                }else{
                    bw.write("NO\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }

    public static int find(int x){
        if(x == parent[x]){
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y) return;

        if(rank[x] < rank[y]){
            parent[x] = y;
        }else{
            parent[y] = x;

            if(rank[x] == rank[y]){
                rank[x]++;
            }
        }
    }
}
