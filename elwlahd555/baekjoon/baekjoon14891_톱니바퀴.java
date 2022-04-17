package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14891_톱니바퀴 {
	private static int wheel[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int answer = 0;
		wheel = new int[5][8];
		for (int i = 1; i < 5; i++) {
			String number = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = number.charAt(j) - '0';
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			int rot[] = new int[5];
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int move = Integer.parseInt(st.nextToken());
			switch (num) {
			case 1:
				rot[1] = move;
				if (wheel[1][2] != wheel[2][6]) {
					rot[2] = rot[1] * -1;
				} else {
					rot[2] = 0;
				}
				if (wheel[2][2] != wheel[3][6]) {
					rot[3] = rot[2] * -1;
				} else {
					rot[3] = 0;
				}
				if (wheel[3][2] != wheel[4][6]) {
					rot[4] = rot[3] * -1;
				} else {
					rot[4] = 0;
				}
				break;
			case 2:
				rot[2] = move;
				if (wheel[1][2] != wheel[2][6]) {
					rot[1] = rot[2] * -1;
				} else {
					rot[1] = 0;
				}
				if (wheel[3][6] != wheel[2][2]) {
					rot[3] = rot[2] * -1;
				} else {
					rot[3] = 0;
				}
				if (wheel[3][2] != wheel[4][6]) {
					rot[4] = rot[3] * -1;
				} else {
					rot[4] = 0;
				}
				break;
			case 3:
				rot[3] = move;
				if (wheel[3][2] != wheel[4][6]) {
					rot[4] = move * -1;
				} else {
					rot[4] = 0;
				}
				if (wheel[3][6] != wheel[2][2]) {
					rot[2] = move * -1;
				} else {
					rot[2] = 0;
				}
				if (wheel[2][6] != wheel[1][2]) {
					rot[1] = rot[2] * -1;
				} else {
					rot[1] = 0;
				}
				break;
			case 4:
				rot[4] = move;
				if (wheel[4][6] != wheel[3][2]) {
					rot[3] = rot[4] * -1;
				} else {
					rot[3] = 0;
				}
				if (wheel[3][6] != wheel[2][2]) {
					rot[2] = rot[3] * -1;
				} else {
					rot[2] = 0;
				}
				if (wheel[2][6] != wheel[1][2]) {
					rot[1] = rot[2] * -1;
				} else {
					rot[1] = 0;
				}
				break;
			}
			rotation(rot);
		}
		for (int i = 1; i < 5; i++) {
			answer += wheel[i][0] * Math.pow(2, i - 1);
		}
		System.out.println(answer);
	}

	private static void rotation(int[] rot) {
		for (int i = 1; i < 5; i++) {
			if (rot[i] == 0) {
				continue;
			}
			if (rot[i] == 1) {
				int temp = wheel[i][7];
				int temp2 = 0;
				for (int j = 0; j < 8; j++) {
					temp2 = wheel[i][j];
					wheel[i][j] = temp;
					temp = temp2;
				}
			} else {
				int temp = wheel[i][0];
				int temp2 = 0;
				for (int j = 7; j >= 0; j--) {
					temp2 = wheel[i][j];
					wheel[i][j] = temp;
					temp = temp2;
				}
			}
		}
	}

}
