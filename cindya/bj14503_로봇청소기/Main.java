package cindya.bj14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int[] moveRow = {-1, 0, 1, 0}, moveCol = {0, 1, 0, -1};

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
            if(map[r][c] == 0){
                map[r][c] = 2;
                cnt++;
            }
            int originalD = d;
            int nr = r, nc = c;
            do {
                d = (d + 3) % 4;
                nr = r + moveRow[d];
                nc = c + moveCol[d];
            }
            while (0 > nr || nr >= n || 0 > nc || nc >= m || map[nr][nc] != 0 || d != originalD);

            if(map[r + moveRow[d]][c + moveCol[d]] == 0 || d != originalD) {

                continue;
            }

            originalD = (d + 1) % 4;
            while (map[r + moveRow[(d + 2) % 4]][c + moveCol[(d + 2) % 4]] != 2 && d != originalD)
                d = (d + 3) % 4;

            if(map[r + moveRow[(d + 2) % 4]][c + moveCol[(d + 2) % 4]] == 2) continue;
            break;
        }
        System.out.println(cnt);

        br.close();
    }
}
