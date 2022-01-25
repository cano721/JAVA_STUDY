import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(br.readLine());
        int[][] arr = new int[n+1][2];
        for (int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i=2; i<=n; i++) {
            dp[i] = 1;
            for (int j=1; j<i; j++) {
                if (arr[i][1] > arr[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int maxlen = 0;
        for (int i=1; i<=n; i++) {
            maxlen = Math.max(maxlen, dp[i]);
        }

        System.out.println(n - maxlen);
    }
}