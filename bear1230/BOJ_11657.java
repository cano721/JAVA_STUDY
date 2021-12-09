/*
문제
N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다. 
각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다. 
시간 C가 양수가 아닌 경우가 있다. 
C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.
1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다. 
둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다. 
*/


import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int INF = Integer.MAX_VALUE;
	static long[] dist;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new ArrayList[n + 1];
		dist = new long[n + 1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        
		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<>();
		}
        
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
            
			list[start].add(new Node(end, time));
		}
		if (bellman_ford()) {
			for (int i = 2; i < n + 1; i++) {
				System.out.println(dist[i] == INF ? "-1" : dist[i]);
			}
		} else {
			System.out.println(-1);
		}
	}

	private static boolean bellman_ford() {		
		dist[1] = 0;
		boolean check = false;

		for (int i = 1; i < n + 1; i++) {
			check = false;
			for (int j = 1; j < n + 1; j++) {
				for (Node node : list[j]) {
					if (dist[j] != INF && dist[j] + node.time < dist[node.from]) {
						dist[node.from] = dist[j] + node.time;
						check = true;
					}
				}
			}
			if (!check) break;	
		}
		if (check)  
			return false;
		return true;
	}

	static class Node {
		int from;
		int time; // 소요시간

		Node(int from, int time) {
			this.from = from;
			this.time = time;
		}
	}
}