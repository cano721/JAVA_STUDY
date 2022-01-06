import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n3020_개똥벌레 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] bot = new int[H + 1]; // 석순 정보
        int[] top = new int[H + 1]; // 종유석 정보
		int min = N; // 파괴하는 장애물의 최솟값
		int cnt = 0; // min에 해당되는 구간의 수

		for (int i = 0; i < N / 2; i++) {
			bot[Integer.parseInt(br.readLine())]++; // 석순(bot)
			top[Integer.parseInt(br.readLine())]++; // 종유석(top)
		}
		int[] sumBot = new int[H + 1];	//높이 h이하인 석순 개수
		int[] sumTop = new int[H + 1];	//높이 h이하인 종유석 개수
		
		for (int i = 1; i <= H; i++) {
			sumBot[i] = sumBot[i-1] + bot[i];
			sumTop[i] = sumTop[i-1] + top[i];
		}
		
		for (int i = 1; i <= H; i++) {
			int crush = 0;
			crush += sumBot[H] - sumBot[i-1]; 
			crush += sumTop[H] - sumTop[H-i];
			
			if(min > crush) {
				min = crush;
				cnt = 1;
			}else if(min == crush) {
				cnt++;
			}
		}
		System.out.println(min+" "+cnt);

	}

}
