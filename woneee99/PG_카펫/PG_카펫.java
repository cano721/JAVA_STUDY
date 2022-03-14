class PG_카펫 {
	public int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int sum = brown + yellow;

		int r = (int) (Math.sqrt(sum));
		for (int i = 1; i <= r; i++) {
			int row = i;
			int col = sum / i;

			if ((row - 2) * (col - 2) == yellow && row * col == sum) {
				answer[0] = row > col ? row : col;
				answer[1] = sum / answer[0];
			}
		}
		return answer;
	}
}