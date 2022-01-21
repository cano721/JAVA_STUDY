import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10868 {
	static int []arr, minResult;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1]; 
		int s = (int)Math.ceil(Math.log(n)/Math.log(2));
		int size = 1 << (s + 1);
		
		minResult = new int[size];
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		init(1,1,n);
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			int result = solve(1, 1, n, left, right);
			sb.append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static int init(int node, int start, int end) {
		if(start == end) return minResult[node] = arr[start];
		
		int mid = (start+end)/2;
		return minResult[node] = Math.min(init(node*2, start, mid), init(node*2+1, mid+1, end));
	}
	
	static int solve(int node, int start, int end, int left, int right) {
		if(right < start || end < left) return Integer.MAX_VALUE;
		
		if(start >= left && right >= end) return minResult[node];
		int mid = (start+end)/2;
		return Math.min(solve(node*2, start, mid, left, right), solve(node*2+1, mid+1, end, left, right));
	}
}
