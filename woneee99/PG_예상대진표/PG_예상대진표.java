class PG_예상대진표 {
	public int solution(int n, int a, int b) {
		int count = 0;
		while (true) {
			count++;
			int x = (int) Math.ceil((double) a / 2);
			int y = (int) Math.ceil((double) b / 2);
			if (x == y)
				break;

			a = x;
			b = y;
		}
		return count;
	}
}