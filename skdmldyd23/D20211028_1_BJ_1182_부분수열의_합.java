import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D20211028_1_BJ_1182_부분수열의_합 {
	static int N, count = 0;
	static int[] input;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		input = new int[N];
		visited = new boolean[N];
		
		int total = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N ; i++) input[i] = Integer.parseInt(st.nextToken());
		dfs(0, total);
		System.out.println(count);
	}
	
	private static void dfs(int depth, int total) {
		if(depth==N) {
			int sum = 0;
			boolean cntTrue = false;
			for(int i=0; i<N; ++i) {
				if(visited[i]) {
					cntTrue = true;
					sum += input[i];
				}
			}
			if(sum == total && cntTrue) count++;
			return;
		}
		visited[depth] = true;
		dfs(depth+1, total);
		visited[depth] = false;
		dfs(depth+1, total);
	}
}
