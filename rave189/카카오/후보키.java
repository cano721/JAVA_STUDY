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
	 * 테이블이 주어지면 후보키의 개수를 구하는 문제
	 * 후보키는 유일성과 최소성을 만족해야 한다.
	 * column이 포함되는지 안되는지를 구분하는 문제이므로 비트마스킹을 사용한다.
	 * 후보키는 최소성을 만족해야 하므로 0001이 후보키라면 xxx1이 들어가는 모든 키는 최소성을 만족하지 못한다.
	 * 따라서 후보키를 찾는 순간 그와 연관된 모든 키를 슈퍼키로 구분하여 탐색하지 않는다.
	 * 후보키는 튜플을 String으로 묶어서 유일성을 만족하는지로 확인한다.
	 * @param relation 테이블
	 * @return 후보키의 개수
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