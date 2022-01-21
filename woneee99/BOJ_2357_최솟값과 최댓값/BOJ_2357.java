import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2357 {
	static int []arr;
	static int []maxResult, minResult;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 입력 정수 
		int m = Integer.parseInt(st.nextToken()); // 계산 갯수
		
		arr = new int[n+1];
		int re = (int)Math.ceil((Math.log(n)/ Math.log(2)))+1;
		int size = (int) Math.pow(2, re);
		
		maxResult = new int[size];
		minResult = new int[size];
		
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		minInit(1, 1, n);
		maxInit(1, 1, n);
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			int min = minCal(1, left, right, 1, n);
			int max = maxCal(1, left, right, 1, n);
			
			sb.append(min).append(" ").append(max).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	static int minInit(int node,int start, int end) {
		if(start == end) return minResult[node] = arr[start];
		
		int mid = (start+end)/2;
		
		return minResult[node] = Math.min(minInit(node*2, start, mid), minInit(node*2+1, mid+1, end));
	}
	
	static int maxInit(int node,int start, int end) {
		if(start == end) return maxResult[node] = arr[start];
		
		int mid = (start+end)/2;
		
		return maxResult[node] = Math.max(maxInit(node*2, start, mid), maxInit(node*2+1, mid+1, end));
	}
	
	static int minCal(int node, int left, int right, int start, int end) {
		if(right < start || end < left) return Integer.MAX_VALUE;
		
		if(start >=left && end <= right) return minResult[node];
				
		int mid = (start+end)/2;
		
		return Math.min(minCal(node*2, left, right, start, mid), minCal(node*2+1, left, right, mid+1, end));
	}
	
	static int maxCal(int node, int left, int right, int start, int end) {
		if(right < start || end < left) return Integer.MIN_VALUE;
		
		if(start >=left && end <= right) return maxResult[node];
		
		int mid = (start+end)/2;
		
		return Math.max(maxCal(node*2, left, right, start, mid), maxCal(node*2+1, left, right, mid+1, end));
	}
}




