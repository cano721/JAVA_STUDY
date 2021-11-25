/*
    전봇대 a b 둘 중 한개의 전봇대를 기준으로 정렬

    1번예시를 A 기준으로 정렬

    A 1 2 3 4 6 7 9 10
    B 8 2 9 1 4 6 7 10

    a기준 1,2,3 을 제거한다면 나머지가 오름차순으로 증가하기때문에 교차하는게 없다.
    LIS로 가능개수를 파악 가능
    
    dp[현재전선] = 최대 설치 가능 개수

    현재전선을 기준으로 이전전선을 확인하여 설치 가능한지 확인
    
    for(현재 전선 : i){
        for(이전 전선 : j){
            현재 전선이 이전 전선보다 뒤에 설치되면
            이전전선 설치개수 +1
            dp[i] = Math.max(dp[i],dp[j] +1)
        }
    }
    ...

    dp[1] = 1
    dp[2] = 1 (2-2, 이전은 1-8 밖에 없으므로 2보다 8이커서 1개 그대로)
    dp[3] = max(dp[2] +1,dp[1]+1) = 2 (2-2 나 1-8 에서 3-9 추가 설치 가능)
    dp[4] = 1 (4-1이므로 이전에 가능한게 없음)
    dp[5] = max(dp[2]+1, dp[4]+1) = 2 (6-4 이전 2-2만이나 4-1 가능)
    ...
    
    1번 예제를 기준으로
    최종적으로 간선이 꼬이지 않고 설치될 수 있는 개수는 5개임.
    총 전기선 8개에서 3개만 설치 불가능하다는 뜻으로 3개를 없애면 된다는 뜻
    없애야하는 개수(3) = 전선총개수(8) - 설치가능개수(5)

*/

import java.util.*;
import java.io.*;

public class BJ2565_전깃줄 {

    public static int n;
    public static int[] dp;
    public static int[][] arr;
    public static String scode;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new int[n];
        
        // 배열 채우기
        // a전봇대 전선위치 0 b전봇대 전선위치 1
        arr = new int[n][2];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // a전봇대를 기준으로 오름차순 정렬
        Arrays.sort(arr,new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        
        int max = 0;
        for(int i = 0; i < n; i++){
            topDown(i);
        }
        // bottomUp();
        for(int v: dp){
            max = Math.max(v,max);
        }

        // 최대 설치 개수

        // 없애야하는 개수
        int answer = n -max;
        bw.write(answer+"\n");
        bw.flush();
        bw.close();
    }

    public static int topDown(int e){

        // 값 구한적 있으면 리턴
        if(dp[e] != 0){
            return dp[e];
        }
        // 1개는 무조건 가능하니 초기화
        dp[e] = 1;

        // 이전 전깃줄 확인
        for(int i = 0; i < e; i++){
            // 현재 전깃줄 추가 설치 가능하면
            if(arr[e][1] > arr[i][1]){
                // 이전 전깃줄 +1
                dp[e] = Math.max(dp[e],topDown(i)+1);
            }
        }
        return dp[e];
    }

    public static void bottomUp(){

        // 현재 전깃줄
        for(int i = 0; i < n; i++){
            // 1개씩은 다 이을 수 있으니 초기화
            dp[i] = 1;
            // 이전 전깃줄들 확인하기
            for(int j = 0; j< i; j++){
                // 현재 전깃줄이 이전 전깃줄에서 추가 설치 가능하면
                if(arr[i][1] > arr[j][1]){
                    // 이전 전깃줄 가능개수 +1
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
    }
}
