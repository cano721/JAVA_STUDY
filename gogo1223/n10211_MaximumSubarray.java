import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n10211_MaximumSubarray {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        int[] answer = new int[T];
        for (int i = 0; i < T; i++) {
        	int N = Integer.parseInt(br.readLine());
        	
        	int[] arr = new int[N];
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
        	answer[i] = arr[0];
        	for (int j = 1; j < N; j++) {
				arr[j] = Math.max(arr[j], arr[j]+arr[j-1]);
				answer[i] = Math.max(answer[i], arr[j]);
			}
		}
        
        for (int i = 0; i < T; i++) {
			System.out.println(answer[i]);
		}
        

	}

}
