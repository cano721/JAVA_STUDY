import java.util.*;

public class Main {
	static int[] num;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		num = new int[N + 1];
		visited = new boolean[N + 1];
		dfs(N, M ,0 , 0);

	}

	// 조합
	static void dfs(int N, int M, int count , int preNum) {
		if (count == M) {
			for (int i = 0; i < M; i++) { // 현재 결과배열을 출력
				System.out.print(num[i] + " ");
			}
			System.out.println("");
			return; // DFS 종료
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
	             if (preNum > i) {
	                    continue;
	              }
				
				visited[i] = true;
				num[count] = i;
				dfs(N, M ,count + 1, num[count]);
				visited[i] = false;
			}
		}
	}
}