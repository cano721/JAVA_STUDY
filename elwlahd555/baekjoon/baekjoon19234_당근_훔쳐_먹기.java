package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class baekjoon19234_당근_훔쳐_먹기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int T=Integer.parseInt(st.nextToken());
		int map[][]=new int[N][2];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			map[i][0]=Integer.parseInt(st.nextToken());
			map[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(map, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});
		for (int i = 0; i < N; i++) {
			System.out.println(map[i][0]+" "+map[i][1]);
		}
		Long ans=(long) 0;
		for (int i = 0; i < N; i++) {
			ans+=map[i][1]+(map[i][0]*(T-N+i));
		}
		System.out.println(ans);
	}
}
