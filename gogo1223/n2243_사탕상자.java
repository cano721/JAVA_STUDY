import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class n2243_사탕상자 {
	static int tree[];
	static int MAX = 1000001;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		tree = new int[MAX*4];
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			
			int a = Integer.parseInt(input[0]);
			
			if(a == 1) {
				int b = Integer.parseInt(input[1]);
				int answer = binarySearch(1, 1, MAX, b);
				sb.append(answer+"\n");
			}else if(a == 2) {
				int b = Integer.parseInt(input[1]);
				int c = Integer.parseInt(input[2]);
				update(1, 1, MAX, b, c);
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	private static void update(int node, int start, int end, int target, int count) {
        if (target < start || target > end) return;
        tree[node] += count;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(node * 2, start, mid, target, count);
        update(node * 2 + 1, mid + 1, end, target, count);
    }
	//이분탐색
	private static int binarySearch(int node, int start, int end, int target) {
        if (start >= end) {
            update(1, 1, MAX, start, -1);
            return start;
        }

        int mid = (start + end) / 2;
        if (tree[node * 2] >= target)
            return binarySearch(node * 2, start, mid, target);
        else
            return binarySearch(node * 2 + 1, mid + 1, end, target - tree[node * 2]);
    }
}
