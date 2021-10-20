import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(Count(n));
	}

	private static int Count(int n) {
		
		int cnt = 0;
		
		if(n<100) {
			return n;
		} else {
			cnt = 99;
			
			for(int i=100; i<=n; i++) {
				
				int a = i / 100;
				int b = (i / 10) % 10;
				int c = i % 10;	
				if(b-a == c-b) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
