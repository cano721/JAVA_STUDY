package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * ù ���ڸ� �빮���� JadenCase�� ����� ����
	 * @param s ���ڿ�
	 * @return JadenCase�� s
	 */
    public String solution(String s) {
        StringBuilder answer = new StringBuilder(s.toLowerCase());
        if(isAlphabet(answer.charAt(0)))
            answer.setCharAt(0, (char)(answer.charAt(0)-32));
        for(int i=1; i<answer.length(); i++) {
            if(answer.charAt(i-1) == ' ' && isAlphabet(answer.charAt(i)))  {
                answer.setCharAt(i, (char)(answer.charAt(i)-32));
            }
        }
        return answer.toString();
    }
    
    public boolean isAlphabet(char ch) {
        return 'a' <= ch && ch <= 'z';
    }
}