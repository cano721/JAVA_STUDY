import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n5676_음주코딩 {
	static int N, K;
	static int[] arr, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = "";

		StringBuilder sb = new StringBuilder();
		while((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				
				arr[i] = (temp == 0) ? 0 : (temp > 0) ? 1 : -1;
			}
			tree = new int[N*4];
			init(0, N-1, 1);
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(command.equals("C")) {
					arr[a-1] = (b == 0) ? 0 : (b > 0) ? 1 : -1;
					update(0, N-1, 1, a-1, b);
				}else if(command.equals("P")) {
					int temp = product(0, N-1, 1, a-1, b-1);
					if(temp == 0) {
						sb.append("0");
					}else if(temp > 0) {
						sb.append("+");
					}else {
						sb.append("-");
					}
				}
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static int init(int start, int end, int node) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return tree[node] = (init(start, mid, node*2) * init(mid+1, end, node*2+1));
	}
	
	private static int update(int start, int end, int node, int index, int dif) {
		if(index < start || index > end) return tree[node];
		if(start == end) return tree[node] = dif;
		
		int mid = (start + end) / 2;
		return tree[node] = update(start, mid, node*2, index, dif) * update(mid+1, end, node*2+1, index, dif);
	}
	
	private static int product(int start, int end, int node, int left, int right) {
		if(right < start || left > end) return 1;
		else if(left <= start && end <= right) return tree[node];
		else {
			int mid = (start + end) / 2;
			return (product(start, mid, node*2, left, right) * product(mid+1, end, node*2+1, left, right));
		}
	}
}
