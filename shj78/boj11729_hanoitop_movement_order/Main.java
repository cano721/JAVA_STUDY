import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int count = 0;
	
	/* 
	 * ��� A(from)���� N���� ������ ��� B(other)�� �̿��Ͽ� ��� C(to)�� �ű�� �˰���.
	 * N: ������ ����
	 * from: ����� - A
	 * other: ������� �������� �ƴ� �� - B
	 * to: ������ - C
	 *  1. ��� A���� N-1���� ������ ��� C�� �̿��Ͽ� �⵿ B�� �ű��.
	 *  2. ��� A���� 1���� ������ ��� C�� �ű��.
	 *  3. ��� B���� N-1���� ������ ��� A�� �̿��ؼ� ��� C�� �ű��.
	 */
	public static void hanoi(int N, int from, int other, int to) {
		if(N == 0)
			return;
		count ++;
		// n-1���� ������ �������� �ƴ� ��(other)�� �Űܳ���.
		hanoi(N-1, from, to, other);	
		
		// ������ ������ �������� �ű�.
		sb.append(from + " " + to + "\n");
		
		// �������� �ƴ� ��(other)�� �Űܳ��Ҵ� ���ǵ��� �������� �ű�
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