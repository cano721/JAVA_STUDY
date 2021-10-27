import java.io.BufferedReader;
import java.io.InputStreamReader;

public class D20211025_1_BJ_Q2231_분해합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		System.out.println(calculation(num));
	}
	
	private static int calculation(int num) {
		String[] arr;
		int sum;
		for (int i=1; i<num; i++) {
			sum = i;
			arr = Integer.toString(i).split("");
			for (int j=0; j<arr.length; j++) sum += Integer.parseInt(arr[j]);
			if(sum == num) return i;
		}
		return 0;
	}
}
