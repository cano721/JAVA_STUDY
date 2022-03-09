package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * �簢���� �־�����.
	 * �� �簢���� (0, 0)�������� (w, h)�������� ���������� �߶���.
	 * �簢������ 1x1 �簢���� ������ ���ϴ� ����
	 * 
	 * w+h - gcd(w, h)�� 1x1�� �ƴ� �簢���� ������ ���� �� �ִ�.
	 * @param w ���� ����
	 * @param h ���� ����
	 * @return 1x1 �簢���� ����
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