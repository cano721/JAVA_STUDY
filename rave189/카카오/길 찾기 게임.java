package Programmers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	PriorityQueue<Node> pq = new PriorityQueue<>();
	ArrayList<Integer> preOrder = new ArrayList<>();
	ArrayList<Integer> postOrder = new ArrayList<>();
	Node root;

	/**
	 * �� ����� ��ġ ��ǥ�� �־�����.
	 * �� ������ Ʈ���� ������� �Ѵ�.
	 * Ʈ���� ������ ���� �������� �����.
	 * 1. Ʈ���� �����ϴ� ��� ����� x, y ��ǥ ���� �����̴�.
	 * 2. ��� ���� ���� �ٸ� x���� ������.
	 * 3. ���� ����(level)�� �ִ� ���� ���� y ��ǥ�� ������.
	 * 4. �ڽ� ����� y ���� �׻� �θ� ��庸�� �۴�.
	 * 5. ������ ��� V�� ���� ���� Ʈ��(left subtree)�� �ִ� ��� ����� x���� V�� x������ �۴�.
	 * 6. ������ ��� V�� ������ ���� Ʈ��(right subtree)�� �ִ� ��� ����� x���� V�� x������ ũ��.
	 * 
	 * Ʈ���� ���� �� Ʈ���� ������ȸ�� ������ȸ�� ����� �迭�� ����� ��ȯ�ϴ� ����
	 * 
	 * �׳� �ڵ� ¥�� �ٷ� �����ôµ� �׳� �¾ƹ���;
	 * �ٽ� ���� �����°ǰ� �� ������
	 * @param nodeinfo �� ������ ����
	 * @return Ʈ���� ������ȸ���, ������ȸ���
	 */
	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];
		init(nodeinfo);
		makeTree();
		makePreOrder(root);
		makePostOrder(root);
		answer[0] = preOrder.stream().mapToInt(v -> v.intValue()).toArray();
		answer[1] = postOrder.stream().mapToInt(v -> v.intValue()).toArray();
		return answer;
	}

	public void init(int[][] nodeinfo) {
		for (int i = 0; i < nodeinfo.length; i++)
			pq.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
	}

	public void makeTree() {
		root = pq.poll();
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			Node prev = null;
			Node it = root;
			while (it != null) {
				prev = it;
				if (cur.x < it.x)
					it = it.left;
				else
					it = it.right;
			}
			if (cur.x < prev.x)
				prev.left = cur;
			else
				prev.right = cur;
		}
	}

	public void makePreOrder(Node cur) {
		if (cur == null)
			return;
		preOrder.add(cur.value);
		makePreOrder(cur.left);
		makePreOrder(cur.right);
	}

	public void makePostOrder(Node cur) {
		if (cur == null)
			return;
		makePostOrder(cur.left);
		makePostOrder(cur.right);
		postOrder.add(cur.value);
	}
}

class Node implements Comparable<Node> {
	int x, y, value;
	Node left, right;

	public Node(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	@Override
	public int compareTo(Node o) {
		if (y > o.y)
			return -1;
		else if (y == o.y)
			return x - o.x;
		return 1;
	}
}