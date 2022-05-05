package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon3020_개똥벌레 {

	static int N; // 동굴의 길이
	static int H; // 동굴의 높이
	static int[] top;// 석순
	static int[] bot;// 종유석
	static int min; // 파괴하는 장애물의 최솟값
	static int cnt; // min에 해당되는 구간의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 동굴의 길이
		H = Integer.parseInt(st.nextToken()); // 동굴의 높이
		bot = new int[H + 1]; // 석순 정보
		top = new int[H + 1]; // 종유석 정보
		min = N; // 파괴하는 장애물의 최솟값
		cnt = 0; // min에 해당되는 구간의 수

		for (int i = 0; i < N / 2; i++) {
			bot[Integer.parseInt(br.readLine())]++; // 석순(bot)
			top[Integer.parseInt(br.readLine())]++; // 종유석(top)
		} // End of input

		process();

		System.out.println(min + " " + cnt);

	}

	private static void process() {

		int[] bot_sum = new int[H + 1]; // 높이가 h이하인 석순의 갯수
		int[] top_sum = new int[H + 1]; // 높이가 h이하인 종유석의 갯수

		// 누적합 계산
		for (int i = 1; i < H + 1; i++) {
			top_sum[i] = top_sum[i - 1] + top[i];
			bot_sum[i] = bot_sum[i - 1] + bot[i];
		}

		for (int i = 1; i < H + 1; i++) {
			int crush = 0; // 부딪히는 개수

			// 부딪히는 석순의 갯수 = 전체 석순의 갯수 - (h-i)이하인 석순의 갯수
			crush += bot_sum[H] - bot_sum[i - 1];
			// 부딪히는 종유석의 갯수 = 전체 종유석갯수 - (i-1)이하인 종유석의 갯수
			crush += top_sum[H] - top_sum[H - i];

			// 최솟값 && 개수 위한 조건문
			if (min > crush) {
				min = crush;
				cnt = 1;
			} else if (min == crush) {
				cnt++;
			}
		}

	}

}
