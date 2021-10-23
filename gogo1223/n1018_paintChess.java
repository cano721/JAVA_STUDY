import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1018_paintChess {
	
	public static boolean[][] arr;
	public static int answer = 64;
	
	public static void main(String[] args) throws IOException {
		// 입력 값 셋팅 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		int N, M;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new boolean[N][M];
		
		for (int i = 0; i < arr.length; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < line.length(); j++) {
				if(line.charAt(j) == 'B') {
					arr[i][j] = false;
				}
				else {
					arr[i][j] = true;
				}
			}
		}
		for (int i = 0; i < N - 7; i++) {
			for (int j = 0; j < M - 7; j++) {
				paintChess(i, j);
			}
		}
		System.out.println(answer);
	}

	private static void paintChess(int i, int j) {
		boolean start = arr[i][j];
		int temp = 0;
		
		for (int p = 0; p < 8; p++) {
			for (int q = 0; q < 8; q++) {
				if((p + q) % 2 == 0) { //index 합이 짝수면 start와 같아야함
					if(arr[i+p][j+q] == start) {
						continue;
					}else {
						temp++;
					}
				}else { //index 합이 홀수면 start와 달라야함
					if(arr[i+p][j+q] == start) {
						temp++;
					}else {
						continue;
					}
				}
				
			}
		}		
		if(temp > 64 - temp) { //start가 반대일 경우도 생각
			temp = 64 - temp;
		}
		if(temp < answer) { //전역변수 answr과 비교
			answer = temp;
		}
		
	}

}
