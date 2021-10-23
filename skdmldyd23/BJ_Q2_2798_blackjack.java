import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_Q2_2798_blackjack {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken()); // 카드개수
		int max = Integer.parseInt(st.nextToken()); // max값

		int[] nums = new int[n];
		int i = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		while(st.hasMoreTokens()) {
			nums[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		System.out.println(calculation(nums, n, max));
	}
	
	private static int calculation(int[] nums, int n, int max) {
		int result = 0;
		int sum = 0;
		for(int i = 0; i < n-2; ++i) {
			if(nums[i] > max) continue;
			for(int j = i+1; j < n-1; ++j) {
				if(nums[i]+nums[j] > max) continue;
				for(int k = j+1; k < n; ++k) {
					sum = nums[i]+nums[j]+nums[k];
					if(sum == max) return sum;
					else if(result < sum && sum < max) result = sum;
				}
			}
		}
		return result;
	}
}
