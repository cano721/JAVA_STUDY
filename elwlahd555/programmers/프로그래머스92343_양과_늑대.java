package elwlahd555.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 프로그래머스92343_양과_늑대 {
	public static void main(String[] args) {
		int info[]= {0,0,1,1,1,0,1,0,1,0,1,1};
		int edges[][]= {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
		System.out.println(solution(info, edges));
	}
    private static int MaxCnt;
	private static Map<Integer, List<Integer>> nodes;
    public static int solution(int[] info, int[][] edges) {
        MaxCnt = 0;
		nodes = new HashMap<>();
		for(int[] e : edges) {
			if(!nodes.containsKey(e[0])) nodes.put(e[0], new ArrayList<>());
			nodes.get(e[0]).add(e[1]);
		}
		List<Integer> list = new ArrayList<>();
		list.add(0);
		dfs(0, 0, 0, list, info);
		return MaxCnt;
    }
    public static void dfs(int idx, int s, int w, List<Integer> list, int[] info) {
		if(info[idx]==0) s+=1;
		else w+=1;
		if(s<=w) return;
		
		MaxCnt = Math.max(MaxCnt, s);
		
		List<Integer> next = new ArrayList<>();
		next.addAll(list);
		if(nodes.containsKey(idx)) next.addAll(nodes.get(idx));
		next.remove(Integer.valueOf(idx));
		
		for(int n : next) {
			dfs(n, s, w, next, info);
		}
		
		return;
	}
}
