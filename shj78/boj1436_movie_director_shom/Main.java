import java.util.Scanner;
 
public class Main {
	public static void main(String[] args) {
    
		Scanner in = new Scanner(System.in);
        
		int N = in.nextInt();	
 
		if (N > 1) {
			func(N);
		} 
		else {
			System.out.println(666);
		}
	}
 
	public static void func(int n) {
		int count = 1;
		int prev_digit = 0; // ���� �ڸ���
		int num = 0;		// ���� �ڸ����� ������ ������ �� �ڸ���
		
		/*
		 
		   ���� ǥ�� ���
		   '_'(�����)�� �������� ǥ���Ѵ�.  ex) (prev_digit) _ num
		   �� ��, �ڸ����� ���� num �� 0 �Ǵ� 600, 660, 666 �� ���´�.
		   
		 */
		while (true) {
 
			/*
			 *  ���� �ڸ����� X...666X �̸鼭 X...6666 �� �ƴ� ��� 
			 *  (ex. 6660_000, 6660_001, ...)
			 */
			if (((prev_digit % 10000) / 10) == 666 && prev_digit % 10 != 6) {
				for (int i = 0; i < 1000; i++) {
					if (count == n) {
						System.out.print(prev_digit * 1000 + num);
						return;
					}
					num++;
					count++;
				}
				prev_digit++;
			}
 
			// ���� �ڸ����� X...666 �� ��� (ex. 666_000, 1666_004, ...)
			else if (prev_digit % 1000 == 666) {
				num = 0;
				for (int i = 0; i < 1000; i++) {
 
					if (count == n) {
						System.out.print(prev_digit * 1000 + num);
						return;
					}
					count++;
					num++;
				}
				prev_digit++;
			}
 
			// ���� �ڸ����� X...66 �� ��� (ex. 66_600, 166_600, ...)
			else if (prev_digit % 100 == 66) {
				num = 600;
				for (int i = 0; i < 100; i++) {
					if (count == n) {
						System.out.print(prev_digit * 1000 + num);
						return;
					}
					count++;
					num++;
				}
				prev_digit++;
 
			}
 
			
			// ���� �ڸ����� X...6 �� ��� (ex. 6_660, 16_663, ...) 
			else if (prev_digit % 10 == 6) {
				num = 660;
				for (int i = 0; i < 10; i++) {
					if (count == n) {
						System.out.print(prev_digit * 1000 + num);
						return;
					}
					num++;
					count++;
				}
				prev_digit++;
			} 
			
			// �� ���� ��� (ex. 241_666, 23_666 ...)
			else {
				num = 666;
				if (count == n) {
					System.out.print(prev_digit * 1000 + num);
					return;
				}
				count++;
				prev_digit++;
 
			}
		}
	}
}