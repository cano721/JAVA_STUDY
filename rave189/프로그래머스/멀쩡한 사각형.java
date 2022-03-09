package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * 사각형이 주어진다.
	 * 이 사각형의 (0, 0)지점에서 (w, h)지점까지 일직선으로 잘랐다.
	 * 사각형에서 1x1 사각형의 개수를 구하는 문제
	 * 
	 * w+h - gcd(w, h)로 1x1이 아닌 사각형의 개수를 구할 수 있다.
	 * @param w 가로 길이
	 * @param h 세로 길이
	 * @return 1x1 사각형의 개수
	 */
	public long solution(int w, int h) {
        long answer = (long)w*h;
        if(w == h)
            answer -= w;
        else {
            int gcd = gcd(w, h);
            int count = getRemoveCnt(w/gcd, h/gcd);
            answer -= count * gcd;
        }
        return answer;
    }
    
    public int gcd(int a, int b) {
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    
    public int getRemoveCnt(int w, int h) {
        int count = 0;
        for(int i=w, j=h; !(i == 0 && j == 0);) {
            if(i >= j) {
                i--;
                count++;
            } else {
                j--;
                count++;
            }
        }
        return count-1;
    }
}