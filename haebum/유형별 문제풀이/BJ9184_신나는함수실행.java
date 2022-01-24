/*
    저장하지 않았던 값이면 새로 구하기
    이미 구한 값이라면 dp에서 불러내기

    탑다운 방식으로 진행
    
    
*/

import java.util.*;
import java.io.*;

public class BJ9184_신나는함수실행 {

    public static Integer[][][] dp = new Integer[101][101][101];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1 && c == -1){
                break;
            }
            int num = w(a,b,c);
            bw.write("w(" + a + ", " + b + ", " + c + ") = " +num +"\n");
        }
        bw.flush();
        bw.close();
    }

    //탑다운방식
    public static int w(int i,int j, int k){

        int ni = i+50;
        int nj = j+50;
        int nk = k+50;

        if(dp[ni][nj][nk] != null){
            return dp[ni][nj][nk];
        }
        if(i <= 0 || j <= 0 || k <= 0) return 1;
        
        else if(i > 20 || j > 20 || k > 20){
            dp[ni][nj][nk] = w(20,20,20);
        }
        else if(i < j && j < k){
            dp[ni][nj][nk] = w(i,j,k-1) + w(i,j-1,k-1) - w(i,j-1,k);
        }
        else{
            dp[ni][nj][nk] = w(i-1,j,k) + w(i-1,j-1,k) + w(i-1,j,k-1) - w(i-1,j-1,k-1);
        }

        return dp[ni][nj][nk];
    }
}
