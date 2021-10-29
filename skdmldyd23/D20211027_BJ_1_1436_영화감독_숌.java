import java.io.BufferedReader;
import java.io.InputStreamReader;

public class D20211027_BJ_1_1436_영화감독_숌 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(calculation(n));
	}
	
	public static int calculation(int n) {
		Integer num = 665;
		int cnt = 0;
		while(n > cnt) if((++num).toString().contains("666")) cnt++;
		return num;
	}
}
