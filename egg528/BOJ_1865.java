package BOJ;

import java.io.*;
import java.util.*;

class Main{
	static ArrayList<ArrayList<Road>> roadList;
	static int[] time;
	static int N;
	static int M;
	static int W;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.valueOf(br.readLine());
		
		while(T-- > 0) {
			String[] NMW = br.readLine().split(" ");
			
			N = Integer.valueOf(NMW[0]);
			M = Integer.valueOf(NMW[1]);
			W = Integer.valueOf(NMW[2]);
			
			time = new int[N+1];
			
			
			roadList = new ArrayList<ArrayList<Road>>();
			
			
			// ArrayList 생성
			for(int i = 0; i <= N; i++) {
				roadList.add(new ArrayList<Road>());
			}
			
			// 도로 정보 입력 받기
			for(int i = 0; i < M; i++) {
				String[] temp = br.readLine().split(" ");
				roadList.get(Integer.parseInt(temp[0])).add(new Road(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
				roadList.get(Integer.parseInt(temp[1])).add(new Road(Integer.parseInt(temp[0]), Integer.parseInt(temp[2])));
			}
			
			// 웜홀 정보 입력 받기
			for(int i = 0; i < W; i++) {
				String[] temp = br.readLine().split(" ");
				roadList.get(Integer.parseInt(temp[0])).add(new Road(Integer.parseInt(temp[1]), -Integer.parseInt(temp[2])));
			}
			
			boolean isMinusCycle = false;
			
			for(int i = 1; i <= N; i++) {
				if(bellmanFord(i)) {
					isMinusCycle = true;
					break;
				}
			}
			
			
			if(isMinusCycle) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
	static boolean bellmanFord(int start) {
		Arrays.fill(time, Integer.MAX_VALUE);
		time[start] = 0;
		boolean isUpdate = false;
		
		
		for(int i = 1; i < N; i++) {
			isUpdate = false;
			
			for(int j = 1; j <= N; j++) {
				for(Road road : roadList.get(j)) {
					if(time[j] != Integer.MAX_VALUE && time[j]+road.time < time[road.node]) {
						time[road.node] = time[j]+road.time;
						isUpdate = true;
					}
				}
				
			}
			
			
			if(!isUpdate) {
				return false;
			}
		}
		
		if(isUpdate) {
			for(int i = 1; i <= N; i++) {
				for(Road road : roadList.get(i)) {
					if(time[i] != Integer.MAX_VALUE && time[i] + road.time < time[road.node]) {
						return true;
					}
				}
			}
		}
		
		return false;	
	}
	
	
	static class Road{
		int node;
		int time;
		
		public Road(int node, int time) {
			this.node = node;
			this.time = time;
		}
	}
}