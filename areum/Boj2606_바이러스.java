import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2606_바이러스 {
    static int N, M, ans;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 컴퓨터 수 = 정점의 수
        M = Integer.parseInt(br.readLine()); // 컴퓨터 쌍의 수 = 간선의 수

        graph = new int[N+1][N+1];
        visited = new boolean[N+1];

        StringTokenizer st = null;
        for(int i=0; i<M; i++) { // 연결 정보 저장
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        dfs(1);
        System.out.println(ans-1);
    }

    static void dfs(int st) {
        if(visited[st]) return;

        visited[st] = true;
        ans++;

        for(int i=0; i<graph[st].length; i++) { // 그래프 탐색
            if(graph[st][i]==1 && !visited[i]) { // 연결된 정점이면서 방문하지 않은 경우
                dfs(i);
            }
        }
    }
}
