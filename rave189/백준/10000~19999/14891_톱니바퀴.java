package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int SIZE = 4, LEFT = 6, RIGHT = 2;
	static StringBuilder[] gears = new StringBuilder[SIZE];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < SIZE; i++)
			gears[i] = new StringBuilder(br.readLine());
		int k = Integer.parseInt(br.readLine());
		while (k-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());
			moveGear(idx, direction);
		}
		int answer = 0;
		for (int i = 0; i < gears.length; i++)
			answer += gears[i].charAt(0) == '1' ? Math.pow(2, i) : 0;
		System.out.println(answer);
	}

	public static void moveGear(int idx, int direction) {
		if (idx + 1 < gears.length && gears[idx].charAt(RIGHT) != gears[idx + 1].charAt(LEFT))
			rightGearRotate(idx + 1, direction * -1);
		if (idx - 1 >= 0 && gears[idx].charAt(LEFT) != gears[idx - 1].charAt(RIGHT))
			leftGearRotate(idx - 1, direction * -1);
		rotate(idx, direction);
	}

	public static void rightGearRotate(int idx, int direction) {
		if (idx + 1 < gears.length && gears[idx].charAt(RIGHT) != gears[idx + 1].charAt(LEFT))
			rightGearRotate(idx + 1, direction * -1);
		rotate(idx, direction);
	}

	public static void leftGearRotate(int idx, int direction) {
		if (idx - 1 >= 0 && gears[idx].charAt(LEFT) != gears[idx - 1].charAt(RIGHT))
			leftGearRotate(idx - 1, direction * -1);
		rotate(idx, direction);
	}

	public static void rotate(int idx, int direction) {
		if (direction == 1) {
			gears[idx].insert(0, gears[idx].charAt(gears[idx].length() - 1));
			gears[idx].deleteCharAt(gears[idx].length() - 1);
		} else {
			gears[idx].append(gears[idx].charAt(0));
			gears[idx].deleteCharAt(0);
		}
	}
}