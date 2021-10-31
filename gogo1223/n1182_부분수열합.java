import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1182_부분수열합 {

	/*
	 * 입출력
	 * */
	static int answer = 0;
	static int[] arr;
	static int n, s;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		//테스트 개수
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		
		solution(0, 0);
		
		System.out.println(answer);
	}
	/*
	 * 문제풀이
	 * */
	private static void solution(int depth, int sum) {
		if(sum + arr[depth] == s) answer++;
		if(depth == n-1) {
			return;
		}
		solution(depth+1, sum+arr[depth]);
		solution(depth+1, sum);
	}

}
