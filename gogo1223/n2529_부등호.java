import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2529_부등호 {
	static int k;
	static String[] arr;
	static String min, max;		//자리수 맞춰서 넣어주기
	static boolean[] visited = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		//-----입력-----
		k= Integer.parseInt(st.nextToken());
		arr = new String[k];
		
		st = new StringTokenizer(br.readLine()); 
		min = "9";
		max = "0";
		for (int i = 0; i < k; i++) {
			arr[i] = st.nextToken();
			min += "9";
			max += "0";
		}
		//-----구현-----
		for (int j = 0; j < 10; j++) {
			visited[j] = true;
			solution(0, j, j+"");
			visited[j] = false;
		}
		
		//-----출력-----
		System.out.println(max);
		System.out.println(min);
	}

	private static void solution(int depth, int bfNum, String answer) {
		if(depth == k) {
			min = min.compareTo(answer) < 0 ? min : answer;
            max = max.compareTo(answer) > 0 ? max : answer;
			return;
		}
		for (int i = 0; i < 10; i++) {
			if(arr[depth].equals("<") && bfNum >= i) continue;
			else if(arr[depth].equals(">") && bfNum <= i) continue;
			if(visited[i]) continue;
			
			visited[i] = true;
			solution(depth+1, i, answer+i);
			visited[i] = false;
		}
	}

}
