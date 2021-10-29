import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int count = 0;
	
	/* 
	 * 기둥 A(from)에서 N개의 원반을 기둥 B(other)를 이용하여 기둥 C(to)로 옮기는 알고리즘.
	 * N: 원판의 개수
	 * from: 출발지 - A
	 * other: 출발지도 목적지도 아닌 곳 - B
	 * to: 목적지 - C
	 *  1. 기둥 A에서 N-1개의 원반을 기둥 C를 이용하여 기동 B로 옮긴다.
	 *  2. 기둥 A에서 1개의 원반을 기둥 C로 옮긴다.
	 *  3. 기둥 B에서 N-1개의 원반을 기둥 A를 이용해서 기둥 C로 옮긴다.
	 */
	public static void hanoi(int N, int from, int other, int to) {
		if(N == 0)
			return;
		count ++;
		// n-1개의 원판을 목적지가 아닌 곳(other)로 옮겨놓음.
		hanoi(N-1, from, to, other);	
		
		// 마지막 원판을 목적지로 옮김.
		sb.append(from + " " + to + "\n");
		
		// 목적지가 아닌 곳(other)에 옮겨놓았던 원판들을 목적지로 옮김
		hanoi(N-1, other, from, to);	
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		hanoi(N, 1, 2, 3);
		System.out.println(count);
		System.out.println(sb);
		scan.close();
	}

}