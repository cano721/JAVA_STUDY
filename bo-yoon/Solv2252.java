

import java.io.*;
import java.util.*;
`

public class Solv2252 {



    static int N, M;
    static ArrayList<ArrayList<Integer>> nodeList;
    static int [] edge;
    static Queue<Integer> q;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new ArrayList<ArrayList<Integer>>();
        for(int i = 0 ; i <= N +1 ; i++) {
            nodeList.add(new ArrayList<Integer>());
        }

        edge = new int[N+1];

        while( M --> 0 ) {
            st = new StringTokenizer(br.readLine());
            int p1= Integer.parseInt(st.nextToken());
            int p2= Integer.parseInt(st.nextToken());
            nodeList.get(p1).add(p2);
            edge[p2]++;
        }

        q = new LinkedList<>();

        for(int i = 1; i < edge.length; i++) {
            if(edge[i] == 0 ){
             q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int p1 = q.poll();
            ArrayList<Integer> nodes = nodeList.get(p1);

            for (int i = 0 ; i < nodes.size(); i++) {
                int tmp = nodes.get(i);
                edge[tmp]--;
                if(edge[tmp] == 0) {
                    q.offer(tmp);
                }
            }

            System.out.print(p1 + " ");
        }
    }
}

