package 유형별문제풀이.bitMask;

import java.io.BufferedReader;	
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.BitSet;
import java.util.StringTokenizer;

/*
 * 자를 수 있는 막대기의 길이는
 * 
 * 64 32 16 8 4 2 1
 * 
 * 즉, 2진수로 나타낼 수 있는 수이다.
 * 
 * 따라서 X길이를 만들수 있는 막대기의 갯수는
 * 결국에 X를 이진법으로 나타내어 만들어진 값의 1의 갯수이다.
 * 
 * 1의 갯수를 구하는 함수 Integer.bitCount를 이용한다.
 * 
 @author Jeeyani
 */

public class BJ1094_막대기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());

		
		StringBuffer sb = new StringBuffer();
		int ans = Integer.bitCount(n);
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	
}
