import java.util.*;
import java.io.*;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		Carrot[] carrots = new Carrot[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			carrots[i] = new Carrot(w, p);
		}

		Arrays.sort(carrots);

		long ans = 0;

		int t = T - 1;

		for (int i = 0; i < N; i++) {
			ans += carrots[i].w + (long) carrots[i].p * t--;
		}

		System.out.println(ans);
	}

	static class Carrot implements Comparable<Carrot> {
		int w, p;

		Carrot(int w, int p) {
			this.w = w;
			this.p = p;
		}

		@Override
		public int compareTo(Carrot o) {
			if(o.p - p == 0)
                return (int) (o.w - w);
            else
                return (int) (o.p - p);
        }

		
	}
}