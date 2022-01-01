package TopologicalSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 * 
 * dfs를 이용하여 부모를 호출하고, 더 이상 호출할 부모가 없을 때 자기 자신의 피 정보를 리턴
 * 
 * 참조: https://gkscode.tistory.com/67
 @author Jeeyani
 */

public class BJ5021_왕위계승 {

	static int n, m;
	static String king;
	static Map<String, String[]> map = new HashMap<>(); //가족족보
	static Map<String, Double> blood = new HashMap<>(); //각 혈통자들의 피
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		king = br.readLine();
	
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent1 = st.nextToken();
			String parent2 = st.nextToken();

			map.put(child, new String[] {parent1,parent2});
			
			blood.put(child, 0.0);
			blood.put(parent1, 0.0);
			blood.put(parent2, 0.0);
			
		}
		
		double result = 0.0;
		String next = "";
		for (int i = 0; i < m; i++) {
			String candi = br.readLine();
			
			//blood 정보 초기화
			init();
			
			//해당 후보자의 피 정보 저장
			double temp = dfs(candi);
			
			//피를 진하게 이어받은 사람 찾기
			if(result < temp) {
				result = temp;
				next = candi;
			}
			
		}

		bw.write(next);
		bw.flush();

		bw.close();
		br.close();

	}

	private static double dfs(String name) {
		// 더 이상 찾을 부모가 없는 경우
		if(!map.containsKey(name)) {
			
			if(blood.containsKey(name)) {
				return blood.get(name);
			}
			else return 0.0;
			
		}
		//부모가 존재할 경우 해당 사람의 피 정보 갱신
		String[] parents = map.get(name);
		
		double newBlood = (dfs(parents[0]) + dfs(parents[1]))/2;
		blood.put(name, newBlood);
		return newBlood;
	}

	private static void init() {
		
		for(String value : blood.keySet()) {
			blood.put(value, 0.0);
		}
		//최초왕은 1로 초기화
		blood.put(king, 1.0);
	}
}
