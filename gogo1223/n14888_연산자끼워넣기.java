import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n14888_연산자끼워넣기 {
	
	static int n;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] arr, calc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		calc = new int[4];
		
		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < 4; i++) {
			calc[i] = Integer.parseInt(st.nextToken());
		}
		solution(1, arr[0]);
		System.out.println(max);
		System.out.println(min);
	}

	private static void solution(int depth, int val) {
		if(depth == n) {
			max = Math.max(max, val);
			min = Math.min(min, val);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(calc[i] == 0) continue;
			
			calc[i]--;
			
			switch(i) {
				case 0: solution(depth+1, val + arr[depth]); break;//덧셈
				case 1: solution(depth+1, val - arr[depth]); break;//뺄셈
				case 2: solution(depth+1, val * arr[depth]); break;//곱셈
				case 3: solution(depth+1, val / arr[depth]); break;//나눗셈
			}
			
			calc[i]++;
		}
		
	}

}
