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
	 * ���� �־�����.
	 * �Ŀ��� *, +, - �� ���� �����ڰ� �ִ�.
	 * �� �� ���� �������� �켱������ �޸��Ͽ� ���� �� �ִ� ������� �ִ��� ���ϴ� ����
	 * * > +, - �� ���� ������ �켱������ 2���� ���� �� ����.
	 * ���� �����ڳ����� ���� �����ڰ� �켱������ �� ����.
	 * ���Ʈ ������ �̿��Ͽ� ��� ����� �� 6������ �� Ž���Ѵ�.
	 * @param expression ��
	 * @return �켱������ �޸��Ͽ� ���� �� �ִ� �ִ�
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
	 * ����� ������ ����Ʈ�� �ʱ�ȭ ���ش�.
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
	 * �Ķ���ͷ� ���� �����ڿ� ���� ������ �ִٸ� ������ �����Ѵ�.
	 * ������ �����ϸ� �ε����� ũ�⸦ ��� �ϳ��� �����ش�.
	 * ArrayList���� remove�� ȿ���� �ſ� �������� �Է��� �۴ٴ� ���� �����ϸ�
	 * �������� �ڵ��� ���Ǽ� ������ ������ �����ƺ���.
	 * 
	 * remove�� �Ⱦ��� �����غ����� �ߴµ�, �׷��� �ڵ��ϳ� remove���� �� ���� ���µ�
	 * �׳� ���ϰ� remove���� �����ϴ°� �� ���ƺ���
	 * @param opType ������
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