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
	 * 각 노드의 위치 좌표가 주어진다.
	 * 이 노드들을 트리로 만들려고 한다.
	 * 트리는 다음과 같은 조건으로 만든다.
	 * 1. 트리를 구성하는 모든 노드의 x, y 좌표 값은 정수이다.
	 * 2. 모든 노드는 서로 다른 x값을 가진다.
	 * 3. 같은 레벨(level)에 있는 노드는 같은 y 좌표를 가진다.
	 * 4. 자식 노드의 y 값은 항상 부모 노드보다 작다.
	 * 5. 임의의 노드 V의 왼쪽 서브 트리(left subtree)에 있는 모든 노드의 x값은 V의 x값보다 작다.
	 * 6. 임의의 노드 V의 오른쪽 서브 트리(right subtree)에 있는 모든 노드의 x값은 V의 x값보다 크다.
	 * 
	 * 트리를 만든 후 트리의 전위순회와 후위순회의 결과를 배열로 만들어 반환하는 문제
	 * 
	 * 그냥 코드 짜고 바로 돌려봤는데 그냥 맞아버림;
	 * 다시 폼이 오르는건가 꽤 괜찮네
	 * @param nodeinfo 각 노드들의 정보
	 * @return 트리의 전위순회결과, 후위순회결과
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