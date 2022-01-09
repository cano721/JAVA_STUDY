import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] mod = new int[m];
		int[] arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = (arr[i - 1] + Integer.parseInt(st.nextToken())) % m;
			mod[arr[i]]++;

		}
		long ans = mod[0];
		for (int i = 0; i < m; i++) {
			ans += (long) mod[i] * (mod[i] - 1) / 2;
		}

		System.out.println(ans);
	}

}