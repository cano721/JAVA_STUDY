package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	static int[] set;
	static int[] level;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
	
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int M = Integer.parseInt(br.readLine());
			
			String[] list = new String[M];
			HashMap<String, Integer> friend = new HashMap<String, Integer>();
			int cnt = 0;
			
			// 1. 배열 관계 저장
			// HashMap에 사람 정리, index
			for(int i = 0; i < M; i++) {
				list[i] = br.readLine();
				
				String[] temp = list[i].split(" ");
				
				if(friend.getOrDefault(temp[0], -1) == -1) {
					friend.put(temp[0], cnt);
					cnt++;
				}
				
				if(friend.getOrDefault(temp[1], -1) == -1) {
					friend.put(temp[1], cnt);
					cnt++;
				}
			}
			
			set = new int[cnt];
			level = new int[cnt];
			for(int i = 0; i < cnt; i++) {
				set[i] = i;
				level[i] = 1;
			}
			
			for(int i = 0; i < M; i++) {
				String[] temp = list[i].split(" ");
				
				int a_idx = friend.get(temp[0]);
				int b_idx = friend.get(temp[1]);
				

				
				bw.write(union(a_idx, b_idx)+"\n");
				
			}
		}
		bw.flush();
		bw.close();
		br.close();
		
	}
	static int getParent(int a) {
		if(set[a] == a) return a;
		
		return set[a] = getParent(set[a]);
	}
	
	static int union(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if(a < b) {
			set[b] = a;
			level[a] += level[b];
			level[b] = 1;
			
			return level[a];
		}else if(a == b){
			return level[a];
		}else {
			set[a] = b;
			level[b] += level[a];
			
			level[a] = 1;
			return level[b];
		}
	}
}	