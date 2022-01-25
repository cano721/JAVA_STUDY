import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] r1, int[] r2) {
				if(r1[0] == r2[0]) return r1[1] - r2[1];
				else return r1[0] - r2[0];
			}
		});
        
		PriorityQueue<Integer> q = new PriorityQueue<>();
		q.add(arr[0][1]);

		for (int i = 1; i < n; i++) {
			if (q.peek() <= arr[i][0]) q.poll();
			q.add(arr[i][1]);
		}

		System.out.print(q.size());
	}
}
