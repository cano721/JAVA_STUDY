package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2644_촌수계산 {
    static int N, a, b, ans = -1;
    static int dist[];
    static ArrayList<Integer> relation[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        relation = new ArrayList[N+1]; // 관계 정보
        dist = new int[N+1]; // 촌수
        Arrays.fill(dist, -1); // -1로 초기화

        for (int i = 1; i <= N; i++)
            relation[i] = new ArrayList<Integer>();

        st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) { // 관계 정보 저장
            st = new StringTokenizer(br.readLine(), " ");
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            relation[parent].add(child);
            relation[child].add(parent);
        }

        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        dist[a] = 0;
        q.add(a);

        while(!q.isEmpty()) {
            int now = q.poll(); // 확인할 사람 queue 에서 뺀 후
            // 비교 대상을 찾으면 촌수 저장
            if(now == b) {
                ans = dist[now];
                break;
            }

            // 해당 사람과 관계있는 사람들을 확인
            for (int i = 0; i < relation[now].size(); i++) {
                int next = relation[now].get(i);

                // 이미 촌수를 확인한 사람이면 continue;
                if(dist[next] != -1) continue;

                // 다음 관계는 현재까지 촌수 + 1
                dist[next] = dist[now] + 1;
                q.add(next);
            }
        }
    }
}
