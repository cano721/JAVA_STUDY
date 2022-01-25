package 유형별문제풀이.cumulativeSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * <이분법 사용>
 * B를 정렬한 후 각 A에 대해 T-A가 B에 있는지 찾는 방식은 O((A+B)logB)
 * 
 * 
 * <HashMap을 사용>
 * B를 해싱한 후 각 A에 대해 T-A가 해시된 값에 있는지 바로 찾는 방식 O(A+B)
 * 
 * 1. 이중반복문을 사용하여 A의 모든 범위 확인하며 모든 부배열의 합 구하기
 * 2. map<부배열의 합, 해당 합이 나타난 횟수>
 * 3. B의 경우 'T-해당합'이 map에 존재하면 더해서 저장
 * 
 * 참고) https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-2143-%EC%9E%90%EB%B0%94-%EB%91%90-%EB%B0%B0%EC%97%B4%EC%9D%98-%ED%95%A9-BOJ-2143-JAVA
 @author Jeeyani
 */

public class BJ2143_두배열의합 {

	static int t, a, b;
	static int[] A, B;
	static int[] ASum, BSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		t = Integer.parseInt(br.readLine());

		a = Integer.parseInt(br.readLine());
		A = new int[a + 1];
		ASum = new int[a + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= a; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			ASum[i] = ASum[i - 1] + A[i];
		}

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 1; i <= a; i++) {
			for (int j = i; j <= a; j++) {
				int pSum = ASum[j] - ASum[i - 1];

				if (!map.containsKey(pSum)) {
					map.put(pSum, 1);
				} else {
					map.put(pSum, map.get(pSum) + 1);
				}
			}
		}

		b = Integer.parseInt(br.readLine());
		B = new int[a + 1];
		BSum = new int[a + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= b; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			BSum[i] = BSum[i - 1] + B[i];
		}

		long ans = 0; //카운팅한 결과값은 int 범위를 넘어갈 수 있음!!

		for (int i = 1; i <= b; i++) {
			for (int j = i; j <= b; j++) {
				int pSum = BSum[j] - BSum[i - 1];
				int key = t - pSum;

				if (map.containsKey(key)) {
					ans += map.get(key);
				}

			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
}
