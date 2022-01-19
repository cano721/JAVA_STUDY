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

public class BJ2042_구간합구하기 {

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

		stree.init(num, 1, 1, n);

		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//int c = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				//b번째 수 c로 변경
				num[b] = c;
				stree.update(1, n, 1, b, c - num[b]);

			} else {
				sb.append(stree.sum(1, n, 1, b, (int) c) + "\n");

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

		public long init(long[] arr, int node, int start, int end) {
			//트리의 가장 마지막 레벌은 각 원소값을 저장함
			if (start == end) {
				return tree[node] = arr[start];
			}

			//좌측, 우측으로 나눠져서 값을 초기화해준다.
			int mid = (start + end) / 2;
			return tree[node] = init(arr, node * 2, start, mid) + init(arr, node * 2 + 1, mid + 1, end);
		}

		// Segment Tree 내 값이 변경되는 경우
		// idx: 구간 합을 수정하고자 하는 노드
		// dif: 수정할 값
		public void update(int start, int end, int node, int idx, long dif) {
			// 만약 변경할 값이 범위 밖이면 확인하지 않음
			if (idx < start || end < idx)
				return;

			//변경된 차이만큼 각 노드에 더해줌
			tree[node] += dif;
			if (start == end) {
				return;
			}

			int mid = (start + end) / 2;
			update(start, mid, node * 2, idx, dif); // 좌측 node로
			update(mid + 1, end, node * 2 + 1, idx, dif); // 우측 node로

		}

		//구하고자 하는 범위의 합구하기
		// left, right: 구간 합을 구하고자 하는 범위
		public long sum(int start, int end, int node, int left, int right) {
			// 범위를 벗어나게 되는 경우 더할 필요 없음
			if (left > end || right < start) {
				return 0;
			}

			// 범위 내 완전히 포함 시에는 더 내려가지 않고 바로 리턴
			if (left <= start && end <= right) {
				return tree[node];
			}

			// 그 외의 경우 좌 / 우측으로 지속 탐색 수행
			int mid = (start + end) / 2;
			return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
		}

	}

}
