import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n10026_적록색약 {
	static int N;
	static String[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		N = Integer.parseInt(st.nextToken());
		arr =  new String[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String[] s = st.nextToken().split("");
		
			for (int j = 0; j < N; j++) {
				arr[i][j] = s[j];
			}
		}
		visited[0][0] = true;
		solution(0, 0);
		
		System.out.println(answer);
	}

	private static void solution(int i, int j) {
		
		visited[i][j] = true;
		
		for (int k = 0; k < 4; k++) {
			int x = i+dx[k];
			int y = j+dy[k];
			
			if(x < 0 || x >= N || y < 0 || y >= N){
				continue;
			}
			if(!visited[x][y] && arr[x][y] == arr[i][j]){
				solution(x, y);
			}
		}
		
		
	}

}
