import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int[] dp = new int[k+1];
      
    for (int i = 0; i < n; i++) {
      int coin = Integer.parseInt(br.readLine());
      for (int j = 1; j <= k; j++) {
        if (j - coin > 0) {
          dp[j] = dp[j] + dp[j-coin];
        } else if (j - coin == 0) {
          dp[j]++;
        }
      }
    }
    System.out.print(dp[k]);
  }
}