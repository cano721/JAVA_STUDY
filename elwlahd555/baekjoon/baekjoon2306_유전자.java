package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon2306_유전자 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String DNA = br.readLine();
		int[][] dp=new int[DNA.length()][DNA.length()];
		for(int size=1;size<DNA.length();size++) {
			for(int start=0;start+size<DNA.length();start++) {
				int end=start+size;
				// 어떤 X가 KOI 유전자라면, aXt와 gXc도 KOI 유전자이다.
				if((DNA.charAt(start)=='a'&&DNA.charAt(end)=='t')|| (DNA.charAt(start)=='g'&&DNA.charAt(end)=='c')) {
					dp[start][end]=dp[start+1][end-1]+2;
				}
				// 어떤 X와 Y가 KOI 유전자라면, 이 둘을 연결한 XY도 KOI 유전자이다.
				for(int mid=start;mid<end;mid++) {
					dp[start][end]=Math.max(dp[start][end], dp[start][mid]+dp[mid+1][end]);
				}
			}
		}
		System.out.println(dp[0][DNA.length()-1]);
	}
}
