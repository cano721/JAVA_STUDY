package LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 * 
 * 1. n은 10^15가 최대임으로 long타입으로 선언
 * 2. k진 트리의 경우 노드번호가 큰 것이 결국 level이 크거나 같다.
 * 3. k진 트리에서의 부모를 구하는 공식은 (a-2)/k+1
 * 
 * 따라서 부모노드를 찾으면서, 두 노드의 부모노드가 같아질 때까지 반복
 * 
 * 
 @author Jeeyani
 */

public class BJ11812_k진트리 {

	static long n;
	static int k, q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Integer.parseInt(st.nextToken());
			long y = Integer.parseInt(st.nextToken());

			if (k == 1) {
				sb.append(Math.abs(x-y)+"\n");
				continue;
			}
			
			long dist = 0;
			while( x != y) {
				long max = Math.max(x, y);
				y = Math.min(x, y);
				x = (max - 2) /k+1;
				dist++;
			}
			sb.append(dist+"\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
}
