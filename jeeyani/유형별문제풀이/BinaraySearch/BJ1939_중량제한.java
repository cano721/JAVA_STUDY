package 유형별문제풀이.BinaraySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 
 * 
 * 
 * 
 */

public class BJ1939_중량제한 {

	static class IandNode {
		int idx;
		int weight;

		public IandNode(int idx, int weight) {

			this.idx = idx;
			this.weight = weight;
		}

	}

	static int n, m, maxW = 0, max;
	static List<IandNode>[] list;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			list[x].add(new IandNode(y, w));
			list[y].add(new IandNode(x, w));

			maxW = Math.max(maxW, w); //중량이 가장 무거운 값 저장

		}

		//공장 위치 저장
		st = new StringTokenizer(br.readLine());
		int factory1 = Integer.parseInt(st.nextToken());
		int factory2 = Integer.parseInt(st.nextToken());

		getMaxWeight(factory1, factory2);

		StringBuilder sb = new StringBuilder();
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static void getMaxWeight(int factory1, int factory2) {
		int left = 1;
		int right = maxW; 
		int mid = 0;

		Queue<Integer> q = new LinkedList<>();

		while (left <= right) {

			mid = (left + right) / 2;
			q.add(factory1);
			visited[factory1] = true;

			//두 정점의 공장으로 갈 수 있는 경로 찾기
			//경로가 존재하면 최대 중량값 저장 및 중량 늘리기
			if (isRoute(q, mid, factory2)) {
				max = Math.max(mid, max);
				left = mid + 1;
				
			//아니라면 중량 줄이기
			} else {
				right = mid - 1;
			}

			//초기화
			q.clear();
			Arrays.fill(visited, false);
		}

	}

	//bfs를 활용하여 각 정점의 경로 찾기
	private static boolean isRoute(Queue<Integer> q, int mid, int end) {

		while (!q.isEmpty()) {

			int start = q.poll();

			for (IandNode node : list[start]) {
				if (node.weight >= mid) {
					//경로 도착
					if (start == end)
						return true;

					if (!visited[node.idx]) {
						visited[node.idx] = true;
						q.add(node.idx);
					}
				}
			}
		}
		return false;
	}

}
