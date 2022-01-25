package 유형별문제풀이.SegmentFenwickTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 기존 세그먼트 트리에 최솟값을 구하는 메소드를 생성
 * 
 * */

public class BJ10868_최솟값 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		long[] num = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			num[i] = Long.parseLong(br.readLine());
		}

		SegmentTreeMin streeMin = new SegmentTreeMin(n);
		
		streeMin.init(num, 1, n, 1);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(streeMin.min(1,n,1,a,b)+"\n");

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class SegmentTreeMin {
		long tree[];
		int treeSize;

		//생성자
		public SegmentTreeMin(int arrSize) {
			//트리의 높이를 이용하여 배열의 크기 구하기
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			this.treeSize = (int) Math.pow(2, h + 1);
			tree = new long[treeSize];

		}

		public long init(long[] arr, int start, int end, int node) {
			//트리의 가장 마지막 레벌은 각 원소값을 저장함
			if (start == end) {
				return tree[node] = arr[start];
			}

			//좌측, 우측으로 나눠져서 최소값으로 초기화해준다.
			int mid = (start + end) / 2;
			return tree[node] = Math.min(init(arr, start, mid, node * 2), init(arr, mid + 1, end, node * 2 + 1));
		}

		public long min(int start, int end, int node, int left, int right) {

			// 범위 밖에 있는 경우
			if (left > end || right < start) {
				return Integer.MAX_VALUE;
			}

			// 범위 안에 있는 경우
			if (left <= start && end <= right) {
				return tree[node];
			}

			// 그렇지 않다면, 두 부분으로 나누어 최소값구하기
			int mid = (start + end) / 2;
			return Math.min(min(start, mid, node * 2, left, right) , min(mid + 1, end, node * 2 + 1, left, right));
		}

	}
}
