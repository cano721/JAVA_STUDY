package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_bj_16916_부분문자열 {

	static int ans=0;
	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j=0;
		for(int i=1;i<pattern.length();i++) {
			while(j>0 && pattern.charAt(i)!=pattern.charAt(j)) {
				j = pi[j-1];
			}
			if(pattern.charAt(i)==pattern.charAt(j)) 
				pi[i] = ++j;
		}
		return pi;
	}
	
	static void KMP(String origin, String ptn) {
		int[] pi = getPi(ptn);
		int j=0;
		for(int i=0;i<origin.length();i++) {
			while(j>0 && origin.charAt(i)!=ptn.charAt(j)) {
				j=pi[j-1];
			}
			if(origin.charAt(i)==ptn.charAt(j)) {
				if(j==ptn.length()-1) {
					ans=1;
					break;
				}
				else
					j++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		String pattern = br.readLine();
		KMP(origin,pattern);
		System.out.println(ans);
	}
}