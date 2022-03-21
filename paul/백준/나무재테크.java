import java.io.*;
import java.util.*;

public class Main {
    // n*n , m : 나무의 수, k년도,
    static int n, m, k, ans;
    // 양분을 저장
    static int[][] board;
    // 겨울에 추가할 에너지
    static int[][] energy;
    static int[][] dydx = {
            { -1, -1 },
            { -1, 0 },
            { -1, 1 },
            { 0, -1 },
            { 0, 1 },
            { 1, -1 },
            { 1, 0 },
            { 1, 1 }
    };
    static ArrayList<Integer>[][] tree = new ArrayList[11][11];

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        energy = new int[n][n];
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                energy[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = 5;
                tree[i][j] = new ArrayList<Integer>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a - 1][b - 1].add(c);
            ans++;
        }

    }

    static void pro() {
        // k년씩 시물레이션 돌리기.
        for (int i = 0; i < k; i++) {
            ss();
            fw();
        }
        System.out.println(ans);
    }

    // 봄 여름 한사이클
    static void ss() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int size = tree[i][j].size();

                // 영역에 나무 존재하면
                if (size > 0) {
                    Collections.sort(tree[i][j]);
                    ArrayList<Integer> temp = new ArrayList<>();
                    // 어린 나무부터 나이만틈 영양분 빨아먹기
                    int e = board[i][j];
                    boolean flag = true;

                    for (int r = 0; r < size; r++) {
                        // 양분이 나이보다 많으면 빨아먹고 나이 증가.
                        if (e >= tree[i][j].get(r) && flag) {
                            e = e - tree[i][j].get(r);
                            tree[i][j].set(r, tree[i][j].get(r) + 1);

                            temp.add(tree[i][j].get(r));
                        } else {
                            flag = false;
                            // 양분이 적으면 나무 죽고 저장
                            e += tree[i][j].get(r) / 2;
                            // k부터 양분 다 저장하고 죽여야해.
                            ans--;
                        }
                    }
                    // 양분 최신화,
                    board[i][j] = e;

                    if (flag)
                        continue;
                    // 죽은 나무가 존재하면 나무 정보 최신화.
                    tree[i][j].clear();
                    tree[i][j] = temp;
                }

            }
        }

    }

    // 가을 겨울 한 사이클.
    static void fw() {

        // 가을 나무 번식
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int size = tree[i][j].size();
                if (size > 0) {
                    for (int r = 0; r < size; r++) {

                        // 나무별로 나이가 5로 나눠떨어지면 1인 나무 뿌리기.
                        int age = tree[i][j].get(r);
                        if (age % 5 == 0) {
                            for (int t = 0; t < 8; t++) {
                                int cy = dydx[t][0] + i, cx = dydx[t][1] + j;
                                // 범위 벗아나면 추가 안해
                                if (cy < 0 || cy >= n || cx < 0 || cx >= n)
                                    continue;
                                tree[cy][cx].add(1);
                                ans++;
                            }
                        }
                    }
                }

                // 겨울
                board[i][j] += energy[i][j];
            }
        }
    }

}
