import java.util.*;
import java.io.*;

public class Main {

    /**
     *  2가지 모양일 때를 생각해서 계산한다.
     *  1. 세로로 긴 것을 놓았을 때, 나머지 n-1을 놓는 경우의 수와 같음.
     *  2. 가로로 긴 것을 놓았을 때, 나머지 n-2을 놓는 경우의 수와 같음.
     */ 
    public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine());
       final int MOD = 10007;

       // n+1 로 하면 1일 때 ArrayIndexOutofBound 에러남.
       int[] dp = new int[n+2];
       dp[1] = 1;
       dp[2] = 2;

       for(int i =3 ; i<=n; i++){
           dp[i] = (dp[i-2] + dp[i-1])%MOD;
       }
       System.out.println(dp[n]);
    }

    public static void input() throws Exception{
       
    }

    public static void pro(){

    }
    
    
    

}

