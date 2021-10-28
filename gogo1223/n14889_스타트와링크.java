import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n14889_스타트와링크 {
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T;
		//테스트 개수
		T = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[T][T];
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0; j < T; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = solution(arr);
		System.out.println(answer);

	}

	private static int solution(int[][] arr) {
		visited = new boolean[arr.length];
		int answer = Integer.MAX_VALUE;
		
		combi(arr.length, arr, 0, 0);
		
		return answer;
	}
	
	static void combi(int n, int[][] arr, int idx, int count) {
		/*종단조건*/
		// 팀 조합이 완성될 경우
		if (count == n / 2) {

			getDiff(n, arr); //최소값 찾기
			return;
		}

		for (int i = idx; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true; 
				combi(n, arr, i + 1, count + 1); 
				
				visited[i] = false; // 재귀가 끝나면 비방문으로 변경
			}
		}
	}

	private static void getDiff(int n, int[][] arr) {
		int startTeam = 0;
		int linkTeam = 0;

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
			
				if (visited[i] == true && visited[j] == true) {
					startTeam += arr[i][j];
					startTeam += arr[j][i];
				}
				else if (visited[i] == false && visited[j] == false) {
					linkTeam += arr[i][j];
					linkTeam += arr[j][i];
				}
			}
		}
		int val = Math.abs(startTeam - linkTeam);

		/*
		 * 차이가 0인 경우,
		 * 가장 최소의 차이임으로 바로 종료
		 */
		if (val == 0) {
			System.out.println(val);
			System.exit(0);
		}

		
	}

}
