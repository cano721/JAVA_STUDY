import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 맛 순위를 인덱스로 해 세그먼트 트리 만들기
 */
public class BOJ_2243 {
	static int []arr;
	static final int max = 1000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[max*4];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if(a==1) { //꺼내는 경우
				int b = Integer.parseInt(st.nextToken()); // 사탕 순위
				int result = tree(1,1,max, b);
				sb.append(result).append("\n");
			}
			else { //넣는 경우
				int b = Integer.parseInt(st.nextToken()); //맛
				int c = Integer.parseInt(st.nextToken()); //개수
				update(1,1,max, b,c);
			}
		}
		System.out.println(sb.toString());
	}
	static int tree(int node, int start, int end, int rank) { //rank 사탕 순위
		if(start >= end) {
			update(1, 1, max, start, -1);
			return start;
		}
		
		int mid = (start+end)/2;
		
		if(arr[node*2] >= rank) return tree(node*2, start, mid, rank);
		else return tree(node*2+1, mid+1, end, rank - arr[node * 2]);
	}
	
	static void update(int node, int start, int end, int rank, int cnt) {
		if(start > rank || end < rank) return;
		
		arr[node] += cnt;
		
		if(start == end) return;
		int mid = (start+end)/2;
		
		update(node*2, start, mid, rank, cnt);
		update(node*2+1, mid+1, end, rank, cnt);
	}
}
