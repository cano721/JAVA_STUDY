package prioirityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1.각 강의시간을 배열로 받기
 * 2. 시작시간 기준으로 오름차순 (만약 시작시간이 같다면, 종료시간으로 오름차순)
 * 3. 우선순위 큐에 배열의 첫번째 종료시간을 넣어주기
 * 4. 배열의 두번째 값부터 순회, 시작시간이 우선순위의 종료시간보다 작거나 같다면(강의실이 하나 더 필요한 경우),heap에서 하나 뺀다
 * 4-1. 순회하면서, 현재 종료시간을 q에 넣어준다.
 * 5. heap에 남아있는 데이터의 갯수가 강의실의 갯수이다.
 * 
 */

public class BJ11000_강의실배정 {

	static int n;
	static int[][] time;
	static PriorityQueue<Integer> heap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		
		time = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			
			for (int j = 0; j < 2; j++) {
				time[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작시간 기준으로 오름차순 정렬, 시작시간이 같을 경우 종료시간 기준으로 오름차순 정렬!
		Arrays.sort(time, new Comparator<int[]>() {
			
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o1[1] - o2[1];
				
				return o1[0] - o2[0];
			}
			
		});
		
		heap = new PriorityQueue<>();
		heap.add(time[0][1]);
		
		for (int i = 1; i < n; i++) {
			//시작시간(time[i][0])이 우선순위의 종료시간(heap.peek())보다 작거나 같다면
			if(time[i][0] >= heap.peek()) {
				heap.poll();
			}
			
			heap.add(time[i][1]);
			
		}
		System.out.println(heap.size());

	}

}
