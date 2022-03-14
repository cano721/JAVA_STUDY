package programmers;

public class d220313_멀쩡한사각형 {

	public static void main(String[] args) {
		int w = 2;
		int h = 2;
		long ans = solution(w, h);	//8 12 80
		System.out.println(ans);
	}
	//타입 확인 필수! long타입으로 변경하기, h/w*i 가 아닌 i*h/w로 계산해야함 
	//숫자가 클땐 곱하기 먼저하는 걸 습관으로 들이자
	private static long solution(int W, int H) {
		long answer = 0;
        long w = (long)W;
        long h = (long)H;
        if(w <= 1 || h <= 1) return 0;
        double scope = (double)h/w;
        for (long i = 1; i <= w; i++) {
			answer += (int)Math.ceil((double)i*h/w) - (int)Math.floor((double)(i-1)*h/w);
		}
        return w*h - answer;
	}

}
