package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n2031_이쿠키달지않아 {
	static int t, n, d, k;
	static int[] arr, cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        t = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        arr = new int[n+1];
    	st = new StringTokenizer(br.readLine()," ");
        for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        
        Arrays.sort(arr);
        cnt = new int[n+1];
        for (int i = 1; i <= n; i++) {
        	cnt[i] = i - upperBound(i) + 1;
		}
        int[][] DP = new int[k+1][n+1];
        
        for(int i = 1; i <= k; i++) {
        	for(int j = 1; j <= n; j++) {
        		int t = cnt[j];
        		DP[i][j] = Math.max(DP[i][j-1], t + DP[i-1][j-t]);
        	}
        }
        
        System.out.println(DP[k][n]);

	}
	static int upperBound(int idx) {
    	int left = 0;
    	int right = idx;
    	int target = arr[idx]-d;
    	
    	while(left+1 < right) {
    		int mid = (left+right)/2;
    		
    		if(arr[mid] <= target) {
    			left = mid;
    		}else {
    			right = mid;
    		}
    	}
    	return right;
    }
	private static boolean find(int num, int[] arr) {

        int l = 0;
        int r = arr.length-1;

        while(l <= r){
            int mid = (l+r) / 2;

            if(arr[mid] == num){
                return true;
            } else if(arr[mid] > num){
                r = mid-1;
            } else {
                l = mid+1;
            }
        }

        return false;

    }

}
