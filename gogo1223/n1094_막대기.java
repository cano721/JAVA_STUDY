import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1094_막대기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        int stick  = 64;
        while(N > 0) {
        	if(stick > N) stick /= 2;
        	else {
        		cnt++;
        		N -= stick;
        	}
        }
        System.out.println(cnt);

	}

}
