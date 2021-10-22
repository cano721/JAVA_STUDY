import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj1065_Hansu {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print(hansu_number(Integer.parseInt(br.readLine())));
	}
	
	public static int hansu_number(int num) {
		int cnt = 0;
		
		if(num < 100) {
			return num;
		}
		
		else {
			cnt = 99;
			if(num == 1000) {
				num = 999;
			}
			
			for(int i = 100; i <= num; i++) {
				int hundred = i / 100;
				int ten = (i / 10) % 10;
				int one = i % 10;
				
				if((hundred-ten) == (ten - one)) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}

}
