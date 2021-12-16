import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;



class Node implements Comparable<Node> {
    int x, y, cnt;

    public Node(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node o) {
        return cnt - o.cnt;
    }
}


public class Solv1922 {

    static final int INF = 100000 +1 ;
    static int N, M;
    static int graph[];
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


    public static void main(String[] args) throws NoSuchElementException, NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());


        list = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Node(a,b,c));
        }


        Collections.sort(list);
        graph = new int[INF];
        for(int i = 1 ; i <= N; i++) {
            graph[i] = i;
        }

        for(int i = 0 ; i < list.size() ; i++) {
           if(!findUnion(list.get(i).x, list.get(i).y)) {

               ans += list.get(i).cnt;
               union(list.get(i).x, list.get(i).y);

           }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

