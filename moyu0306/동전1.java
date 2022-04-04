import java.io.*;
import java.util.*;


public class Main {
    static public int[] dp;
    static public boolean[] isVisited;
    static public ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int n = Integer.parseInt(info[0]);
        int k = Integer.parseInt(info[1]);
        dp = new int[k+1];
        isVisited = new boolean[k+1];
        list = new ArrayList<>();
        dp[0] = 1;
        for(int i=0; i< n; i++) {
            int coin = Integer.parseInt(br.readLine());
            for (int j = coin; j <= k; j++) {
                dp[j] += dp[j - coin];
            }
        }
        System.out.println(dp[k]);
    }
}