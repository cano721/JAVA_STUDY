package implementation;

import java.util.Scanner;

public class BJ1316_그룹단어체커 {

	static boolean[] alphbat = new boolean[26];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int ans = 0;
		for (int i = 0; i < n; i++) {
			String inputStr = sc.next();
			if(check(inputStr)) {
				ans++;
			}
			
			
		}
		System.out.println(ans);

	}

	private static boolean check(String inputStr) {
		int now = inputStr.charAt(0);
		
		for (int j = 1; j < inputStr.length(); j++) {
			
			if(alphbat[now-'a']) { //이미 확인된 알파벳
				return false;
			}
			else {
				if(now != inputStr.charAt(j-1)) {
					alphbat[now-'a'] = true;
				}
			}

		}
		
		return true;
	}

}
