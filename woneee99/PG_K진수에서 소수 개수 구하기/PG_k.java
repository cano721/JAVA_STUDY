public class PG_k {
    public int solution(int n, int k) {
        int answer = 0;
		int num = n;
		
		StringBuilder sb = new StringBuilder();
		while(num > 0) {
			sb.append(num%k);
			num = num/k;
		}
		sb.reverse();

		String []arr = sb.toString().split("0");
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i].equals("")) continue;
			if(cal(Long.valueOf(arr[i])))answer++;
		}
        
		return answer;
    }
    static boolean cal(long x) {

		if(x == 1) return false;
		
		int result = (int)Math.sqrt(x);
		for(int j=2; j<=result; j++) {
			if(x%j == 0) return false;
		}
		return true;
	}
}