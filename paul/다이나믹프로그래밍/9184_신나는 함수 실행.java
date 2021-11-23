import java.util.*;
import java.io.*;

/**
 * 출력형식 주의.....w 소문자.....
 */

public class Main {
    public static void main(String[] args) throws Exception {
        //input();
        //pro();
        int[][][] dp = new int[21][21][21];
        for(int i =0; i<= 20; i++){
            for(int j =0; j<= 20; j++){
                for(int k=0; k<=20; k++){
                    if(i == 0 || j ==0 || k == 0 ) {
                        dp[i][j][k] = 1;
                    }
                    else if(i < j &&  j < k){
                        dp[i][j][k] = dp[i][j][k-1] + dp[i][j-1][k-1] - dp[i][j-1][k];
                    } 
                    else{
                        dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-1][k] + dp[i-1][j][k-1] - dp[i-1][j-1][k-1];
                    }
                        
                }
            }
        }

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1 && c == -1) break;
            sb.append("w(" + a + ", " + b + ", " + c +") = ");
            int k =0; 
            if(a <= 0 || b <= 0 || c <= 0)  k = 1;
            else if (a > 20 || b > 20 || c> 20) k = dp[20][20][20];
            else k = dp[a][b][c];
            sb.append(k).append('\n');
        }
        System.out.println(sb);
    }
    
    public static void input() throws Exception{
      
    }

    public static void pro(){
       
    }
}