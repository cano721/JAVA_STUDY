package twoPoinSlider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class n1484_다이어트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		int n = Integer.parseInt(st.nextToken());
		
		int now = 2, last = 1;
		int answer = -1;

		while(Math.pow(now, 2) - Math.pow(now-1, 2) <= n) {
			if(Math.pow(now, 2) - Math.pow(last, 2) == n) {
				answer = now;
				bw.write(now+"\n");
				now++;
			}
			else if(Math.pow(now, 2) - Math.pow(last, 2) > n) {
				last++;
			}else now++;
		}
		
		if(answer == -1) bw.write("-1");
		
		br.close();
		bw.flush();
		bw.close();
	}

}
