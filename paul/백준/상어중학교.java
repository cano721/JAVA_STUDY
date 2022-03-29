import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int y, x, cnt, rbCnt;

        public Node(int yy, int xx, int cc, int ccc) {
            y = yy;
            x = xx;
            cnt = cc;
            rbCnt = ccc;
        }

        public int compareTo(Node o) {
            if (this.cnt == o.cnt) {
                if (this.rbCnt == o.rbCnt) {
                    if (this.y == o.y) {
                        return o.x - this.x;
                    }
                    return o.y - this.y;
                }
                return o.rbCnt - this.rbCnt;
            }
            return o.cnt - this.cnt;
        }
    }

    static int N, M, by, bx, blockCnt, rbCnt, answer;
    static int[][] map, temp;
    static boolean[][][] visited;
    static List<Node> list;
    static Queue<Integer> q;

    static int[][] dir = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    public static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[21][21];
        temp = new int[21][21];
        visited = new boolean[6][21][21];

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void pro() {
        list = new ArrayList<>();
        q = new LinkedList<>();

        while (true) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    // 일반 블록 x
                    if (map[i][j] <= 0 || visited[map[i][j]][i][j])
                        continue;

                    by = i;
                    bx = j;
                    blockCnt = 0;
                    rbCnt = 0;
                    dfs(i, j, map[i][j]);

                    if (blockCnt + rbCnt >= 2) {
                        list.add(new Node(by, bx, blockCnt, rbCnt));
                    }
                }
            }

            
            if (list.isEmpty())
                break;

            Collections.sort(list);

            Node node = list.get(0);
            answer += node.cnt * node.cnt;
            list.clear();

            remove(node);
            down();
            rotate();
            down();
            init();
        }
        System.out.println(answer);

    }

    public static void init() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                for (int k = 1; k <= M; ++k) {
                    visited[k][i][j] = false;
                }
            }
        }
    }

    
    public static void dfs(int y, int x, int v) {
        blockCnt++;
        visited[v][y][x] = true;

        if (map[y][x] == 0) {
            rbCnt++;
        } else {
            
            if (y <= by) {
                if (y < by) {
                    by = y;
                    bx = x;
                } else {
                    if (x < bx) {
                        by = y;
                        bx = x;
                    }
                }
            }
        }

        for (int d = 0; d < 4; ++d) {
            int ny = y + dir[d][0];
            int nx = x + dir[d][1];

            if (ny < 0 || nx < 0 || ny >= N || nx >= N)
                continue;
            if (map[ny][nx] <= -1 || visited[v][ny][nx])
                continue;
            if (map[ny][nx] == 0 || map[ny][nx] == v) {
                dfs(ny, nx, v);
            }
        }
    }

    
    public static void remove(Node node) {
        int v = map[node.y][node.x];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                visited[v][i][j] = false;
            }
        }

        dfs(node.y, node.x, v);

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (visited[v][i][j]) {
                    map[i][j] = -2;
                }
            }
        }
    }

    public static void down() {
        for (int j = 0; j < N; ++j) {
            int y = N - 1;
            for (int i = N - 1; i >= 0; --i) {
                if (map[i][j] == -1) {
                    while (!q.isEmpty()) {
                        map[y--][j] = q.poll();
                    }
                    while (y > i) {
                        map[y--][j] = -2;
                    }
                    y--;
                } else {
                    if (map[i][j] != -2) {
                        q.offer(map[i][j]);
                    }
                }
            }

            while (!q.isEmpty()) {
                map[y--][j] = q.poll();
            }
            while (y >= 0) {
                map[y--][j] = -2;
            }
        }
    }

    
    public static void rotate() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                temp[N - j - 1][i] = map[i][j];
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                map[i][j] = temp[i][j];
            }
        }
    }
}