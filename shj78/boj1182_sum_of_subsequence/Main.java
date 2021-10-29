import java.util.Arrays;
import java.util.Scanner;
public class Main {
	static int[] arr;
	static int n, s, cnt = 0;
	
	private static void dfs(int v, int su) {
		if(v == n) {
			if(su == s) cnt++;
			return;
		}

		dfs(v + 1, su + arr[v]);
		dfs(v + 1, su);
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt(); s = scan.nextInt(); arr = new int[n];
		
		for(int i = 0; i < n; i++) arr[i] = scan.nextInt();
		Arrays.sort(arr);
		
		dfs(0, 0);
		System.out.print(s == 0? --cnt : cnt);
	}
}