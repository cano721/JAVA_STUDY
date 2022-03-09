package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * 카펫이 있다.
	 * 카펫의 안쪽에는 노란색으로 칠해져 있고, 바깥쪽으로 갈색이 칠해져 있다.
	 * 노란색과 갈색의 수가 주어질 때, 가로의 길이와 세로의 길이를 구하는 문제
	 * 가로가 세로보다 길다.
	 * @param brown 갈색 격자의 수
	 * @param yellow 노란 격자의 수
	 * @return 카펫의 가로, 세로
	 */
	public int[] solution(int brown, int yellow) {
        for(int col=1; col<=yellow; col++) {
            if(yellow%col == 0) {
                int row = yellow/col;
                int needBrown = col*2 + row*2 + 4;
                if(brown == needBrown) {
                    return new int[] {row + 2, col + 2};
                }
            }
        }
		return new int[] {};
    }
}