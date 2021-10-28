import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int producer = Integer.MAX_VALUE;
		boolean flag = false;
		
		for (int i=1; i<n; i++) {
			String[] num = Integer.toString(i).split("");
			
			int sum = i;
			for (int j=0; j<num.length; j++) {
				sum += Integer.parseInt(num[j]);
			}
			if (sum == n) {
				producer = Math.min(producer, i);
				flag = true;
			}
		}
		
		if (!flag) {
			System.out.println(0);
		} else {
			System.out.println(producer);
		}
		
	}
}