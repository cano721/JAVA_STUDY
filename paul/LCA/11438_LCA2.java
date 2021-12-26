import java.io.*;
import java.util.*;

/**
 *  블로그를 보고 문제 풀었습니다..
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n,m;
    static int[][] ancestor;
    static int[] depth;
    static boolean[] visited;
    static int maxHeight = 18; 
    static List<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        depth = new int[n+1];
        ancestor = new int[n+1][maxHeight];
        for (int i=0; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        // 루트1의 부모는 1로
        dfs(1,1,1);

        for (int i=1; i<maxHeight; i++){
            for (int j=1; j<=n; j++){
                int tmp = ancestor[j][i-1];
                ancestor[j][i] = ancestor[tmp][i-1]; // 나의 8(2^3)번째 조상은 나의 4(2^2)번째 조상의 4(2^2)번째 조상이다.
            }
        }

        m = Integer.parseInt(br.readLine());
        for (int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a, b)).append('\n');

        }
        System.out.print(sb);
    }

    static void dfs(int parent, int cur, int dep){
        if (visited[cur]) return;
        visited[cur] = true;
        depth[cur] = dep;
        ancestor[cur][0] = parent;
        for (int i=0; i<graph[cur].size(); i++){
            dfs(cur, graph[cur].get(i), dep+1);
        }
    }

    static int LCA(int a, int b){
        // a가 더 덜 깊은 경우 a와 b를 swap
        // swap을 하지않으면 아래 로직을 if문으로 분기 해야하기 때문에
        if(depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 높이 맞추기
        // 높이의 차이 만큼 a의 높이를 올림
        // 차이가 만약 5 라면 101
        // 100(i = 2) a을 4만큼 끌어 올리고
        // 그럼 차이는 1되고
        // 1(i = 0) 일때 a을 1만큼 끌어올려서 높이를 마추게 된다.
        for (int i = maxHeight - 1; i>=0; i--) {
            if (depth[a] - depth[b] >= (1 << i)) {
                a = ancestor[a][i];
            }
        }

        // 높이가 같을때 같은 경우
        if (a == b) return a;

        // 높이를 같이 올라가면서 같은 경우
        // 루트에 가장 가까운 a != b를 찾아서 변경함
        // 2^이 기록되어있기때문에 2^8에서는 같지만 2^4에서는 다를 수 있음
        // 만약 9 높이에서 같다면 16에서는 같지만 8에서는 다르기 때문에
        // 8(2^3)까지 이동한 뒤 1(2^0)을 더 움직여서 도달한다.
        for (int i = maxHeight - 1; i>=0; i--) {
            if (ancestor[a][i] != ancestor[b][i]) {
                a = ancestor[a][i];
                b = ancestor[b][i];
            }
        }
        return ancestor[a][0];
    }
}