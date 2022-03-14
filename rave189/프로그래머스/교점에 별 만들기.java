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
	 * Ax + By + C = 0���� �̷���� ������ �־�����.
	 * �� �������� �׷����� �׷��� ��, ������� ������ �������� �����.
	 * ������ ����� �κп��� *��, �� ����� �κп��� .�� ��� ����ϴ� ����
	 * �迭�� �׵θ��� ���� �ٱ��� *�� ���缭 ©�� ��ȯ�Ѵ�.
	 * ....
	 * ..*.  -> ..*
	 * *...     *..
	 * �ٱ��ʿ� .���θ� �̷���� �κ��� ��� �߶󳻰� ��ȯ�Ѵ�.
	 * 
	 * Ax + By + C = 0
	 * Cx + Dy + F = 0
	 * �� ������ ������ x = (bf-ed)/(ad-bc), y =(ec-af)/(ad-bc)�̴�.
	 * ������ ������ Ŀ bf, ec ���� ���ϸ� int ������ �ʰ��Ѵ�.
	 * long���� Ÿ���� �����ϰ� ��ȯ�ϴ� �迭���� ���� int ����ȯ�Ͽ� �����Ѵ�.
	 * @param line ������
	 * @return ������ ���� ���� �迭
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