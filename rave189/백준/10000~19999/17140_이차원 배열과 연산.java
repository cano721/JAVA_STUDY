package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 3x3 �迭�� �ִ�.
 * �迭�� 1�ʰ� ���������� ������ ���� ������ �Ѵ�.
 * R���� : �迭�� ��� �࿡ ���� ������ �����Ѵ�. (���� ���� >= ���� ������ ��� ����)
 * C���� : �迭�� ��� ���� ���� ������ �����Ѵ�. (���� ���� < ���� ������ ��� ����)
 * ������ ������ ���� �Ѵ�.
 * �� �� Ȥ�� ���� ���� ���� �� �� ���Դ��� ����.
 * ���� ���� ����Ƚ�� ��������, ����Ƚ���� ���ٸ� ���� ������������ �����Ѵ�.
 * ���� �� �Ǵ� ���� ũ�Ⱑ 100�� �Ѿ�ٸ� ���� ���� ������.
 * �̶� �迭[r][c]�� ���� k�� �Ǵ� �ּ� �ð��� ���ϴ� ����
 * 100�ʰ� �Ѿ�ٸ� -1�� ����Ѵ�.
 * @author Rave
 *
 */
public class Main {

	static final int SIZE = 3, MAX = 100;
	static int[][] map = new int[SIZE][SIZE];
	static Comparator<Item> order = ((v1, v2) -> {
		if (v1.frequency < v2.frequency)
			return -1;
		else if (v1.frequency == v2.frequency)
			return v1.value - v2.value;
		return 1;
	});

	/**
	 * ����
	 * ������ try catch �����༭ boundException�� ����;
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int answer = 0;
		for (; answer <= MAX; answer++) {
			try {
				if (map[r][c] == k)
					break;
			} catch (ArrayIndexOutOfBoundsException e) {
			}
			if (map.length >= map[0].length) {
				calcR();
			} else
				calcC();
		}
		try {
			System.out.println(map[r][c] == k ? answer : -1);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(-1);
		}
	}

	public static void calcR() {
		Map<Integer, Integer> hash = new HashMap<>();
		List<Item>[] newMap = new List[map.length];
		int max = 0;
		for (int i = 0; i < map.length; i++) {
			newMap[i] = new ArrayList<>();
			for (int row : map[i])
				if (row != 0)
					hash.put(row, hash.getOrDefault(row, 0) + 1);
			max = Math.max(max, hash.size());
			for (int key : hash.keySet())
				newMap[i].add(new Item(key, hash.get(key)));
			hash.clear();
		}
		max = Math.min(max * 2, MAX);
		map = new int[map.length][max];
		for (int i = 0; i < newMap.length; i++) {
			newMap[i].sort(order);
			int idx = 0;
			for (Item cur : newMap[i]) {
				try {
					map[i][idx++] = cur.value;
					map[i][idx++] = cur.frequency;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
		}
	}

	public static void calcC() {
		Map<Integer, Integer> hash = new HashMap<>();
		List<Item>[] newMap = new List[map[0].length];
		int max = 0;
		for (int i = 0; i < map[0].length; i++) {
			newMap[i] = new ArrayList<>();
			for (int j = 0; j < map.length; j++)
				if (map[j][i] != 0)
					hash.put(map[j][i], hash.getOrDefault(map[j][i], 0) + 1);
			max = Math.max(max, hash.size());
			for (int key : hash.keySet())
				newMap[i].add(new Item(key, hash.get(key)));
			hash.clear();
		}
		max = Math.min(max * 2, MAX);
		map = new int[max][map[0].length];
		for (int i = 0; i < newMap.length; i++) {
			newMap[i].sort(order);
			int idx = 0;
			for (Item cur : newMap[i]) {
				try {
					map[idx++][i] = cur.value;
					map[idx++][i] = cur.frequency;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
		}
	}
}

class Item {
	int value, frequency;

	public Item(int value, int frequency) {
		this.value = value;
		this.frequency = frequency;
	}
}