import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n7568_덩치 {

	public static void main(String[] args) throws IOException {
		// 입력 값 셋팅 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T;
		int rank;
		T = Integer.parseInt(st.nextToken());	//입력개수
		
		int[][] arr = new int[T][3];			//덩치입력
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine()); 
			arr[i][0] = Integer.parseInt(st.nextToken()); 
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = i + 1;
		}
		
		for (int i = 0; i < arr.length; i++) {
			rank = 1;
			for (int j = 0; j < arr.length; j++) {
				if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
					rank++;
				}
			}
			System.out.print(rank + " ");
		}
	}

}
