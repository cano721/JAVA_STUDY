class PG_피로도 {
	boolean[] checked;
	int result = 0;

	public int solution(int k, int[][] dungeons) {
		checked = new boolean[dungeons.length];

		cal(0, k, 0, dungeons);
		return result;
	}

	public void cal(int depth, int remain, int res, int[][] dun) {
		if (depth == dun.length) {
			result = Math.max(res, result);
			return;
		}
		for (int i = 0; i < dun.length; i++) {
			if (!checked[i]) {
				checked[i] = true;
				if (remain >= dun[i][0]) 
					cal(depth + 1, remain - dun[i][1], res + 1, dun);
				else
					cal(depth + 1, remain, res, dun);
				checked[i] = false;
			}
		}
	}
}