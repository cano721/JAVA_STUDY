import java.util.Arrays;

class Solution {

    static int[][] d;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 20000000;

        d = new int[n][n];
        for (int t[] : d) {
            Arrays.fill(t, 20000000);
        }

        for (int i = 0; i < fares.length; ++i) {
            int start = fares[i][0] - 1;
            int end = fares[i][1] - 1;

            d[start][end] = fares[i][2];
            d[end][start] = fares[i][2];
        }

        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i == j) {
                        d[i][j] = 0;
                    } else {
                        d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                    }
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            answer = Math.min(answer, d[s - 1][i] + d[i][a - 1] + d[i][b - 1]);
        }

        return answer;
    }
}