package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N���� ����� �ִ�.
 * �� �߿��� ȸ���� �����Ϸ��� �Ѵ�.
 * ȸ���� �����ϴ� ������ �� ȸ���� ������ ���� ���� ����� ȸ���� �� �� �ִ�.
 * ������ �� ȸ���� �ٸ� ȸ����� ����� ������ ���� ������ �ް� �ȴ�.
 * ��� ȸ���� �ٸ� ��� ȸ���� ģ���̸�, �� ȸ���� ������ 1���̴�.
 * ��� ȸ���� ������ 2���̸�, �ٸ� ��� ȸ���� ģ���̰ų� ģ���� ģ������ ���Ѵ�.
 * ���� ��� ȸ���� ������ 3���̸�, �ٸ� ��� ȸ���� ģ���̰ų�, ģ���� ģ���̰ų�, ģ���� ģ���� ģ������ ���Ѵ�.
 * �� ��, ȸ���� ������ ȸ���� �� �� �ִ� ��� ����� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int[][] relationship;
	static int INF = 200000000;

	/**
	 * �ʺ� �켱 Ž������ �����ϰ� Ǯ���� �������� �÷��̵� �ͼ��δ� �� ��Ǯ���� ��������
	 * �켱 �÷��̵� �ͼ��� ����Ͽ� �� ȸ���鰣�� �Ÿ��� ���Ѵ�.
	 * ���� �� ȸ���� �ٸ� ȸ���鰣�� �ִ� �Ÿ��� ���ϰ�, �� �� ���� ���� ���� ���Ѵ�.
	 * �� ȸ���� �ٸ� ȸ���鰣�� �Ÿ��� ���� ���� ���� ��츦 ��� ã�´�.
	 * 
	 * �÷��̵� �ͼ��� ����ϱ� �� ���踦 INF�� ������ ��, �ڱ� �ڽ��� 0���� �ʱ�ȭ �ؾ� �Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		relationship = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(relationship[i], INF);
			relationship[i][i] = 0;
		}
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			if (first == -2 && second == -2)
				break;
			relationship[first][second] = 1;
			relationship[second][first] = 1;
		}
		for (int t = 0; t < n; t++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					relationship[i][j] = Math.min(relationship[i][j], relationship[i][t] + relationship[t][j]);
		int[] score = new int[n];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				score[i] = Math.max(score[i], relationship[i][j]);
			min = Math.min(min, score[i]);
		}
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < n; i++)
			if (score[i] == min)
				result.add(i);
		for (int v : result)
			answer.append(v + 1).append(' ');
		System.out.println(min + " " + result.size());
		System.out.println(answer);
	}
}