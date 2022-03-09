package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * ī���� �ִ�.
	 * ī���� ���ʿ��� ��������� ĥ���� �ְ�, �ٱ������� ������ ĥ���� �ִ�.
	 * ������� ������ ���� �־��� ��, ������ ���̿� ������ ���̸� ���ϴ� ����
	 * ���ΰ� ���κ��� ���.
	 * @param brown ���� ������ ��
	 * @param yellow ��� ������ ��
	 * @return ī���� ����, ����
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