import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n10972_외판원순회2 {
	static int N;
	static int[][] w;
	
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		//---------입력---------
		N = Integer.parseInt(st.nextToken());	//도시 개수
		w = new int[N][N];						//비용 행렬
		visited = new boolean[N];
		
		for (int i = 0; i < w.length; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < w.length; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//---------구현---------
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			solution(0, i, i, 0);	//구현부
		}
		
		//---------출력---------
		System.out.println(answer);

	}

	private static void solution(int depth, int start, int point, int sum) {
		if(depth == N-1) {
			if(w[point][start] != 0)	//실수주의) 마지막에서 처음으로 다시 돌아가는 길이 있어야 함(0 아님)
				answer = Math.min(answer, sum + w[point][start]);
			return;
		}
		for (int i = 0; i < visited.length; i++) {
			if(w[point][i] == 0) continue;	//갈 수 없는 길 제외
			if(visited[i]) continue;		//방문한 길(true) 제외
			
			visited[i] = true;
			solution(depth+1, start, i, sum + w[point][i]);
			visited[i] = false;
		}
		
	}

}
