package Programmers;

import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] files =
//			{ "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG", "F15" };
				{ "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat", "F15" };
		String[] result = s.solution(files);
		for (String v : result)
			System.out.println(v);
	}
}

class Solution {
	/**
	 * 파일을 새로 정렬하는 프로그램
	 * 파일명을 크게 Head, Number, Tail로 나눌 수 있다.
	 * Head는 문자만 있는 부분이다.
	 * Number는 Head이후에 나오는 최대 다섯글자 숫자로 이루어진 부분이다.
	 * Tail은 Number뒤로 나오는 나머지 부분이다. (없을 수도 있다.)
	 * 우선 Head를 기준으로 대소문자 구분없이 사전 순으로 정렬한다.
	 * Head가 같으면 Number를 기준으로 정렬한다.
	 * Number에서 앞의 0은 무시한다.(0001 과 1은 같다.)
	 * Number도 같으면 입력이 들어온 순서대로 정렬한다.
	 * @param files
	 * @return
	 */
	public String[] solution(String[] files) {
		PriorityQueue<File> pq = new PriorityQueue<>();
		for (int i = 0; i < files.length; i++)
			pq.add(makeFile(i, files[i]));
		String[] answer = new String[pq.size()];
		for (int i = 0; i < answer.length; i++)
			answer[i] = pq.poll().name;
		return answer;
	}

	public File makeFile(int idx, String file) {
		String head = "";
		int index = 0;
		for (; index < file.length(); index++) {
			char ch = file.charAt(index);
			if ('0' <= ch && ch <= '9') {
				head = file.substring(0, index);
				break;
			}
		}
		int number = -1;
		int start = index;
		for (; index < file.length(); index++) {
			char ch = file.charAt(index);
			if (!('0' <= ch && ch <= '9') || index - start == 5) {
				number = Integer.parseInt(file.substring(start, index));
				break;
			}
		}
		if(number == -1)
			number = Integer.parseInt(file.substring(start));
		return new File(idx, number, file, head.toLowerCase());
	}
}

class File implements Comparable<File> {
	int idx, number;
	String name, head;

	public File(int idx, int number, String name, String head) {
		this.idx = idx;
		this.number = number;
		this.name = name;
		this.head = head;
	}

	@Override
	public int compareTo(File o) {
		int headCompare = head.compareToIgnoreCase(o.head);
		if (headCompare == 0) {
			if (number == o.number)
				return idx - o.idx;
			return number - o.number;
		}
		return headCompare;
	}

	@Override
	public String toString() {
		return "File [idx=" + idx + ", number=" + number + ", name=" + name + ", head=" + head + "]";
	}
}