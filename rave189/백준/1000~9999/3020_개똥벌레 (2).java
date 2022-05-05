package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] stalagmite, stalactites;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()) / 2;
		int h = Integer.parseInt(st.nextToken());
		stalactites = new int[h + 1];
		stalagmite = new int[h + 1];
		for (int i = 0; i < n; i++) {
			stalagmite[Integer.parseInt(br.readLine())]++;
			stalactites[Integer.parseInt(br.readLine())]++;
		}
		for (int i = 1; i <= h; i++) {
			stalagmite[i] += stalagmite[i - 1];
			stalactites[i] += stalactites[i - 1];
		}
		int min = Integer.MAX_VALUE, count = 0;
		for (int i = 1; i <= h; i++) {
			int stalagmiteCnt = stalagmite[h] - stalagmite[i - 1];
			int stalatitesCnt = stalactites[h] - stalactites[h - i];
			int totalCnt = stalagmiteCnt + stalatitesCnt;
			if (totalCnt < min) {
				min = totalCnt;
				count = 1;
			} else if (totalCnt == min)
				count++;
		}
		System.out.println(String.format("%d %d", min, count));
	}
}