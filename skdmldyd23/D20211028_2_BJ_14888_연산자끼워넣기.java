import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D20211028_2_BJ_14888_연산자끼워넣기 {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int a[] = new int[n];
		int b[] = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) a[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4 ; i++) b[i] = Integer.parseInt(st.nextToken());
		
		dfs(1, n, a, b, a[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int idx, int n, int[]a, int[]b, int result) {
		if(idx==n) {
			if(max < result) max = result;
			if(min > result) min = result; 
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(b[i]<=0) continue;
			b[i] -= 1;
			int nextValue = result;
			if(i==0) nextValue += a[idx];
			if(i==1) nextValue -= a[idx];
			if(i==2) nextValue *= a[idx];
			if(i==3) nextValue /= a[idx];
			dfs(idx+1, n, a, b, nextValue);
			b[i] += 1;
		}
	}
}
