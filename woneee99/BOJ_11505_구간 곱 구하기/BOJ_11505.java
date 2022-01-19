import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11505 {

	static long []arr, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new long[n+1];
		
		int s = (int) Math.ceil(Math.log(n) / Math.log(2))+1;
		int size = (int) Math.pow(2, s);
		
		result = new long[size];
		
		for(int i=1; i<=n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init(1,1,n);
		
		for(int i=0; i<m+k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==1) { //update
				arr[b] = c;
				update(1,1,n,b,c);
			}
			else { //mul
				sb.append(mul(1,1,n,b,c)).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	static long init(int node, int start, int end) {
		if(start==end) return result[node] = arr[start];
		
		int mid = (start+end)/2;
		
		return result[node] = (init(node*2, start, mid)*init(node*2+1, mid+1, end))%1000000007;
	}
	
	static long update(int node, int start, int end, int index, int val) {
		if(index < start || index > end) return result[node];
		
		if(start == end) return result[node] = val;
		
		int mid = (start+end)/2;
		
		return result[node] = (update(node*2, start, mid, index, val) * update(node*2+1, mid+1, end, index, val)) %1000000007;
	}
	
	static long mul(int node, int start, int end, int left, int right) {
		if(end < left || start > right) return 1;
		
		if(left <= start && end <= right) return result[node];
		
		int mid = (start + end)/2;
		
		return (mul(node*2, start, mid, left, right) * mul(node*2+1, mid+1, end, left, right)) %1000000007;
	}
}
