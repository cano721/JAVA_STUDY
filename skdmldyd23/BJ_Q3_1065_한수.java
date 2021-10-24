import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_Q3_1065_한수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println(calculation(Integer.parseInt(br.readLine())));
		
	}
	private static int calculation(int num) {
		int h,t,o;
		int cnt = 99;
		
		if(num < 100) return num;
		else if(num == 1000) num -= 1;
		
		for(int i = 100; i <= num; i++) {
			h = i/100; 
			t = (i/10)%10;
			o = i%10;
			if(h-t == t-o) ++cnt;
		}
		return cnt;
	}
}
