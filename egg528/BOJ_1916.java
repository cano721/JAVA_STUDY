package exercise;

import java.io.*;
import java.util.*;

class Main{	
	static int INF = 100000000;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Bus>> list = new ArrayList<ArrayList<Bus>>();
		
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<Bus>());
		}
		
		for(int i = 0; i < M; i++) {
			String[] temp = br.readLine().split(" ");
			list.get(Integer.parseInt(temp[0])).add(new Bus(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
		}
		
		String[] AB = br.readLine().split(" ");
		int A = Integer.parseInt(AB[0]);
		int B = Integer.parseInt(AB[1]);
		
		boolean[] visited = new boolean[N+1];
		int[] time = new int[N+1];
		Arrays.fill(time, INF);
		
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[0] <= o2[0])? -1 : 1;
			}
		});
		
		time[A] = 0;
		queue.add(new int[] {time[A], A});
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			
			int dist = now[0];
			int node = now[1];
			
			if(visited[node]) continue;
			
			visited[node] = true;
			
			for(Bus bus : list.get(node)) {
				if(!visited[bus.to]) {
					if(dist + bus.time < time[bus.to]) {
						time[bus.to] = dist + bus.time;
						queue.add(new int[] {dist + bus.time, bus.to});
					}
				}
			}
		}
		
		System.out.println(time[B]);
		
		
		

	}
	static class Bus{
		int to;
		int time;
		
		public Bus(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}
}