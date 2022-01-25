package 유형별문제풀이.TopologicalSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BJ14676_영우는사기꾼 {

	static int n, m, k;
	static List<Integer>[] list; //선행관계 
	static int[] buildinglist; //지은 건물의 수
	static int[] buildingCnt; //건물을 짓기 위해 선행되어야 하는 건물의 수
	static String ans = "King-God-Emperor";
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		list = new ArrayList[n+1];
		buildinglist = new int[n + 1];
		buildingCnt = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			buildingCnt[b]++;
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int chk = Integer.parseInt(st.nextToken());
			int buliding = Integer.parseInt(st.nextToken());
			
			//건설
			if(chk ==1) {
				if(buildingCnt[buliding] > 0) { //건설할 수 없는 경우
					ans = "Lier!";
					break;
				}
				
				else {
					//건설한 경우 체크
					buildinglist[buliding]++;
					
					if(buildinglist[buliding]==1 && list[buliding].size() > 0) {
						
						for (int j = 0; j < list[buliding].size(); j++) {
							int next = list[buliding].get(j);
							//선행되어 지어진 건물만큼 감소
							buildingCnt[next]--;
						
						}
						
					}
				}
			}
			//파괴
			else {
				if(buildinglist[buliding] == 0) { //건설한 적이 없는 경우
					ans = "Lier!";
					break;
				}
				else {
					//파괴된 경우 체크
					buildinglist[buliding]--;
					
					if(buildinglist[buliding]==0 && list[buliding].size() > 0) {
						for (int j = 0; j < list[buliding].size(); j++) {
							int next = list[buliding].get(j);
							//선행건물이 파괴된 만큼 증가
							buildingCnt[next]++;
						}
					}
					
				}
			}
		}

		bw.write(ans);
		bw.flush();

		bw.close();
		br.close();

	}
}
