package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int answer = 0;
		for(int i=0; i<n; i++)
			for(int j=i+1; j<n; j++)
				for(int t=j+1; t<n; t++)
					if(arr[i] + arr[j] + arr[t]  <= m)
						answer = Math.max(answer, arr[i] + arr[j] + arr[t]);
		System.out.println(answer);
	}
}