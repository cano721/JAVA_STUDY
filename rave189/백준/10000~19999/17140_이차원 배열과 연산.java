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
 * 3x3 배열이 있다.
 * 배열은 1초가 지날때마다 다음과 같은 연산을 한다.
 * R연산 : 배열의 모든 행에 대해 정렬을 수행한다. (행의 개수 >= 열의 개수인 경우 적용)
 * C연산 : 배열의 모든 열에 대해 정렬을 수행한다. (행의 개수 < 열의 개수인 경우 적용)
 * 정렬은 다음과 같이 한다.
 * 각 행 혹은 열에 대해 수가 몇 번 나왔는지 센다.
 * 이제 수의 등장횟수 오름차순, 등장횟수가 같다면 수의 오름차순으로 정렬한다.
 * 만약 행 또는 열의 크기가 100을 넘어간다면 이후 값은 버린다.
 * 이때 배열[r][c]의 값이 k가 되는 최소 시간을 구하는 문제
 * 100초가 넘어간다면 -1을 출력한다.
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
	 * 구현
	 * 정답을 try catch 안해줘서 boundException이 나옴;
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