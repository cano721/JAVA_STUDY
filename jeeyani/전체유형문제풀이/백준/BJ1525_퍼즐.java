package 전체유형문제풀이.백준;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


/*
 * 
 * 3*3 퍼즐, 경우의 수 9! = 362880
 * 
 * 1. 1차원 배열 형태로 풀기 123456789 (0을 9로 바꿔서 입력하기)
 * 2. map사용, map(현재까지 갱신된 값,현재까지 이동한 횟수)
 * 
 * */

public class BJ1525_퍼즐 {

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			
			for (int j = 0; j < 3; j++) {
				
				String str = st.nextToken();
				if("0".equals(str)) sb.append("9");
				else sb.append(str);
			}
		}
		
		int ans = getMoveCnt(sb);
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
	}

	public static int getMoveCnt(StringBuilder sb) {
		
		Queue<String> q = new LinkedList<String>();
		Map<String, Integer> map = new HashMap<>(); //map(현재까지 갱신된 값,현재까지 이동한 횟수)
		
		q.offer(sb.toString());
		map.put(sb.toString(), 0);
		
		while(!q.isEmpty()) {
			String temp = q.poll();
			int nine = temp.indexOf("9"); //9가 있는 위치 찾기
			int x = nine/3;
			int y = nine%3;
			
			
			for (int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				int move = nx*3 + ny; //상하좌우로 움직였을 때의 index위치
				
				if(nx>=0 && nx<3 && ny>=0 && ny<3) {
					StringBuilder sbNext = new StringBuilder(temp);
					char ch = sbNext.charAt(move);
					sbNext.setCharAt(nine, ch); //9번이 있던 위치로 옮기기
					sbNext.setCharAt(move, '9');
					
					String next = sbNext.toString();
					if(!map.containsKey(next)) {
						q.offer(next);
						map.put(next, map.get(temp)+1);
					}
				}
			}
		}
		if(map.containsKey("123456789")) return map.get("123456789");
		else return -1;
		
	}


}
