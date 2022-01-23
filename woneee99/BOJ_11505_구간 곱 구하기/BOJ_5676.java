import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5676 {
	static int []arr, result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		String Line = "";
		while((Line = br.readLine()) != null) {
			st = new StringTokenizer(Line);
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			arr = new int[n+1];
			result = new int[n*4];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				arr[i] = (tmp == 0) ? 0 : (tmp > 0) ? 1 : -1;
			}
			
			StringBuilder sb = new StringBuilder();
			init(1,1,n);
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				
				String a = st.nextToken();
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if(a.equals("C")) {
					y = (y == 0) ? 0 : (y > 0) ? 1 : -1;
					update(1,1,n,x,y);
				}
				else {
					long q = mul(1,1,n,x,y);
					sb.append((q == 0) ? 0 : (q > 0) ? "+" : "-");
				}
			}
			System.out.println(sb.toString());
		}
	}
	static int init(int node, int start, int end) {
		if(start == end) return result[node] = arr[start]; 
			
		int mid = (start + end)/2;
		return result[node] = init(node*2, start, mid)*init(node*2+1, mid+1, end);
	}
	
	static int update(int node, int start, int end, int index, int val) {
		if(end < index || index < start) return result[node];
		
		if(start == end) return result[node] = val;
		int mid = (start + end)/2;
		
		return result[node] = update(node*2, start, mid, index, val)*update(node*2+1, mid+1, end,index, val);
	}
	static int mul(int node, int start, int end, int left, int right) {
		if(right <start || end < left) return 1;
		
		if(start >= left && end <= right) return result[node];
		int mid = (start + end)/2;
		
		return mul(node*2, start, mid, left, right)*mul(node*2+1, mid+1, end, left, right);
		
	}
}
