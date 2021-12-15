import java.util.*;
import java.io.*;

/**
 *  MST : 크루스칼 알고리즘 
 *  1. 간선들을 오름차순 정렬하고 정점들을 각 컴포넌트로 초기화.
 *  2. 간선을 살피면서 양쪽 정점을 포함한 컴포넌트가 연결되어 있지 않다면 간선을 뽑고 연결한다 (find)
 *  3. 간선 v-1개가 뽑혔을 때, 그 간선들과 정점들이 이루는 그래프가 MST임.
 * 
 *  List에 담아서 정렬하는 것보다 Priority Queue에 담는게 훨씬 빠름.
 */

public class Main {
    
    static int n,m;
    static int[] parent;
    static List<Edge>list = new ArrayList<>();
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge>{
        int u,v,w;
        public Edge(int uu, int vv, int ww){
            u =uu;
            v =vv;
            w =ww;
        }
        
        @Override
        public int compareTo(Edge o1){
            if(this.w == o1.w) return o1.u - o1.v;
            else return this.w - o1.w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n+1];

        StringTokenizer st;
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            //list.add(new Edge(a,b,c));
            pq.add(new Edge(a,b,c));
        }

        // 1. 비용에 따라 정렬 및 루트 초기화
        //Collections.sort(list);
        for(int i =1; i<=n; i++) parent[i] = i;

        // 2. 각각의 연결 살피면서 union관계인지 확인.
        int result = 0;
        int cnt = 0;
        // for(Edge e : list){
        //     if(cnt == n-1) break;
        //     if(union(e.u, e.v)){
        //         result += e.w;
        //         cnt++;
        //     }
        // }

        while(!pq.isEmpty()){
            if(cnt == n-1) break;
            Edge e = pq.poll();
            if(union(e.u, e.v)){
                result += e.w;
                cnt++;
            }
        }

        System.out.println(result);
    }

     

    static int find(int n){
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if( a == b ) return false;
        if( a < b) parent[b] = a;
        else parent[a] = b;
        return true;
    }
}


