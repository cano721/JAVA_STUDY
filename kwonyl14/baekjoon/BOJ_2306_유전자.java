import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	 * 유전
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str=br.readLine();
		int[][] dp=new int[str.length()][str.length()];
		for(int size=1;size<str.length();size++) {
			for(int start=0;start+size<str.length();start++) {
				int end=start+size;
				// 어떤 X가 KOI 유전자라면, aXt와 gXc도 KOI 유전자이다.
				if((str.charAt(start)=='a'&&str.charAt(end)=='t')|| (str.charAt(start)=='g'&&str.charAt(end)=='c')) {
					dp[start][end]=dp[start+1][end-1]+2;
				}
				// 어떤 X와 Y가 KOI 유전자라면, 이 둘을 연결한 XY도 KOI 유전자이다.
				for(int mid=start;mid<end;mid++) {
					dp[start][end]=Math.max(dp[start][end], dp[start][mid]+dp[mid+1][end]);
				}
			}
		}
		for(int i=0;i<str.length();i++) {
			for(int j=0;j<str.length();j++) {
				System.out.printf(dp[i][j]+" ");
			}System.out.println();
		}
		System.out.println(dp[0][str.length()-1]);
	}
}