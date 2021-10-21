import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2798_blackJack {

	public static void main(String[] args) throws IOException {
		// 입력 값 셋팅 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		int N, M;
		int sum;
		int MAX = 0;
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < arr.length - 2; i++) {
			if(arr[i] >= M) continue;
			
			for (int j = i+1; j < arr.length - 1; j++) {
				if(arr[j] >= M) continue;
				
				for (int j2 = j+1; j2 < arr.length; j2++) {
					if(arr[j2] >= M) continue;
					sum = arr[i] + arr[j] + arr[j2];
					if(sum > MAX && sum <= M) {
						MAX = arr[i] + arr[j] + arr[j2];
					}
				}
			}
		}
		
		System.out.println(MAX);
	}

}
