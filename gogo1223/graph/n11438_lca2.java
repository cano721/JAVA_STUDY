package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/* 블로그 참조
 * https://pangtrue.tistory.com/262 
 * */
public class n11438_lca2 {
	private static int N, M, K;
    private static List<List<Integer>> tree;
    private static int[] depth;
    private static int[][] parents;
 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
 
        tree = new ArrayList<>();
        for (int i = 0; i < N + 1; i++)
            tree.add(new ArrayList<>());
 
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
 
        int tmp = 1;
        K = 0;
        while (tmp <= N) { // 최대 depth 알아내기.
            tmp <<= 1;
            K++;
        }
 
        depth = new int[N + 1];
        parents = new int[N + 1][K];
 
        dfs(1, 1);
        fillParents();
 
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
 
            int lca = lca(a, b);
            bw.write(lca + "\n");
        }
 
        bw.flush();
        bw.close();
        br.close();
    }
 
    private static int lca(int a, int b) {
        if (depth[a] < depth[b]) { // 깊이가 낮은 쪽을 기준으로 맞춘다.
            int temp = a;
            a = b;
            b = temp;
        }
 
        //더 깊은 a를 2승씩 점프하며 두 노드의 depth를 맞춘 후, 맞춘 depth의 조상 노드로 대체한다.
        for (int i = K - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parents[a][i]; // a를 2^i 번 째 조상 노드로 대체한다.
            }
        }
 
        // depth 맞춘 후 대체한 조상 노드가 b와 같다면. 즉, a의 조상노드가 b라면 종료한다.
        if (a == b) return a;
 
        // 이제 두 노드는 같은 depth를 가지기 때문에
        // 같이 2승씩 점프시키며 공통부모 바로 아래까지 올린다.
        for (int i = K - 1; i >= 0; i--) {
            if (parents[a][i] != parents[b][i]) {
                a = parents[a][i];
                b = parents[b][i];
            }
        }
 
        return parents[a][0];
    }
 
    private static void fillParents() {
        for (int i = 1; i < K; i++) { // DP를 이용해 각 노드별 2^K 번 째 조상 노드를 저장한다.
            for (int j = 1; j <= N; j++) {
                parents[j][i] = parents[ parents[j][i - 1] ][i - 1];
            }
        }
    }
 
    private static void dfs(int node, int cnt) {
        depth[node] = cnt;
 
        for (Integer next : tree.get(node)) {
            if (depth[next] == 0) {
                dfs(next, cnt + 1);
                parents[next][0] = node;
            }
        }
    }
}
