import java.io.*;
import java.util.*;

public class Main {

	static int N, L, ans;
	static int map[][], rmap[][];
	static boolean visit[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		rmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				rmap[j][i] = map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			if (makeRoad(map[i]))
				ans++;
			if (makeRoad(rmap[i]))
				ans++;
		}
		System.out.println(ans);
	}

	static boolean makeRoad(int[] road) {
		int size = 1;
		for (int i = 0; i < N - 1; i++) {
			if (road[i] == road[i + 1])
				size++;
			else if (road[i] + 1 == road[i + 1]) {
				if (size < L)
					return false;
				size = 1;
			} else if (road[i] == road[i + 1] + 1) {
				if (i + L >= N)
					return false;
				for (int j = i + 1; j < i + L; j++) {
					if (road[j] != road[j + 1])
						return false;
				}
				i += L - 1;
				size = 0;
			} else
				return false;
		}
		return true;
	}
}