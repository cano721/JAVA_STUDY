package programmers;

public class d220202_문자열압축 {

	public static void main(String[] args) {
		String s = "aabbaccc";	//aaaaaaaaaabbbbbbbbbb 일 때 10a10b가 된다.
		int answer = solution(s);
		System.out.println(answer);

	}

	private static int solution(String s) {
		int answer = Integer.MAX_VALUE;
		
		if(s.length() == 1) return 1;
		
		for (int i = 1; i <= s.length()/2; i++) {		//압축 사이즈
			int count = 1;
			boolean flag = false;
			String temp = "";
			String str = "";
			for (int j = 0; j < s.length()/i; j++) {	//압축
				if(s.substring(i*j, i*j + i).equals(temp)) {
					count++;
					flag = true;
					continue;
				}
				if(flag) {
					str += count + temp;
					count = 1;
					flag = false;
				}else {
					str += temp;
				}
				temp = s.substring(i*j, i*j + i);
			}
			if(flag) {
				str += count + temp;
			}else {
				str += temp;
			}
			
			answer = Math.min(answer, str.length() + s.length() % i);
		}
		return answer;
	}

}
