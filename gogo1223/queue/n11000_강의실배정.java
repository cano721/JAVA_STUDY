package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n11000_강의실배정 {
	
	static int n, answer;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        
        arr = new int[n][2];
        answer = n;
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine()," ");
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
		}
        Arrays.sort(arr, new Comparator<int[]>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {
        		if(o1[0] == o2[0]) {
        			return o1[1] - o2[1];
        		}else {
            		return o1[0] - o2[0];
        		}
        	}
		});
        solution();
        System.out.println(answer);
	}

	private static void solution() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			if(pq.peek()!= null && pq.peek() <= arr[i][0]) {
				pq.poll();
				answer--;
			}
			pq.add(arr[i][1]);
		}
		
	}

}
