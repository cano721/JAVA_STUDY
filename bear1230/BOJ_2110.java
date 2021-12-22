import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        int[] home = new int[n];
        for(int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);
        int start = 1;
        int end = home[n-1] - home[0];
        
        int mid = (start + end) / 2;

        int ans = 0;
        while(start <= end) {
            int cnt = 1;  // 공유기 갯수
            int left = home[0];  
            for(int i = 1; i < n; i++) {
                int dist = home[i] - left; 
                if(dist >= mid) {  
                    left = home[i];
                    cnt++;
                }
            }

            if(cnt >= c) {
                ans = Math.max(ans, mid);
                start = mid + 1;
            }else {
                end = mid - 1;
            }
            mid = (start + end) / 2;
        }
        System.out.println(ans);
    }
}