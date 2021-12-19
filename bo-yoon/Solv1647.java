import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int x, y, cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}


public class Solv1647 {
    static int N, M;
    static int graph[];
    static ArrayList<Integer> res;
    static ArrayList<Node> list;
    static int ans =0;


    static int find(int x) {
        if (graph[x] == x) return x;
        else return graph[x] = find(graph[x]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a < b) graph[b] = a;
        else graph[a] = b;
    }

    static boolean findUnion(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) return true;
        else return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        list = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        // input
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Node(a,b,c));
        }


        // fill graph
        graph = new int[N+1];
        res = new ArrayList<>();
        for(int i = 0 ; i <= N; i++) {
            graph[i] = i;
        }

        Collections.sort(list);

        for(int i = 0 ; i < list.size()  ; i++) {
            if(!findUnion(list.get(i).x, list.get(i).y)) {

                res.add(list.get(i).cost);
                union(list.get(i).x, list.get(i).y);

            }
        }

        Collections.sort(res);
        for(int i = 0 ; i < res.size() -1  ; i++) {
            ans += res.get(i);
        }


        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();



    }
}

