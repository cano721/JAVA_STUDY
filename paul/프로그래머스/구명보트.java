import java.util.*;
/**
 *  현재 가장 무거운 사람 + 현재 가벼운 사람 <= limit  ==> 두 사람다 태우기.
 *  현재 가장 무거운 사람 + 현재 가벼운 사람 > limit ==> 무거운 사람만 태우기.
 */
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int right = people.length-1;
        int left= 0;
        while(left <= right){
            if(people[right] + people[left] <= limit){
                left++;
            }
            right--;
            answer++;
        }
        return answer;
    }
}