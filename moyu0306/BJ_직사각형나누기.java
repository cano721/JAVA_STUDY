import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] rectangle = new int[n][m];

        for(int i = 0; i < n; i++) {
            char[] temp = br.readLine().toCharArray();

            for(int j = 0; j < m; j++) {
                rectangle[i][j] = temp[j] - '0';
            }
        }

        long max = 0;

        for(int i = 1; i < n; i++) {
            long a = getSum(0, i, 0, m, rectangle);

            for(int j = 1; j < m; j++) {
                long b = getSum(i, n, 0, j, rectangle);
                long c = getSum(i, n, j, m, rectangle);

                long tmp = a * b * c;

                if(max < tmp) {
                    max = tmp;
                }
            }

            for(int j = i + 1; j < n; j++) {
                long b = getSum(i, j, 0, m, rectangle);
                long c = getSum(j, n, 0, m, rectangle);

                long tmp = a * b * c;

                if(max < tmp) {
                    max = tmp;
                }
            }
        }

        for(int i = n - 1; i > 0; i--) {
            long a = getSum(i, n, 0, m, rectangle);

            for(int j = 1; j < m; j++) {
                long b = getSum(0, i, 0, j, rectangle);
                long c = getSum(0, i, j, m, rectangle);

                long tmp = a * b * c;

                if(max < tmp) {
                    max = tmp;
                }
            }
        }

        for(int i = 1; i < m; i++) {
            long a = getSum(0, n, 0, i, rectangle);

            for(int j = 1; j < n; j++) {
                long b = getSum(0, j, i, m, rectangle);
                long c = getSum(j, n, i, m, rectangle);

                long tmp = a * b * c;

                if(max < tmp) {
                    max = tmp;
                }
            }

            for(int j = i + 1; j < m; j++) {
                long b = getSum(0, n, i, j, rectangle);
                long c = getSum(0, n, j, m, rectangle);

                long tmp = a * b * c;

                if(max < tmp) {
                    max = tmp;
                }
            }
        }

        for(int i = m - 1; i > 0; i--) {
            long a = getSum(0, n, i, m, rectangle);

            for(int j = 1; j < n; j++) {
                long b = getSum(0, j, 0, i, rectangle);
                long c = getSum(j, n, 0, i, rectangle);

                long tmp = a * b * c;

                if(max < tmp) {
                    max = tmp;
                }
            }
        }

        System.out.println(max);
    }

    private static long getSum(int y1, int y2, int x1, int x2, int[][] rectangle) {
        long sum = 0;

        for(int i = y1; i < y2; i++) {
            for(int j = x1; j < x2; j++) {
                sum += rectangle[i][j];
            }
        }

        return sum;
    }
}