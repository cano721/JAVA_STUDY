package day220414;
import java.util.*;
import java.io.*;

public class BOJ_19238_스타트택시 {
    static class Taxi {
        int x, y, move;

        Taxi(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    static class Passenger {
        int id, sx, sy, ex, ey;

        Passenger() { }
    }

    static int N, M, fuel;
    static int[][] arr = new int[21][21];
    static Taxi taxi;
    static Passenger taken = null;
    static Map<Integer, Passenger> passMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j < N + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Taxi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            Passenger p = new Passenger();
            p.id =  i + 2;  // 벽이 1 이라서 2 부터 넘버링
            p.sx = Integer.parseInt(st.nextToken());
            p.sy = Integer.parseInt(st.nextToken());
            p.ex = Integer.parseInt(st.nextToken());
            p.ey = Integer.parseInt(st.nextToken());

            passMap.put(p.id, p);

            // 출발지는 겹치지 않기 때문에 맵에 기록
            arr[p.sx][p.sy] = p.id;
        }

        // solution
        solution();
    }

    // 모든 승객을 데려다 줄때까지 BFS 를 반복하며 연료의 양을 확인한다.
    static void solution() {
        while (!passMap.isEmpty()) {
            int toStartFuel = bfs();
            fuel -= toStartFuel;

            if (fuel < 0) break;

            int toDestFuel = bfs();
            fuel -= toDestFuel;

            if (fuel < 0) break;

            fuel += toDestFuel * 2;
        }

        System.out.println(fuel < 0 ? -1 : fuel);
    }

    static int bfs() {
        Queue<Taxi> q = new LinkedList<>();
        Queue<Passenger> candidates = new LinkedList<>();
        boolean[][] visited = new boolean[21][21];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int prevMove = taxi.move;
        visited[taxi.x][taxi.y] = true;
        q.add(taxi);

        while (!q.isEmpty()) {
            Taxi now = q.poll();

            // 이동 중에 연료가 떨어지면 종료
            if (fuel - now.move < 0) {
                return Integer.MAX_VALUE;
            }

            // 택시 이동 시간대가 다르고 candidates 가 이미 존재하면 break
            if (prevMove != now.move && !candidates.isEmpty()) {
                break;
            }

            prevMove = now.move;

            if (taken == null) {
                // 택시가 비어있는 경우 가장 가까운 승객 후보를 만나면 candidates 에 추가
                int id = arr[now.x][now.y];

                if (id > 1) {
                    Passenger p = passMap.get(id);
                    candidates.add(p);
                }
            } else {
                // 택시에 승객이 있는 경우 도착지를 만나면 종료
                if (now.x == taken.ex && now.y == taken.ey) {
                    passMap.remove(taken.id);
                    taken = null;
                    taxi = new Taxi(now.x, now.y, 0);
                    return prevMove;
                }
            }

            // 동서남북 이동
            for (int i = 0 ; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 < nx && nx < N+1 && 0 < ny && ny < N+1) {
                    if (arr[nx][ny] != 1 && visited[nx][ny] == false) {
                        q.add(new Taxi(nx, ny, now.move + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        // while 문이 끝났는데 candidates 에 아무것도 없으면 벽에 막혀서 도달못함
        if (candidates.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        taken = findNearest(candidates);
        taxi = new Taxi(taken.sx, taken.sy, 0);
        arr[taken.sx][taken.sy] = 0;
        return prevMove;
    }

    // 같은 거리면 x 가 작고, y 가 작은 사람으로
    static Passenger findNearest(Queue<Passenger> q) {
        Passenger target = q.poll();

        while (!q.isEmpty()) {
            Passenger compare = q.poll();

            if (compare.sx < target.sx) {
                target = compare;
            } else if (compare.sx == target.sx && compare.sy < target.sy) {
                target = compare;
            }
        }

        return target;
    }
}