import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] chess = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                chess[i][j] = str.charAt(j);
            }
        }

        int ans = Integer.MAX_VALUE;

        int temp = 0;

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {

                temp = 0;
                for (int k = i; k < i + 8; k++) {
                    for (int p = j; p < j + 8; p++) {
                        if ((k + p) % 2 == 0) { // 흰색인 부분
                            if (chess[k][p] == 'B')
                                temp++;
                        } else if ((k + p) % 2 == 1) { // 검정색인 부분
                            if (chess[k][p] == 'W')
                                temp++;
                        }
                    }
                }
                ans = Math.min(ans,temp);

                temp=0;
                for (int k = i; k < i + 8; k++) {
                    for (int p = j; p < j + 8; p++) {

                        if ((k + p) % 2 == 0) { // 검정색인 부분
                            if (chess[k][p] == 'W') //흰색이면 다시 칠해
                                temp++;
                        } else { // 흰색인 부분
                            if (chess[k][p] == 'B') // 검정이면 다시 칠해
                                temp++;
                        }

                    }
                }
                ans = Math.min(ans,temp);
            }
        }
        System.out.println(ans);
    }
}
