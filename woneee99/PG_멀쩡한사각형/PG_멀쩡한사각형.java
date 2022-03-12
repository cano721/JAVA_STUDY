class PG_멀쩡한사각형 {
    public long solution(int w, int h) {
        
		int c = gcd(w, h);
		long answer = (long)w * h - (w + h - c);
		
		return answer;
    }
    public int gcd(int a, int b) {
		int r = a % b;
		if(r == 0) return b;
		else return gcd(b, r);
	}
}