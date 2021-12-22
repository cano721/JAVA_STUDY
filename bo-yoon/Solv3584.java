import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solv3584 {
    static int depth[];
    static int parent[];
    static boolean check[];

    static int N, M;
    static ArrayList<Integer>[] list;

    static int lca(int a, int b) {
        int aDepth = depth[a];
        int bDepth = depth[b];
        while ( aDepth < bDepth) {
            b = parent[b];
            bDepth--;
        }
        while (aDepth > bDepth) {
            a = parent[a];
            aDepth--;
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }


    static void dfs(int cur, int dep, int par) {
        depth[cur] = dep;
        parent[cur] = par;
        for(int nextNode : list[cur]) {
            if( nextNode != par) {
                dfs(nextNode, dep + 1, cur);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        StringBuilder ans = new StringBuilder();

        N = Integer.parseInt(br.readLine());


        while (N --> 0) {
            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            list = new ArrayList[M + 1];
            parent = new int[M + 1];
            depth = new int [M + 1];
            check = new boolean[M + 1];
            Arrays.fill(depth,-1);


            for(int i = 0 ; i < M + 1; i++) {
                list[i]= new ArrayList<>();
            }

            for(int i = 1; i < M -1 ; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                parent[to] = from;
                check[to] = true;
                list[from].add(to);
            }
            int root = -1;
            for(int i = 1; i <= M; i++) {
                if (!check[i]) root = i;
            }

            dfs(root,1,0);
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());


            int res = lca(node1, node2);

            ans.append(res + "\n");


        }

        bw.write(ans.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

