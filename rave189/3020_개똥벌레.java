package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 석순과 종유석으로 이루어진 동굴이 있다.
 * 이 동굴을 개똥벌레가 통과하려고 한다.
 * 개똥벌레는 자신이 지나갈 구간을 정한 후 일직선으로 가고, 장애물이 보이면 파괴한다.
 * 개똥벌레가 동굴을 통과했을때 파괴한 장애물의 최솟값과 그러한 구간이 몇 개가 있는지 구하는 문제
 * 첫 번째 장애물은 항상 석순이고, 다음은 종유석과 석순이 번갈아 나온다.
 * @author Rave
 *
 */
public class Main {

	/**
	 * 석순과 종유석의 높이를 배열에 저장한다.
	 * 이후 누적 합을 통해 각 높이의 따른 석순과 종유석의 개수를 구한다.
	 * 동굴의 높이만큼 반복문을 돌면서 석순[H] - 석순[i-1]으로 i번째 석순보다 높은 석순의 개수를 구하고,
	 * 종유석[H] - 종유석[H-i]로 i번째 종유석보다 큰 종유석의 개수를 구한다.
	 * 최솟값인지를 구한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int[] stalagmite = new int[h + 1];
		int[] stalactite = new int[h + 1];
		for (int i = 0; i < n; i += 2) {
			stalagmite[Integer.parseInt(br.readLine())]++;
			stalactite[Integer.parseInt(br.readLine())]++;
		}
		for (int i = 1; i <= h; i++) {
			stalagmite[i] += stalagmite[i - 1];
			stalactite[i] += stalactite[i - 1];
		}
		int min = Integer.MAX_VALUE, count = 0;
		for (int i = 1; i <= h; i++) {
			int sum = stalagmite[h] - stalagmite[i - 1] + stalactite[h] - stalactite[h - i];
			if (sum < min) {
				min = sum;
				count = 1;
			} else if (sum == min)
				count++;
		}
		System.out.println(String.format("%d %d", min, count));
	}
}