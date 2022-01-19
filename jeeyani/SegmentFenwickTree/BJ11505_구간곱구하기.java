package SegmentFenwickTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 
 * https://hongjw1938.tistory.com/20
 * 
 * */

public class BJ11505_구간곱구하기 {
	
	static int MOD = 1000000007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		long[] num = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			num[i] = Long.parseLong(br.readLine());
		}

		SegmentTree stree = new SegmentTree(n);

		stree.init(num, 1, n, 1);

		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				//b번째 수 c로 변경
				num[b] = c;
				stree.update(1, n, 1, b, c);

			} else {
				sb.append(stree.mul(1, n, 1, b, (int) c) + "\n");

			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class SegmentTree {
		long tree[];
		int treeSize;

		//생성자
		public SegmentTree(int arrSize) {
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

			//좌측, 우측으로 나눠져서 값을 초기화해준다.
			int mid = (start + end) / 2;
			return tree[node] = (init(arr,start, mid, node * 2) * init(arr,mid + 1, end, node * 2 + 1)) % MOD;
		}

		// Segment Tree 내 값이 변경되는 경우
		// idx: 구간 합을 수정하고자 하는 노드
		// dif: 수정할 값
		public long update(int start, int end, int node, int idx, long val) {
			// arr[idx]를 x라 하자.
			// tree에서 값이 x인 인덱스를 target이라고 할 때,
			// target과 연결된 가지 부분을 전체 갱신해야 함.

			// 범위 밖에 있는 경우
			if (idx < start || idx > end) {
				return tree[node];
			}

			// 리프 노드 업데이트
			if (start == end) {
				return tree[node] = val;
			}

			int mid = (start + end) / 2;
			return tree[node] = (update(start, mid, node * 2, idx, val) * update(mid + 1, end, node * 2 + 1, idx, val))% MOD;
		}

		public long mul(int start, int end, int node, int left, int right) {
			// 범위 밖에 있는 경우
			if (left > end || right < start) {
				return 1;
			}

			// 범위 안에 있는 경우
			if (left <= start && end <= right) {
				return tree[node];
			}

			// 그렇지 않다면, 두 부분으로 나누어 합을 구하기
			int mid = (start + end) / 2;
			return (mul(start, mid, node * 2, left, right) * mul(mid + 1, end, node * 2 + 1, left, right)) % MOD;
		}

	}

}
