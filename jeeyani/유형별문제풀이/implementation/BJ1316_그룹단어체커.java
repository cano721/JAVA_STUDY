package 유형별문제풀이.implementation;

import java.util.Scanner;

public class BJ1316_그룹단어체커 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int ans = n;
		for (int i = 0; i < n; i++) {
			
			String str = sc.next();
			boolean[] alpt = new boolean[26];
			
			char chk = str.charAt(0);
			alpt[chk-97] = true;
			
			for (int j = 1; j < str.length(); j++) {
				
				char next = str.charAt(j);
				if(chk == next) continue;
				
				chk = next;
				
				if(alpt[next-97]) {
					ans--;
					//n--;   //n자체를 줄이면..for문 다 안돌자너ㅠㅠ..
					break;
					
				}else {
					alpt[next-97] = true;
				}
				
			}
			
		}
		System.out.println(ans);

	}

	
}
