package programmers;

import java.util.Arrays;

public class d220315_구명보트 {

	public static void main(String[] args) {
		int[] people = {70, 50, 80, 50};
		int ans = solution(people, 100);
		System.out.println(ans);
	}

	private static int solution(int[] people, int limit) {
		int answer = 0;
	    int min = 0;
	    Arrays.sort(people);

	    for (int max = people.length - 1; min <= max; max--){
	      if (people[min] + people[max] <= limit) min++;
	      answer++;
	    }

	    return answer;
	}

}
