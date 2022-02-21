package elwlahd555.socartest;

import java.util.LinkedList;

public class test_swap {
	public static void main(String[] args) {
		int numbers[]= {10,40,30,20};
		int k=20;
		System.out.println(solution(numbers,k));
	}
	private static class Point{
		int []numbers;
		int count;
		public Point(int[] numbers, int count) {
			super();
			this.numbers = numbers;
			this.count = count;
		}
		
	}
	private static int solution(int[] numbers, int k) {
		int answer=Integer.MAX_VALUE;
		LinkedList<Point> que=new LinkedList<Point>();
		que.add(new Point(numbers,0));
		while(!que.isEmpty()) {
			Point p=que.poll();
			if(p.count>answer) {
				continue;
			}
			if(check(p,k)) {
				if(answer>p.count) {
					answer=p.count;
				}
			}
			for (int i = 0; i < p.numbers.length-1; i++) {
				for (int j = i+1; j < p.numbers.length; j++) {
					que.add(new Point(make(i,j,p.numbers.clone()),p.count+1));
				}
				
			}
		}
		return answer;
	}
	private static int[] make(int i, int j, int[] numbers) {
		int temp=numbers[i];
		numbers[i]=numbers[j];
		numbers[j]=temp;
		return numbers;
	}
	private static boolean check(Point p, int k) {
		for (int i = 1; i < p.numbers.length-1; i++) {
			if(Math.abs(p.numbers[i]-p.numbers[i-1])>k||Math.abs(p.numbers[i]-p.numbers[i+1])>k) {
				return false;
			}
		}
		return true;
	}
}
