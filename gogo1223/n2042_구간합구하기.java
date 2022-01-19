import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class n2042_구간합구하기 {
	static int N, M, K;
	static long[] arr, tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new long[N];
		tree = new long[N*4];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		init(0, N-1, 1);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K+M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if(a == 1) {
				long dif = c - arr[b-1];
				arr[b-1] = c;
				update(0, N-1, 1, b-1, dif);
			}else if(a == 2) {
				sb.append(sum(0, N-1, 1, b-1, (int)(c-1))+"\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static long init(int start, int end, int node) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
	}
	private static void update(int start, int end, int node, int index, long dif) {
		if(index < start || index > end) return;
		
		tree[node] += dif;
		
		if(start == end) return;
		
		int mid = (start + end) / 2;
		update(start, mid, node*2, index, dif);
		update(mid+1, end, node*2+1, index, dif);
	}
	private static long sum(int start, int end, int node, int left, int right) {
		if(right < start || left > end) return 0;
		else if(left <= start && end <= right) return tree[node];
		else {
			int mid = (start + end) / 2;
			return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
		}
	}
}
