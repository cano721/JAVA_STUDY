package programmers;

public class d220313_멀쩡한사각형 {

	public static void main(String[] args) {
		int w = 2;
		int h = 2;
		long ans = solution(w, h);	//8 12 80
		System.out.println(ans);
	}

	private static long solution(int w, int h) {
		long answer = 0;
		if(w <= 1 || h <= 1) return 0;
		//좌표평면으로 생각하고 width 1씩 증가하며 지나가는 height 값 빼주었다.
		//무엇이 문제일까...
		for (int i = 1; i <= w; i++) {
			answer += (int)Math.ceil((double)h/w * i) - (int)Math.floor((double)h/w * (i-1));
		}
		
		return w*h - answer;
	}

	private static int func(boolean isBefore, int x, int w, int h) {
		if(isBefore) {
			return (int)Math.floor((double)h/w * x);
		}else {
			return (int)Math.ceil((double)h/w * x);
		}
		
	}

}
