package SegmentFenwickTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 1. 세그먼트 트리에 사탕맛을 기준으로 각 구간의 합을 구한다.
 * 
 * 사탕이 상자에 담아지는 순서대로 쌓아진다고 생각하기
 * 맨 아래부터 1순위 
 * 
 * 2 1 2
 * 2 3 3 이라할때,
 * 
 * 3
 * 3
 * 3
 * 1
 * 1  
 * 
 * 2. 이분탐색은 세그먼트 트리를 이용하여 범위를 찾는다.
 * x가 왼쪽 자식노드의 수보다 작거나 같으면 (tree[node * 2] >= x) l ~ m 범위로 내려가고 (더 낮은 구역을 탐색)
 * x가 왼쪽 자식노드의 수보다 크면 (tree[node * 2 < x) m + 1 ~ r의 범위로 내려갑니다. (더 높은 구역을 탐색)
 * 
 * 
 * https://emoney96.tistory.com/93
 * */

public class BJ2243_사탕상자 {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		n = Integer.parseInt(br.readLine());
		
		SegmentTree stree = new SegmentTree(n);
		
		for (int i = 0; i < n; i++) {
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			if(a == 1) {
				int b = Integer.parseInt(st.nextToken());
			
				sb.append(stree.binarySearch(1, b, 1, n)+"\n");
			}
			else {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				stree.update(1, b, 1, n, c);
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

		public void update(int node, int idx, int left, int right, int diff) {
			
			if (idx < left || idx > right) return;
	 
	        if (left == right) {
	            tree[node] += diff;
	            return;
	        }
	        
	        tree[node] += diff;
			int mid = (left + right) / 2;
			
			update(node * 2, idx, left, mid,diff);
			update(node * 2 + 1, idx, mid + 1, right,diff);
		}

		
		public int binarySearch(int node, long x, int left, int right) {
			
			if(left == right) {
				update(1, 1, 1, n,-1);
				return left;
			}
			
			int mid = (left+right)/2;
			
			//x가 왼쪽 자식노드의 수보다 작거나 같으면 더 낮은 구역을 탐색
			if(tree[node * 2] >= x) {
				return binarySearch(node * 2, x, left, mid);
			}
			
			//x가 왼쪽 자식노드의 수보다 크면 더 높은 구역을 탐색
			else {
				return binarySearch(node * 2 + 1, x - tree[node * 2], mid + 1, right);
			}

		}

	}
}
