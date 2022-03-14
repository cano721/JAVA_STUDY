import java.util.HashSet;
import java.util.Set;

/*
 * set을 사용해 교점 저장
 * 다른 Collection보다 빠른 장점이 있음
 */

class PG_교점에별만들기 {
	public static String[] solution(int[][] line) {
		int minC = Integer.MAX_VALUE;
		int minR = Integer.MAX_VALUE;
		int maxC = Integer.MIN_VALUE;
		int maxR = Integer.MIN_VALUE;

		Set<String> set = new HashSet<>();
		for (int i = 0; i < line.length - 1; i++) {
			for (int j = i + 1; j < line.length; j++) {
				int a = line[i][0];
				int b = line[i][1];
				int e = line[i][2];

				int c = line[j][0];
				int d = line[j][1];
				int f = line[j][2];

				int temp = a * d - b * c;
				int temp1 = b * f - e * d;
				int temp2 = c * e - a * f;
				if (temp == 0 || temp1 % temp != 0 || temp2 % temp != 0)
					continue;

				int x = temp1 / temp;
				int y = temp2 / temp;

				set.add(x + "," + y);
				minR = Math.min(minR, x);
				minC = Math.min(minC, y);
				maxR = Math.max(maxR, x);
				maxC = Math.max(maxC, y);
			}
		}
		if (minR == Integer.MAX_VALUE) { // 교점이 없는 경우
			String[] answer = new String[1];
			answer[0] = "*";
			return answer;
		}

		String[] answer = new String[maxC - minC + 1];
		int index = 0;
		for (int i = maxC; i >= minC; i--) {
			StringBuilder sb = new StringBuilder();
			for (int j = minR; j <= maxR; j++) {
				if (set.contains(j + "," + i)) {
					sb.append("*");
				} else
					sb.append(".");
			}
			answer[index++] = sb.toString();
		}
		return answer;
	}
}