package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int INF = 200000000;

	/**
	 * ���� ���� s���� a������ b�������� �� ����� �ýø� Ÿ�� ������ �Ѵ�.
	 * �� ����� �ý� ����� �����ϱ� ���� �ս��Ͽ� ���� ���� ����� �δٸ� �ս��Ϸ��� �Ѵ�.
	 * �� ����� a������ b�������� ���� �ּ� ����� ���ϴ� ����
	 * �ս��� ���� ���� �ʾƵ� �ȴ�.
	 * 
	 * �÷��̵� �ͼ��� ��� ������ ���� �ִ� �Ÿ��� ���ϰ�, s -> i -> a, b�� ���� ���� �ּڰ��� ã�´�.
	 * @param n ����� ����
	 * @param s ���� ����
	 * @param a a�� ���� ����
	 * @param b b�� ���� ����
	 * @param fares �� ���� �� �̿� ���
	 * @return s���� a�� b�� ���� �ּڰ�
	 */
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		int[][] map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (i != j)
					map[i][j] = INF;
		for (int[] fare : fares) {
			map[fare[0]][fare[1]] = fare[2];
			map[fare[1]][fare[0]] = fare[2];
		}
		for (int k = 1; k <= n; k++)
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++)
					if (map[i][k] + map[k][j] < map[i][j])
						map[i][j] = map[i][k] + map[k][j];
		for (int i = 1; i <= n; i++)
			answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);
		return answer;
	}
}