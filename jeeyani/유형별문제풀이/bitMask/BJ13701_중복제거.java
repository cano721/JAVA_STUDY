package 유형별문제풀이.bitMask;

import java.io.BufferedReader;	
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.BitSet;
import java.util.StringTokenizer;

/*
 *
 * 자바의 Bitset 라이브러리를 사용하면 간단하게 해결
 * 
 * 0: false
 * 1: true
 * 
 * bs.set(10);
 * bs.set(20);
 * 
 * 10번째, 20번째 인덱스 값의 bit를 true로 설정
 * 
 * get을 이용하여 값 가져오기
 * bs.get(10); //true
 * bs.get(20); //true
 * bs.get(5); //false
 * 
 @author Jeeyani
 */

public class BJ13701_중복제거 {

	static int[] num;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		BitSet bs = new BitSet();
		
		StringBuffer sb = new StringBuffer();
		while(st.hasMoreTokens()){
			int num = Integer.parseInt(st.nextToken());
			
			if(bs.get(num)) continue;
			bs.set(num);
			sb.append(num+" ");
		
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	
}