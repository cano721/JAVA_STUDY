import java.util.Arrays;

class PG_구명보트 {
	public int solution(int[] people, int limit) {

		Arrays.sort(people);

		int k = 0;
		int answer = 0;

		for (int i = people.length - 1; i >= 0; i--) {
			if (k > i)
				break;
			if (people[i] + people[k] <= limit) {
				k++;
			}

			answer++;
		}
		return answer;
	}
}