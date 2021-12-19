import java.util.*;
import java.io.*;
/**
 *  마을이 2개로 분리되어야하고 집마다 연결하는 도로의 비용은 최소로 하여아한다.
 *  2개로 분리해야하기 때문에 도로를 n-1개 세우는 것이 아닌 n-2개로 세우면 
 *  서로 연결점이 없는 마을 2개가 생긴다. 
 */
public class Main {
    static int n, m;
    static int[] root;
    static class Node implements Comparable<Node>{
        int a, b, c;
        public Node(int aa, int bb ,int cc){
            a =aa;
            b= bb;
            c =cc;
        }

        @Override
        public int compareTo(Node o1){
            return this.c - o1.c;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        root = new int[n+1];
        for(int i =1; i<=n; i++) root[i] =i;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c= Integer.parseInt(st.nextToken());
            pq.add(new Node(a,b,c));
        }

        int result = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            if(cnt == n-2) break;
            Node now = pq.poll();
            if(union(now.a, now.b)){
                cnt++;
                result += now.c;
            }
        }

        System.out.println(result);

    }

    public static int find(int n ){
        if(root[n] == n ) return n;
        return root[n] = find(root[n]);
    }

    public static boolean union(int a, int b){
        a = find(a);
        b= find(b);
        if( a == b) return false;
        if(a > b) root[b] = a;
        else root[a] =b;
        return true;
    }

    
}
