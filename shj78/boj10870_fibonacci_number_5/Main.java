import java.util.Scanner;
import java.util.Arrays;
 
public class Main {
    
    static int dp[];
    
    public static int f(int k) {
        if(k == 0) return 0;
        if(k == 1) return 1;
        if(dp[k] != -1) return dp[k];
        
        int ret = f(k-1) + f(k-2);
        dp[k] = ret;
        return ret;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        
        System.out.println(f(n));
    }
}