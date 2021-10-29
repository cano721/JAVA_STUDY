import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	static int m;
	static int check;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		int arr[] = new int[n];
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
        
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0, arr);
        
		if (m == 0) {
			check = check - 1;
		}
		System.out.println(check);

	}

	public static void dfs(int n, int sum, int[] arr) {
		if (sum == m) {
			check++;
		}
		for (int i = n; i < arr.length; i++) {
			dfs(i + 1, sum + arr[i], arr);
		}
	}

}
