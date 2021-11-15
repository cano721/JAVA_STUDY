package prioirityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ19638_센티와마법의뿅망치 {

	static int n, centiH, hammerCnt, resCnt;
	static int[] people;
	static PriorityQueue<Integer> heap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		centiH = Integer.parseInt(st.nextToken());
		hammerCnt = Integer.parseInt(st.nextToken());
		
		people = new int[n];
		
		heap = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		for (int i = 0; i < n; i++) {
			people[i] = Integer.parseInt(br.readLine());
			heap.add(people[i]);
			
		}
		
		/*
		 * 우선순위큐는 내림차순으로 쌓이기 때문에 맨 첫번째 값이 centiH값 보다 작으면 그 외에 값도 모두 작다 **!!!!
		 */
		for (int i = 1; i <= hammerCnt; i++) {
			
			//우선순위의 첫 번째값 1이거나 centiH보다 작은 경우 상황종료 
			if(heap.peek() == 1 || heap.peek() < centiH) break;
			
			resCnt = i; //뿅망치 사용횟수
			int peopleH = heap.poll();
			peopleH /=2;
			heap.add(peopleH);
			
			
			/*시간초과
			 * if(peopleH ==1) {
				ans = "NO";
				resH = peopleH;
				break;
			}
			else {
				peopleH /=2;
				heap.add(peopleH);
				
				if(checkH()) {
					ans = "YES";
					resCnt = i;
					break;
				}else {
					ans = "NO";
					resH = peopleH;
				}
			}*/
		}

		//큐는 내림차순으로 쌓이기 때문에 맨 첫번째 값이 centiH값 보다 작으면 그 외에 값도 모두 작다
		if(centiH > heap.peek()) {
			System.out.println("YES");
			System.out.println(resCnt);
		}
		else {
			System.out.println("NO");
			System.out.println(heap.peek());
		}
		/*if("YES".equals(ans)) {
			System.out.println(ans);
			System.out.println(resCnt);
		}else {
			System.out.println(ans);
			System.out.println(resH);
		}*/
		
		
	}

	private static boolean checkH() {
		
		int cnt = 0;
		
		for (int i = 0; i < n; i++) {
			int temp = heap.poll();
			if(temp < centiH) {
				cnt++;
			}
			heap.add(temp);
		}
		if(cnt == n) return true;
		else return false;
	}

}
