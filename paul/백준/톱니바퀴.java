import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int y, x;

        public Pair(int yy, int xx) {
            y = yy;
            x = xx;
        }
    }

    static String[] gears = new String[4];
    static int k, ans;
    static boolean[] vis = new boolean[4];
    static List<Pair> orders = new ArrayList<>();
    static Pair[] pointers = new Pair[4];
    static int[] dx = { 1, -1 };

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            gears[i] = br.readLine();
        }
        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            Pair order = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            orders.add(order);
        }

        // 기어 맞닿는 부분
        for (int i = 0; i < 4; i++) {
            pointers[i] = new Pair(6, 2);
        }
    }

    // numbers = 돌릴 기어 번호, direct = 방향
    static void rotateGear(int number, int direct) {
        if (direct == 1) {
            pointers[number].y--;
            pointers[number].x--;
            if (pointers[number].y == -1)
                pointers[number].y = 7;
            if (pointers[number].x == -1)
                pointers[number].x = 7;
        } else {
            pointers[number].y = (pointers[number].y + 1) % 8;
            pointers[number].x = (pointers[number].x + 1) % 8;
        }
    }

    static void pro() {

        for (int i = 0; i < k; i++) {
            int gearNumber = orders.get(i).y - 1;
            int rotateDir = orders.get(i).x;

            // 돌아갈 수 있는 gear 찾아.
            Queue<Integer> q = new LinkedList<>();
            Arrays.fill(vis, false);

            q.add(gearNumber);
            vis[gearNumber] = true;

            while (!q.isEmpty()) {
                int n = q.poll();

                for (int j = 0; j < 2; j++) {
                    int cn = dx[j] + n;
                    if (cn < 0 || cn > 3 || vis[cn])
                        continue;
                    if (n < cn) {
                        if (gears[n].charAt(pointers[n].x) != gears[cn].charAt(pointers[cn].y)) {
                            vis[cn] = true;
                            q.add(cn);
                        }
                    }
                    if (n > cn) {
                        if (gears[n].charAt(pointers[n].y) != gears[cn].charAt(pointers[cn].x)) {
                            vis[cn] = true;
                            q.add(cn);
                        }
                    }
                }
            }

            for (int j = 0; j < 4; j++) {
                // 기어 돌려
                if (vis[j]) {
                    if (j == gearNumber)
                        rotateGear(j, rotateDir);
                    else if (Math.abs(j - gearNumber) == 2)
                        rotateGear(j, rotateDir);
                    else {
                        rotateGear(j, -rotateDir);
                    }
                }
            }
        }

        // 기어의 점수 구하기.
        for (int i = 0; i < 4; i++) {
            int index = pointers[i].x;
            if (index == 0)
                index = 6;
            else if (index == 1)
                index = 7;
            else
                index -= 2;

            if (gears[i].charAt(index) == '1') {
                ans += (1 << i);
            }
        }

        System.out.println(ans);
    }
}