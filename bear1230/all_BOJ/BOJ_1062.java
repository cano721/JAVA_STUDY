import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int result = Integer.MIN_VALUE;
	static int[] words;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new int[N];

		int flag = 0;
		flag = flag | 1 << ('a' - 'a');
		flag = flag | 1 << ('n' - 'a');
		flag = flag | 1 << ('t' - 'a');
		flag = flag | 1 << ('i' - 'a');
		flag = flag | 1 << ('c' - 'a');

		for (int i = 0; i < N; i++) {
			String string = br.readLine();
			for (int j = 4; j < string.length() - 4; j++) {
				words[i] = words[i] | 1 << (string.charAt(j) - 'a');
			}
		}

		if (K < 5) {
			System.out.println(0);
		} else if (K == 26) {
			System.out.println(N);
		} else {
			comb('a', 5, flag);
			System.out.println(result);
		}
	}

	public static void comb(int c, int cnt, int flag) {
		if (cnt == K) {
			int tCnt = 0;
			for (int i : words) {
				if ((i & flag) == i)
					tCnt++;
			}
			if (result < tCnt)
				result = tCnt;

			return;
		}
		if (c > 'z')
			return;
		comb(c + 1, cnt, flag);

		if ((flag & (1 << c - 'a')) == 0) {
			flag |= (1 << c - 'a');
			comb(c + 1, cnt + 1, flag);
		}

	}

}
