import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int num = Integer.parseInt(st.nextToken());
		int max = 0, first = num, prev = 0;
		for (int i = 1; i < N; i++) {
			num = Integer.parseInt(st.nextToken());
			// 오르막길이 이어지면 크기 갱신

			if (prev < num) {
				max = Math.max(max, num-first);
			} else { // 새로운 오르막길 시작
				first = num;
			}
			prev = num;
		}
		System.out.println(max);
	}
}
