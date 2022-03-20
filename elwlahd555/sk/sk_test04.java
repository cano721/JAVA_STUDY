package elwlahd555.sk;

import java.util.Arrays;
import java.util.Comparator;

public class sk_test04 {
	public static void main(String[] args) {
		int n = 5;
		int edges[][] = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 } };
		System.out.println(solution(n, edges));
	}
	private static int [][]map;
	public static long solution(int n, int[][] edges) {
		long answer = 0;
		map = new int[n][n];
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});
		
		for (int i = 0; i < edges.length; i++) {
			map[edges[i][0]][edges[i][1]] = 1;
		}
		int count=1;
		while(count<n) {
			for (int i = 0; i < n; i++) {
				for (int j = i; j < n; j++) {
					if(map[i][j]==count) {
						for (int k = j; k < n; k++) {
							if(map[j][k]>0) {
								map[i][k]=map[j][k]+1;
							}
						}
					}
				}
			}
			count++;
		}
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				map[j][i]=map[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i==j||map[i][j]==0) {
					continue;
				}
				for (int k = 0; k < n; k++) {
					if(i==k||j==k||map[i][k]==0||map[j][k]==0) {
						continue;
					}
					if(map[i][k]==map[i][j]+map[j][k]) {
						answer++;
					}
				}
			}
		}
		return answer;
	}
}
