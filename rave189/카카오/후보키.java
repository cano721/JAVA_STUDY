package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	boolean[] isFind;
	int columnCnt, answer = 0;
	String[][] relation;

	/**
	 * ���̺��� �־����� �ĺ�Ű�� ������ ���ϴ� ����
	 * �ĺ�Ű�� ���ϼ��� �ּҼ��� �����ؾ� �Ѵ�.
	 * column�� ���ԵǴ��� �ȵǴ����� �����ϴ� �����̹Ƿ� ��Ʈ����ŷ�� ����Ѵ�.
	 * �ĺ�Ű�� �ּҼ��� �����ؾ� �ϹǷ� 0001�� �ĺ�Ű��� xxx1�� ���� ��� Ű�� �ּҼ��� �������� ���Ѵ�.
	 * ���� �ĺ�Ű�� ã�� ���� �׿� ������ ��� Ű�� ����Ű�� �����Ͽ� Ž������ �ʴ´�.
	 * �ĺ�Ű�� Ʃ���� String���� ��� ���ϼ��� �����ϴ����� Ȯ���Ѵ�.
	 * @param relation ���̺�
	 * @return �ĺ�Ű�� ����
	 */
	public int solution(String[][] relation) {
		init(relation);
		searchCandidateKey();
		return answer;
	}

	public void init(String[][] relation) {
		columnCnt = relation[0].length;
		isFind = new boolean[1 << columnCnt];
		this.relation = relation;
	}

	public void searchCandidateKey() {
		for (int i = 1; i < isFind.length; i++) {
			if (isFind[i])
				continue;
			if (isCandidateKey(i)) {
				answer++;
				removeSuperKey(i);
			}
		}
	}

	public boolean isCandidateKey(int n) {
		HashSet<String> hash = new HashSet<>();
		for (int i = 0; i < relation.length; i++) {
			String str = "";
			for (int j = 0; j < columnCnt; j++)
				if ((n & (1 << j)) >= 1)
					str += relation[i][j];
			hash.add(str);
		}
		return hash.size() == relation.length;
	}

	public void removeSuperKey(int n) {
		for (int i = n + 1; i < isFind.length; i++)
			if ((i & n) == n)
				isFind[i] = true;
	}
}