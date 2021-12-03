package cindya.bj14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int[] moveRow = {-1, 0, 1, 0}, moveCol = {0, 1, 0, -1};
    // private static final String[] directions = {"↑", "→", "↓", "←"}; // 과정 확인 시 방향 표시

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken()), cnt = 0;
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken(" ")), c = Integer.parseInt(st.nextToken(" ")), d = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][];

        for(int i = 0; i < n; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        while (true){
            // 현재 칸이 청소되어 있지 않으면 청소
            if(map[r][c] == 0){
                map[r][c] = 2;
                cnt++;
            }
            // 과정 확인용
//            System.out.println(directions[d]);
//            for(int i = 0; i < n; i++){
//                for(int j = 0; j < m; j++)
//                    if(i != r || j != c)
//                        System.out.print(String.format("%3d", map[i][j]));
//                    else
//                        System.out.print(String.format("[%d]", map[i][j]));
//                System.out.println();
//            }
//            System.out.println();
            int originalD = d;
            int nr, nc;
            // 이동 가능한 칸이 나오거나 한바퀴를 돌 때까지 루프
            do {
                d = (d + 3) % 4;
                nr = r + moveRow[d];
                nc = c + moveCol[d];
            }
            while (d != originalD && (0 > nr || nr >= n || 0 > nc || nc >= m || map[nr][nc] != 0));

            // (nr, nc)가 이동 가능한 좌표이면 이동하고 continue
            if(0 <= nr && nr < n && 0 <= nc && nc < m && map[r + moveRow[d]][c + moveCol[d]] == 0) {
                r = nr;
                c = nc;
                continue;
            }

            // 현재 방향의 반대 방향
            nr = r + moveRow[(d + 2) % 4];
            nc = c + moveCol[(d + 2) % 4];
            // 반대 방향이 청소한 자리면 반대로 이동
            if(0 <= nr && nr < n && 0 <= nc && nc < m && map[nr][nc] == 2) {
                r = nr;
                c = nc;
                continue;
            }
            // 이동이 불가능한 경우 청소 중지
            break;
        }
        System.out.println(cnt);

        br.close();
    }
}
