import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] tmp = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			tmp[i] = Integer.parseInt(st.nextToken());
		}

        int sum = 0;
        for(int i = 0; i < k; i++){
            sum += tmp[i];
        }
        
        int ans = sum;
		for (int i = k; i < n; i++){			
            sum = sum - tmp[i - k] + tmp[i];
			ans = Math.max(ans, sum);
		}

		System.out.println(ans);
	}
}