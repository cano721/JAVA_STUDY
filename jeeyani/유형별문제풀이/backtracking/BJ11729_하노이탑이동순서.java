package 유형별문제풀이.backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ11729_하노이탑이동순서 {

	/*
	 * [하노이탑 공식]
	 * 
	 * 1. 가장 큰 원판을 C로 옮기기 위해서는 n-1개의 원판이 A에서 B로 이동 => hanoi(n-1)
	 * 2. A에 있는 가장 큰 원판이 C로 이동 => 1회 이동
	 * 3. B에 있는 n-1개의 원판을 C로 이동 => hanoi(n-1)
	 *  
	 * "Hanoi(n) = 2 × Hanoi(n-1) + 1" ==> "An = 2*An-1 + 1 "
	 * 
	 *  일반항 An = 2^n - 1
	 * 
	 * 
	 */

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		bw.write((int) (Math.pow(2, n) - 1) + "\n");

		hanoi(n, 1, 2, 3); // A:1  B:2  C:3

		bw.flush();
		bw.close();

	}

	private static void hanoi(int n, int from, int by, int to) throws IOException {
		
		//이동할 원반의 수 1
		if (n == 1) {
			bw.write(from + " " + to + "\n");
			return;
		}
		//1. n-1개를 A에서 B로 이동
		hanoi(n - 1, from, to, by);

		//2.1개를 A에서 C로 이동
		bw.write(from + " " + to + "\n");

		//3. n-1개를 B에서 C로 이동
		hanoi(n - 1, by, from, to);

	}

}
