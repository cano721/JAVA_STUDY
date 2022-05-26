import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int ans[] = new int[5];
			ans[0] = N / 60;
			N %= 60;
			if (N <= 35) {
				ans[1] = N / 10;
				N %= 10;
				if (N > 5) {
					ans[1]++;
					ans[4] = 10 - N;
				} else {
					ans[3] = N;
				}
			} else {
				ans[0]++;
				ans[2] = 6 - N / 10;
				N %= 10;
				if (N > 4) {
					ans[2]--;
					ans[4] = 10 - N;
				} else {
					ans[3] = N;
				}
			}
			for (int i = 0; i < 5; i++) {
				bw.write(ans[i] + " ");
			}
			bw.write("\n");
			bw.flush();
		}
		bw.close();
	}
}
