package backtracking;

import java.io.*;
import java.util.*;


public class BJ6603_로또 {

	static int n;
	static int[] lotto;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while(true) {
			/*String[] lottoTemp = sc.nextLine().split(" ");
			if(lottoTemp[0].equals("0")) break;
			
			n = Integer.parseInt(lottoTemp[0]);
			visited = new boolean[n];
			lotto = new int[n];
			
			for (int i = 1; i < lottoTemp.length; i++) {
				lotto[i-1] = Integer.parseInt(lottoTemp[i]);
			}*/
			
			n = sc.nextInt();
			if(n == 0) break;
			
			lotto = new int[n]; 
			visited = new boolean[n];
			
			for(int i = 0; i < n; i++) {
				lotto[i] = sc.nextInt();
			}
			combi(0, 0);
			System.out.println("");
		}
	}

	private static void combi(int idx, int cnt) {
		
		/*종단조건*/
		//로또는 6개 뽑기!
		if(cnt == 6) {
			
			for (int i = 0; i < n; i++) {
				if(visited[i]) {
					System.out.print(lotto[i]+" ");
				}
			}
			System.out.println("");
		}
		
		for (int i = idx; i < n; i++) {
			
			if (!visited[i]) {
				visited[i] = true;
				combi(i+1, cnt+1);
				
				visited[i] = false;
			}
			
		}
	}

}
