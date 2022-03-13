package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	HashSet<Point> hash = new HashSet<>();

	/**
	 * Ax + By + C = 0으로 이루어진 직선이 주어진다.
	 * 각 직선들을 그래프에 그렸을 때, 직선들과 만나는 교점들이 생긴다.
	 * 교점이 생기는 부분에는 *를, 안 생기는 부분에는 .을 찍어 출력하는 문제
	 * 배열의 테두리를 가장 바깥쪽 *에 맞춰서 짤라 반환한다.
	 * ....
	 * ..*.  -> ..*
	 * *...     *..
	 * 바깥쪽에 .으로만 이루어진 부분은 모두 잘라내고 반환한다.
	 * 
	 * Ax + By + C = 0
	 * Cx + Dy + F = 0
	 * 두 직선의 교점은 x = (bf-ed)/(ad-bc), y =(ec-af)/(ad-bc)이다.
	 * 숫자의 범위가 커 bf, ec 등을 곱하면 int 범위를 초과한다.
	 * long으로 타입을 선언하고 반환하는 배열에는 강제 int 형변환하여 생성한다.
	 * @param line 직선들
	 * @return 교점에 별을 찍은 배열
	 */
	public String[] solution(int[][] line) {
		long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE, minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;
		for (int i = 0; i < line.length; i++) {
			double a = line[i][0], b = line[i][1], e = line[i][2];
			for (int j = i + 1; j < line.length; j++) {
				double c = line[j][0], d = line[j][1], f = line[j][2];
				if (a * d - b * c != 0) {
					double x = (b * f - e * d) / (a * d - b * c);
					double y = (e * c - a * f) / (a * d - b * c);
					if (x == (long) x && y == (long) y) {
						hash.add(new Point((long) x, (long) y));
						maxX = Math.max(maxX, (long) x);
						maxY = Math.max(maxY, (long) y);
						minX = Math.min(minX, (long) x);
						minY = Math.min(minY, (long) y);
					}
				}
			}
		}
		String[] answer = new String[(int) (maxY - minY + 1)];
		int idx = 0;
		for (long i = maxY; i >= minY; i--, idx++) {
			StringBuilder result = new StringBuilder();
			for (long j = minX; j <= maxX; j++) {
				if (hash.contains(new Point(j, i)))
					result.append("*");
				else
					result.append(".");
			}
			answer[idx] = result.toString();
		}
		return answer;
	}
}

class Point {
	long x, y;

	public Point(long x, long y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		Point other = (Point) o;
		if (x == other.x && y == other.y)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return (x + "" + y).hashCode();
	}
}