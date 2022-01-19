import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2042{
	static long []arr, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken()); // 수 변경
		int k = Integer.parseInt(st.nextToken()); // 구간 합을 구하는 횟수
		
		int h = (int) Math.ceil(Math.log(n)/ Math.log(2)); // 트리 높이
		int size = (int) Math.pow(2, h+1);
		
		arr = new long[n+1];
		result = new long[size];
		
		
		for(int i=1; i<=n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init(1, 1, n);
		
		for(int i=0; i<m+k; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			if(type == 1) { // update
				long diff = b- arr[a];
				arr[a] = b;
				update(1, 1, n, a, diff);
			}
			else { //sum
				sb.append(sum(1, 1, n, a, (int)b)).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	static long init(int node, int start, int end) { //세그먼트 트리
		if(start == end) {
			return result[node] = arr[start];
		}
		
		int mid = (start+end)/2;
		return result[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
	}
	
	static void update(int node, int start, int end, int index, long diff) { 
		if(index > end || index < start) return;
		
		result[node] += diff;
		if(start == end) return;
		
		int mid = (start+end)/2;
		
		update(node*2, start, mid, index, diff);
		update(node*2+1, mid+1, end, index, diff);
	}
	
	static long sum(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;
		
		if(left <= start && right >= end) return result[node];
		
		int mid = (start+end)/2;
		return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
	}
}
