import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n3085_사탕게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T;
		//테스트 개수
		T = Integer.parseInt(st.nextToken());
		
		String[][] arr = new String[T][T];
		String temp;
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine()); 
			temp = st.nextToken();
			for(int j = 0; j < T; j++) {
				arr[i][j] = temp.split("")[j]; 
			}
		}
		int answer = solution(arr);
		System.out.println(answer);

	}

	private static int solution(String[][] arr) {
		int answer = 1;
		String temp;
		answer = checkCandy(arr, answer);
		
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length; j++) {
				//열방향 변경
				if(!arr[i][j].equals(arr[i+1][j])) {
					temp = arr[i][j];
					arr[i][j] = arr[i+1][j];
					arr[i+1][j] = temp;
					
					answer = checkCandy(arr, answer);
					
					arr[i+1][j] = arr[i][j];
					arr[i][j] = temp;
				}
				//행방향 변경
				if(!arr[j][i].equals(arr[j][i+1])) {
					temp = arr[j][i];
					arr[j][i] = arr[j][i+1];
					arr[j][i+1] = temp;
					
					answer = checkCandy(arr, answer);
					
					arr[j][i+1] = arr[j][i];
					arr[j][i] = temp;
				}
			}
		}
		return answer;
	}

	private static int checkCandy(String[][] arr, int answer) {
		int cntX, cntY;
		for (int y = 0; y < arr.length; y++) {
			cntX = 1; cntY = 1;
			for (int x = 0; x < arr.length - 1; x++) {
				//열방향체크
				if(arr[x][y].equals(arr[x+1][y])) {
					cntX++;
				}else {
					cntX = 1;
				}
				//행방향체크
				if(arr[y][x].equals(arr[y][x+1])) {
					cntY++;
					
				}else {
					cntY = 1;
				}
				if(cntX > answer) answer = cntX;
				if(cntY > answer) answer = cntY;
			}
		}
		
		return answer;
	}

}
