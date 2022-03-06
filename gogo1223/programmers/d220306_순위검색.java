package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class d220306_순위검색 {

	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"}; 
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		int[] answer = solution(info, query);	//[1,1,1,1,2,4]
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
	static Map<String, ArrayList<Integer>> map;
	private static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		map = new HashMap<>();
		for (String data : info) {
			dfs("", 0, data.split(" "));		//info : 하나의 string으로 만들기 위함
		}
		//효율성 문제로 query 10만이고 info는 5만이라 여기서 sort해준다.
		for(String strSet : map.keySet()) {
			Collections.sort(map.get(strSet));
		}
		int idx = 0;
		for(String q : query) {
			String[] queryArr = q.replaceAll(" and ", "").split(" ");	//query : 하나의 string으로 만듦
			answer[idx++] = search(queryArr[0], Integer.parseInt(queryArr[1]));	//이분탐색
		}
		return answer;
	}
	//이분탐색
	private static int search(String str, int score) {
		if(!map.containsKey(str)) return 0;
		int start = 0;
		int end = map.get(str).size() - 1;
		while(start <= end) {
			int mid = (start + end) / 2;
			if(map.get(str).get(mid) >= score) {
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		return map.get(str).size() - start;
	}

	private static void dfs(String str, int depth, String[] info) {
		if(depth == 4) {
			if(map.containsKey(str)) {
				map.get(str).add(Integer.parseInt(info[4]));
			}else {
				ArrayList<Integer> value = new ArrayList<>();
				value.add(Integer.parseInt(info[4]));
				map.put(str, value);
			}
			return;
		}
		dfs(str+"-", depth + 1, info);
		dfs(str+info[depth], depth + 1, info);
	}
}
