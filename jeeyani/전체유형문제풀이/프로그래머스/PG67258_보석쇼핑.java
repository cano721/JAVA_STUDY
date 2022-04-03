package 전체유형문제풀이.프로그래머스;

import java.util.*;

/*
 * [투 포인터]
 * 
 * Set을 이용해서 보석종류 담기
 * Map에 담아 보석을 비교하며 범위 변경하기
 * 
 * 
 * https://insight-bgh.tistory.com/m/372
 * */


public class PG67258_보석쇼핑 {

	public static void main(String[] args) {
		
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		int[] result = solution(gems);
		System.out.println(result[0]+" "+result[1]);

	}

	private static int[] solution(String[] gems) {
		int[] answer = new int[2];

		HashSet<String> gemSet = new HashSet<String>();
		gemSet.addAll(Arrays.asList(gems));
		
		int start = 0;
		int end = 0 ;
		int lenMin = Integer.MAX_VALUE;
		
		//보석과 그 보석의 갯수 담기
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		while(true) {
			
			
			//1.종료조건
			//마지막 진열대까지 확인했다면 종료
			if(end == gems.length) break;
			
			//2. 모든 종류를 담지 않았다면, end 범위 오른쪽으로 이동
			else if(map.size() != gemSet.size()) {
				if(end < gems.length) {
					map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
					end++;
				}
			}
			//2. 모든 종류를 담았다면, start 범위 오른쪽으로 이동
			else {
				map.put(gems[start], map.getOrDefault(gems[start], 0) - 1);
				
				if(map.get(gems[start]) == 0) {
					map.remove(gems[start]);
				}
				
				start++;
			}
			
			if((map.size() == gemSet.size()) && (end-start < lenMin)) {
				lenMin = end-start;
				answer[0] = start+1;
				answer[1] = end;
				}
			}
		
       
        return answer;
	}

	

}
