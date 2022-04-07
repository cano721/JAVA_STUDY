package elwlahd555.programmers;

import java.util.LinkedList;

public class 프로그래머스43163_단어_변환 {
	public static void main(String[] args) {
		String begin ="hit";
		String target = "cog";
		String words[]= {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(solution(begin, target, words));
	}
	private static class Point{
		String word;
		int cnt;
		public Point(String word, int cnt) {
			super();
			this.word = word;
			this.cnt = cnt;
		}
	}
    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        LinkedList<Point> que=new LinkedList<Point>();
        que.add(new Point(begin,0));
        if(!check(target,words)) {
        	que.poll();
        }
        while(!que.isEmpty()) {
        	Point p =que.poll();
        	if(p.word.equals(target)) {
        		answer=p.cnt;
        		break;
        	}
        	for (int i = 0; i < words.length; i++) {
        		int count=0;
				for (int j = 0; j < p.word.length(); j++) {
					if(words[i].charAt(j)==p.word.charAt(j)) {
						count++;
					}
				}
				if(p.word.length()-count==1) {
					que.add(new Point(words[i],p.cnt+1));
				}
			}
        }
        return answer;
    }
	private static boolean check(String target, String[] words) {
		for (int i = 0; i < words.length; i++) {
			if(target.equals(words[i])) {
				return true;
			}
		}
		return false;
	}
}
