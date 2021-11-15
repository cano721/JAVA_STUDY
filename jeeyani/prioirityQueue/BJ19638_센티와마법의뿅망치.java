package prioirityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ19638_센티와마법의뿅망치 {

	static int n, centiH, hammerCnt, resCnt, resH;
	static int[] people;
	static PriorityQueue<Integer> heap;
	static String ans;
	
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
		for (int i = 1; i <= hammerCnt; i++) {
			int peopleH = heap.poll();
			
			if(peopleH ==1) {
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
			}
		}

		if("YES".equals(ans)) {
			System.out.println(ans);
			System.out.println(resCnt);
		}else {
			System.out.println(ans);
			System.out.println(resH);
		}
		
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
