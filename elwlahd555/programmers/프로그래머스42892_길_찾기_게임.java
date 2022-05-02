package elwlahd555.programmers;

import java.util.Arrays;
import java.util.Comparator;

public class 프로그래머스42892_길_찾기_게임 {
	public static void main(String[] args) {
		int nodeinfo[][] = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
				{ 2, 2 } };
		System.out.println(solution(nodeinfo));
	}

	private static int[][] result;
	private static int idx;

	public static int[][] solution(int[][] nodeinfo) {
		// 노드를 입력받는다.
		Node[] node = new Node[nodeinfo.length];
		for (int i = 0; i < nodeinfo.length; i++) {
			node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
		}
		// y값 큰 순서대로, y값 같다면 x값 작은 순서대로 정렬
		Arrays.sort(node, new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				if (n1.y == n2.y)
					return n1.x - n2.x;
				else
					return n2.y - n1.y;
			}
		});

		// 트리를 만든다.
		Node root = node[0];
		for (int i = 1; i < node.length; i++) {
			insertNode(root, node[i]);
		}

		result = new int[2][nodeinfo.length];
		idx = 0;
		preorder(root); // 전위 순회
		idx = 0;
		postorder(root); // 후위 순회
		return result;
	}

	public static void insertNode(Node parent, Node child) {
		if (parent.x > child.x) {
			if (parent.left == null)
				parent.left = child;
			else
				insertNode(parent.left, child);
		} else {
			if (parent.right == null)
				parent.right = child;
			else
				insertNode(parent.right, child);
		}
	}

	public static void preorder(Node root) {
		if (root != null) {
			result[0][idx++] = root.value;
			preorder(root.left);
			preorder(root.right);
		}
	}

	public static void postorder(Node root) {
		if (root != null) {
			postorder(root.left);
			postorder(root.right);
			result[1][idx++] = root.value;
		}
	}

	public static class Node {
		int x;
		int y;
		int value;
		Node left;
		Node right;

		public Node(int x, int y, int value, Node left, Node right) {
			this.x = x;
			this.y = y;
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

}
