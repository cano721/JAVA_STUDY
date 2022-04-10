import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] map;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int d1 =1; d1<=N;d1++){
            for(int d2 = 1; d2<=N;d2++){
                for(int x =1 ;x<N;x++){
                    for(int y=1;y<N;y++){
                        if((x+d1+d2<=N) && (y-d1>=1) && (y+d2<=N))
                            divide(x,y,d1,d2);
                    }
                }
            }
        }
        System.out.println(result);
    }

    private static void divide(int x, int y, int d1, int d2) {
        int[] sectionSum = new int[6];
        int[][] section = new int[N+1][N+1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (r < x + d1 && c <= y) {
                    section[r][c] = 1;
                }
                else if (r<=x+d2 && y<c) {
                    section[r][c] = 2;
                }
                else if (x + d1<=r && c < y - d1 + d2) {
                    section[r][c] = 3;
                }
                else if (x + d2 < r && y - d1 + d2 <= c) {
                    section[r][c] = 4;
                }
            }
        }
        for (int i = 0; i <= d1; i++) {
            section[x + i][y - i] = 5;
            section[x + d2 + i][y + d2 - i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            section[x + i][y + i] = 5;
            section[x + d1 + i][y - d1 + i] = 5;
        }
        for (int i = 0; i < d2; i++) {
            int t = 1;
            while (section[x + i + t][y + i] != 5) {
                section[x + i + t][y + i] = 5;
                t++;
            }
        }
        for (int i = 0; i < d1; i++) {
            int t = 1;
            while (section[x + i + t][y - i] != 5) {
                section[x + i + t][y - i] = 5;
                t++;
            }
        }
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                sectionSum[section[r][c]] += map[r][c];
            }
        }
        Arrays.sort(sectionSum);
        result = Math.min(result,sectionSum[5] - sectionSum[1]);
    }
}
