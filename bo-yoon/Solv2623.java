import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solv2623 {

    static int N, M;
    static ArrayList<Integer> [] nodeList;
    static int [] edge;
    static Queue<Integer> q;
    static ArrayList<Integer>  res;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new ArrayList[N + 1];
        for(int i = 0 ; i <  N +1 ; i++) {
            nodeList[i] = new ArrayList<Integer>();
        }

        edge = new int[N+1];

        while( M --> 0 ) {
            st = new StringTokenizer(br.readLine());
            int t= Integer.parseInt(st.nextToken());
            int p1= Integer.parseInt(st.nextToken());

            for (int i = 1; i < t; i++) {
                int p2= Integer.parseInt(st.nextToken());
                nodeList[p1].add(p2);
                edge[p2]++;
                p1 = p2;
            }
        }

        q = new LinkedList<>();

        for(int i = 1; i < edge.length; i++) {
            if(edge[i] == 0 ){
                q.offer(i);
            }
        }

        res = new ArrayList<>();

        while (!q.isEmpty()) {
            int p1 = q.poll();

            res.add(p1);

            for(Integer i : nodeList[p1]) {
                edge[i]--;
                if(edge[i] == 0) {
                    q.offer(i);
                }
            }

        }

        if(res.size() == N ) {
            for(Integer i : res) {
                System.out.print(i + " ");
            }
        }else {
            System.out.println(0);
        }

    }
}

