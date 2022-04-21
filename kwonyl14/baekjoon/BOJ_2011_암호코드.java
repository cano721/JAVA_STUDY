import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        System.out.println(solution(str));
    }
    public static int solution(String str){
        char[] charArr = str.toCharArray();
        int N = charArr.length;
        if(N==0 || charArr[0]=='0') return 0;
        int[] dp = new int[N];
        dp[0] = 1;
        if(N==1) return 1;

        int left = Integer.parseInt(Character.toString(charArr[0]));
        int right = Integer.parseInt(Character.toString(charArr[1]));

        if(left>2 && right==0) return 0;

        int tmp = left*10 + right;
        if (tmp>=10 && tmp<=26 && right>0) dp[1] = 2;
        else dp[1] = 1;

        for(int i=2;i<N;i++){
            left = Integer.parseInt(Character.toString(charArr[i-1]));
            right = Integer.parseInt(Character.toString(charArr[i]));

            if(left==0 && right==0) return 0;
            else if(left>2 && right==0) return 0;
            if(right!=0) dp[i] += dp[i-1]%1000000;

            tmp = left*10 + right;
            if(tmp>=10 && tmp<=26) dp[i] += dp[i-2]%1000000;

            dp[i] = dp[i]%1000000;
        }
        return (dp[N-1]%1000000);
    }
}