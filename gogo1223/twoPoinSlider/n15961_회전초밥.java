package twoPoinSlider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class n15961_회전초밥 {
	static int n, d, k, c, answer = Integer.MIN_VALUE;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		n = Integer.parseInt(st.nextToken());	//접시수
		d = Integer.parseInt(st.nextToken());	//초밥가짓수
		k = Integer.parseInt(st.nextToken());	//연속할접시수
		c = Integer.parseInt(st.nextToken());	//쿠폰번호
		
		arr = new int[n+k];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int j = 0; j < k; j++) {
			arr[n+j] = arr[j];
		}
		
		solution();
		System.out.println(answer);

	}

	private static void solution() {
		int start = 0, end = 0, cnt = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		//처음 배열 담아주기
		for (int i = 0; i < k; i++) {
			if(!list.contains(arr[i])) cnt++;
			list.add(arr[i]);
		}
		
		
		int temp;
		while(true) {
			//쿠폰체크
			if(!list.contains(c)) {
				cnt++;
				answer = Math.max(cnt, answer);
				cnt--;
			}else {
				answer = Math.max(cnt, answer);
			}
			start++;
			end = start + k;
			if(end == n+k) break;
			
			//맨처음 index 빼고 새로운거 넣기
			temp = list.get(0);
			list.remove(0);
			if(!list.contains(temp) && temp != c) cnt--;
			if(!list.contains(arr[end]) && arr[end] != c) cnt++;
			list.add(arr[end]);		
			
		}	
	}

	

}
