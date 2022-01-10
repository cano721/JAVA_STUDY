package BOJ;

import java.io.*;
import java.util.*;
class Main{		
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int idx = 0;
		while(st.hasMoreTokens()) {
			A[idx] = Integer.parseInt(st.nextToken());
			idx++;
		}
		
		
		int M = Integer.parseInt(br.readLine());
		int[] B = new int[M];
		st = new StringTokenizer(br.readLine());
		idx = 0;
		while(st.hasMoreTokens()) {
			B[idx] = Integer.parseInt(st.nextToken());
			idx++;
		}
		
		ArrayList<Long> A_sub = new ArrayList<Long>();
		ArrayList<Long> B_sub = new ArrayList<Long>();
		
		for(int i = 0; i < N; i++) {
			long temp = 0;
			for(int j = i; j < N; j++) {
				temp+=A[j];
				A_sub.add(temp);
			}
		}
		
		for(int i = 0; i < M; i++) {
			long temp = 0;
			for(int j = i; j < M; j++) {
				temp+=B[j];
				B_sub.add(temp);
			}
		}
		
		Collections.sort(A_sub);
		Collections.sort(B_sub);
		
		long ans = 0;
		
		for(long temp : A_sub) {
			ans+=upperBound(B_sub, T-temp) - lowerBound(B_sub, T-temp);
		}
		
		System.out.println(ans);
		
	}
	static int lowerBound(ArrayList<Long> arr, long target) {
		int start = 0;
		int end = arr.size();
		
		while(start < end) {
			int mid = (start + end) / 2;
			
			if(target <= arr.get(mid)) {
				end = mid;
			}else {
				start = mid+1;
			}
		}
		
		return end;
	}
	
	static int upperBound(ArrayList<Long> arr, long target) {
		int start = 0;
		int end = arr.size();
		
		while(start < end) {
			int mid = (start + end) / 2;
			
			if(arr.get(mid) <= target) {
				start = mid+1;
			}else {
				end = mid;
			}
		}
		
		return end;
	}
}