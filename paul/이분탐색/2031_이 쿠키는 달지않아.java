import java.util.*;
import java.io.*;

public class Main{	

    static int t,n,d,k;
	static int[] arr, cnt;
    static int[][] dp;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        t = Integer.parseInt(st.nextToken());
        n= Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k= Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        cnt = new int[n+1];
        dp= new int[k+1][n+1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i < n+1; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }

        pro();

    }

    static void pro(){
        Arrays.sort(arr);
        
       
        // i번째 인덱스부터 d효과를 보는 최대 갯수 찾기.
        for(int i = 1; i < n+1; i++) {
        	cnt[i] = findTarget(i)-i;
        }

        for(int i=1; i<= k; i++) {
            for(int j=1; j<=n; j++) {
                int t = cnt[j];
                dp[i][j] = dp[i][j-1];
                if(j >= t) dp[i][j] = Math.max(dp[i][j-1], t + dp[i-1][j-t]);
            }
        }

        System.out.println(dp[k][n]);
    }

    // i부터 시작해서 arr[i]+d-1을 최초로 초과하는 인덱스 반환.
    static int findTarget(int i){
        int left = i;
        int right = n+1;
        int target = arr[i]+d-1;
        while(left < right){
            int mid = (left+right)/2;
            if(arr[mid] > target) right = mid;
            else left = mid +1;
        }
        return right;
    }
} 