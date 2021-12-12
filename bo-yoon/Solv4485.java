import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Node implements Comparable<Node> {
    int x;
    int y;
    int rupee;

    public Node(int x, int y, int rupee) {
        this.x = x;
        this.y = y;
        this.rupee = rupee;
    }

    @Override
    public int compareTo(Node o) {
        return this.rupee - o.rupee;
    }
}


public class Solv4485 {

    static int N;
    static int[][] Map;
    static int[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        String input = null;

        int cnt = 0;


        while (!(input = br.readLine()).equals("0")) {
            cnt++;
            N = Integer.parseInt(input);
            Map = new int[N][N];


            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    Map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cost = solve();
            sb.append("Problem " + cnt + ": " + cost + "\n");

        }
        System.out.println(sb.toString());
    }

    static int solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        pq.add(new Node(0, 0, Map[0][0]));
        visited[0][0] = Map[0][0];

        while (!pq.isEmpty()) {
            Node pos = pq.poll();
            int curX = pos.x, curY = pos.y;
            int curRupee = pos.rupee;

            if (curX == N - 1 && curY == N - 1) {
                return curRupee;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) continue;

                if (curRupee + Map[ny][nx] < visited[ny][nx]) {
                    visited[ny][nx] = curRupee + Map[ny][nx];
                    pq.add(new Node(nx, ny, curRupee + Map[ny][nx]));
                }

            }
        }
        return -1;
    }
}
