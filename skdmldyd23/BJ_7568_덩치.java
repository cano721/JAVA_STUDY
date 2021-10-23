import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_7568_덩치 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		int n = Integer.parseInt(br.readLine());
		
		int[] w = new int[n];
		int[] h = new int[n];
		
		for(int i = 0; i < n; i++) {
			line = br.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");
			w[i] = Integer.parseInt(st.nextToken());
			h[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n; i++) {
			sb.append(calculation(w[i], h[i], w, h)).append(" ");
		}
		
		System.out.println(sb.toString().trim());
	}
	private static int calculation(int w, int h,int[] wArr, int[] hArr) {
		int cnt = 1;
		for(int i = 0; i < wArr.length; i++) {
			if(w < wArr[i] && h < hArr[i]) cnt++;
		}
		return cnt;
	}
}
