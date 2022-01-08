import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n10986_나머지합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        long[] sum = new long[N+1];
        long[] mod = new long[M];
        long answer = 0;
        for (int i = 1; i <= N; i++) {
        	long num = Long.parseLong(st.nextToken());
        	sum[i] = sum[i-1] + num;
        	if(sum[i] % M == 0) answer++;
        	mod[(int)(sum[i] % M)]++;
		}
        
        for (int i = 0; i < M; i++) {
			answer += mod[i] * (mod[i]-1) / 2; //조합 nC2
		}
        System.out.println(answer);
	}

}
