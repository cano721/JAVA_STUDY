class Solution {
	public int solution(int[] a) {
		int answer = 2;
		int[] fmin = new int[a.length];
		int[] bmin = new int[a.length];
		int tmp = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			fmin[i] = Math.min(tmp, a[i]);
			tmp = fmin[i];

		}
		
		tmp = Integer.MAX_VALUE;
		for (int j = a.length - 1; j >= 0; j--) {
			bmin[j] = Math.min(tmp, a[j]);
			tmp = bmin[j];

		}

		for (int i = 1; i < a.length - 1; i++) {
			int temp = a[i];
			if (temp > fmin[i - 1] && temp > bmin[i + 1]) {
				continue;
			}
			answer++;
		}

		return answer;
	}
}
