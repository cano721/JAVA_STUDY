import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
 
public class D20211024_2_BJ_Q15650_N과M {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr;
		int n, m;
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
 
		arr = new int[m];
        
		dfs(1, 0, n, m, arr, sb);
		
		System.out.println(sb);
 
	}
 
	public static void dfs(int point, int depth, int n, int m, int[] arr , StringBuilder sb) {
		if (depth == m) {
			for (int num : arr) sb.append(num).append(" ");
			sb.append("\n");
			return;
		}
		for (int i = point; i < n+1; i++) {
			arr[depth] = i;
			dfs(i + 1, depth + 1, n, m, arr, sb);
		}
	}
}