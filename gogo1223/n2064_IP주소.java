import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;

public class n2064_IP주소 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][4];
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			String[] temp = tmp.split("\\.");
			for(int j = 0; j < 4; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}

		int[] answerAddr = new int[4];
		int[] answerMask = new int[4];
		int cnt = 0;
		
		for (int i = 0; i < 4; i++) {
			int maxIp = arr[0][i];
			int minIp = arr[0][i];
			for (int j = 0; j < N; j++) {
				maxIp = Math.max(maxIp, arr[j][i]);
				minIp = Math.min(minIp, arr[j][i]);
			}
			Integer.toBinaryString(maxIp);
			Integer.toBinaryString(minIp);
			if(255 == 256 + (~maxIp^minIp)) {
				if(cnt > 3) break;
				answerMask[cnt++] = 255;
			}
			else {
				for (int j = 0; j < 9; j++) {
					if(-(~maxIp^minIp) <= (1<<j)) {
						if(cnt > 3) break;
						answerMask[cnt++] = 256 - (1<<j);
						for (int j2 = 0; j2 < 3; j2++) {
							if(cnt > 3) break;
							answerMask[cnt++] = 0;
						}
						break;
					}
				}
			}
			
			answerAddr[i] = arr[0][i]&answerMask[i];
		}
		for (int i = 0; i < 4; i++) {
			if(i == 3)
				System.out.println(answerAddr[i]);
			else
				System.out.print(answerAddr[i]+".");
		}
		for (int i = 0; i < 4; i++) {
			if(i == 3)
				System.out.println(answerMask[i]);
			else
				System.out.print(answerMask[i]+".");
		}
	}

}
