import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int mod = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = input.length();
        
        int[] dp = new int[n + 1];
        char[] ch = new char[n + 1];
        
        for(int i = 1; i <= n; i++) {
            ch[i] = input.charAt(i-1);
        }

        dp[0] = 1;
        
        for(int i = 1; i <= n; i++) {
            int num = ch[i] - '0';
            if(1 <= num && num <= 9) {
                dp[i] = dp[i-1];
                dp[i] %= 1000000;
            }
            if(i == 1) continue;
            
            num = (ch[i-1] - '0') * 10 + (ch[i] - '0');
            
            if(10 <= num && num <= 26) {
                dp[i] += dp[i-2];
                dp[i] %= mod;
            }
        }
        System.out.println(dp[n]);
    }
}