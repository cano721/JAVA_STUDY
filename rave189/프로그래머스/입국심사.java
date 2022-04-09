package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
    public long solution(int n, int[] times) {
        long left = 0, right = 10000000000000L;
        while(left <= right) {
            long mid = (left + right)/2;
            long cnt = 0;
            for(int time: times)
                cnt += mid/time;
            if(cnt >= n)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }
}