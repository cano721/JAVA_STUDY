package Programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		String expression = "100-200*300-500+20";
		System.out.println(s.solution(expression));
	}
}

class Solution {

	long answer = 0;
	ArrayList<Integer> numbers = new ArrayList<>();
	ArrayList<Character> opList = new ArrayList<>();
	ArrayList<Long> subNumber = new ArrayList<>();
	Queue<Character> subOp = new LinkedList<>();
	char[][] order = { { '*', '+', '-' }, { '*', '-', '+' }, { '+', '-', '*' }, { '+', '*', '-' }, { '-', '*', '+' },
			{ '-', '+', '*' } };

	/**
	 * 식이 주어진다.
	 * 식에는 *, +, - 세 개의 연산자가 있다.
	 * 이 세 개의 연산자의 우선순위를 달리하여 나올 수 있는 결과값의 최댓값을 구하는 문제
	 * * > +, - 와 같이 동등한 우선순위가 2개가 나올 수 없다.
	 * 같은 연산자끼리는 앞의 연산자가 우선순위가 더 높다.
	 * 브루트 포스를 이용하여 모든 경우의 수 6가지를 다 탐색한다.
	 * @param expression 식
	 * @return 우선순위를 달리하여 나올 수 있는 최댓값
	 */
	public long solution(String expression) {
		parse(expression);
		for (int i = 0; i < order.length; i++) {
			init();
			for (char opType : order[i])
				search(opType);
			answer = Math.max(answer, Math.abs(subNumber.remove(0)));
		}
		return answer;
	}

	/**
	 * 계산을 수행할 리스트를 초기화 해준다.
	 */
	public void init() {
		for (int num : numbers)
			subNumber.add((long) num);
		for (char op : opList)
			subOp.add(op);
	}

	public void parse(String exp) {
		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			if (ch == '*' || ch == '+' || ch == '-') {
				numbers.add(Integer.parseInt(exp.substring(0, i)));
				opList.add(exp.charAt(i));
				exp = exp.substring(i + 1);
				i = -1;
			}
		}
		numbers.add(Integer.parseInt(exp));
	}

	/**
	 * 파라미터로 들어온 연산자와 같은 연산이 있다면 연산을 수행한다.
	 * 연산을 수행하면 인덱스와 크기를 모두 하나씩 낮춰준다.
	 * ArrayList에서 remove는 효율이 매우 안좋지만 입력이 작다는 점을 생각하면
	 * 가독성과 코딩의 편의성 때문에 오히려 괜찮아보임.
	 * 
	 * remove를 안쓰고 구현해보려고 했는데, 그렇게 코딩하나 remove쓰나 별 차이 없는듯
	 * 그냥 편하게 remove쓰고 구현하는게 더 나아보임
	 * @param opType 연산자
	 */
	public void search(char opType) {
		int size = subOp.size();
		for (int i = 0; i < size; i++) {
			char curOp = subOp.poll();
			if (opType == curOp) {
				subNumber.set(i, calc(subNumber.get(i), subNumber.get(i + 1), opType));
				subNumber.remove(i + 1);
				i--;
				size--;
			} else
				subOp.add(curOp);
		}
	}

	public long calc(long a, long b, char opType) {
		if (opType == '*')
			return a * b;
		else if (opType == '+')
			return a + b;
		else
			return a - b;
	}
}