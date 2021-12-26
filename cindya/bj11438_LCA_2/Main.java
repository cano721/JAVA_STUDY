package cindya.bj11438_LCA_2;

import java.io.*;
import java.util.*;

public class Main {
    private static int[] depth;
    private static int[][] parents;
    private static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()), m, totalDepth = 18;
        parents = new int[n + 1][totalDepth];
        list = new List[n + 1];
        depth = new int[n + 1];

        for(int i = 0; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        setTree(1);
        getParents(totalDepth);


        m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            bw.write(findCommonParent(a, b, totalDepth) + "\n");
        }
        br.close();
        bw.close();
    }

    private static int setTree(int start){
        Queue<Integer> q = new LinkedList<>();
        int cnt = 1;

        q.offer(1);
        depth[1] = cnt++;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int p = q.poll();
                for (int child : list[p]) {
                    if (depth[child] == 0) {
                        parents[child][0] = p;
                        depth[child] = cnt;
                        q.offer(child);
                    }
                }
            }
            cnt++;
        }
        return cnt;
    }

    private static void getParents(int d){
        for(int i = 1; i < d; i++)
            for(int j = 1; j < parents.length; j++)
                parents[j][i] = parents[parents[j][i - 1]][i - 1];
    }

    private static int findCommonParent(int a, int b, int d){
        if(depth[a] < depth[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = d - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parents[a][i];
            }
        }
//        while(depth[a] > depth[b])
//            a = parents[a][(int)(Math.sqrt(depth[a] - depth[b])) - 1];

        if(a == b) return a;
        for(int i = d - 1; i >= 0; i--){
            if(parents[a][i] != parents[b][i]){
                a = parents[a][i];
                b = parents[b][i];
            }
        }
        return parents[a][0];
    }
}
