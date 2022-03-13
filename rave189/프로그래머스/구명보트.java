package Programmers;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        for(int i=0, j=people.length-1; i <= j;) {
            int sum = people[i] + people[j];
            if(sum <= limit) {
                i++;
            }
            answer++;
            j--;
        }
        return answer;
    }
}