/*
    dp[정수] = 방법의수

    dp[1] = 1
    dp[2] = 2 (1+1,2)
    dp[3] = 4 (1+1+1,1+2,2+1,3)
    dp[4] = 7 (1+3, 1+1+2,2+2, 1+1+1+1,1+2+1,2+1+1,3+1)

    dp[4]를 보면 앞의 방법들에서 만들어지는걸 확인가능
    dp[1]에서 +3을통해 만들 수 있고
    dp[2]에서 +2를 dp[3]에선 +1로 만들 수 있음

    고로 아래와 같은 점화식 가능
    dp[n] = dp[n-3]+dp[n-2]+dp[n-1]


    topDown으로 돌릴려고하니 터져버림...
    스택오버플로우(vscode나 스튜디오에서 잡아둔 메모리량이 더 작은가봄 백준에선 통과)
*/

import java.util.*;
import java.io.*;

public class BJ15988_1_2_3더하기_3 {

    public static int n,div;
    public static Long[] dp = new Long[1_000_001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        div = 1_000_000_009;
        // 초기값
        dp[0] = 0l;
        dp[1] = 1l;
        dp[2] = 2l;
        dp[3] = 4l;
        
        topDown(1_000_000);
        //bottomUp(1_000_000);

        for(int tc = 0; tc < t; tc++){
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[n]+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static long topDown(int idx){
        if(dp[idx] != null){
            return dp[idx];
        }
        dp[idx] = (topDown(idx-1) + topDown(idx-2) + topDown(idx-3))%div;
        return dp[idx];
    }

    public static void bottomUp(int n){
        for(int i = 4; i <=n; i++){
            dp[i] = (dp[i-3]+dp[i-2] +dp[i-1])%div;
        }
    }
}
