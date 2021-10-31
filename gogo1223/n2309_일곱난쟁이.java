import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n2309_일곱난쟁이 {
	/*
	 * 아홉 난쟁이 중 백설공주의 일곱난쟁이를 찾아라! 난쟁이의 키의 합은 100이다. 
	 * */
	static int[] height = new int[9];
	static int[] answer = new int[7];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		solution(0, 0, 0, answer);
	}

	private static void solution(int depth, int sum, int cnt, int[] answer) {
		if(cnt == 7) {
			if(sum != 100) {
				return;
			}
			Arrays.sort(answer);
			for (int i = 0; i < answer.length; i++) {
				System.out.println(answer[i]);
			}
			return;
		}
		if(depth >= 9) return;
		
		//난쟁이 미선택
		solution(depth+1, sum, cnt, answer);
		//난쟁이 선택
		answer[cnt] = height[depth];
		solution(depth+1, sum+height[depth], cnt+1, answer);

	}

}
