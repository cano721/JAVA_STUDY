import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n + 1];
		int[] sum = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i - 1] + arr[i];
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		long answer = 0;
		for (int i = 1; i <= n; i++) {
			answer += map.getOrDefault(sum[i] - k, 0);
			map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
		}
		System.out.println(answer);

	}
}