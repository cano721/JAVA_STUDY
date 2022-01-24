package 전체유형문제풀이;

public class PG17682_다트게임1차 {

	public static void main(String[] args) {
		
		String dartResult ="1D#2S*3S";
		
		int res = solution(dartResult);

		System.out.print(res);

	}

	private static int solution(String dartResult) {
		int answer = 0;
        int n = 0, index = 0, nowInt = 0;
        int[] score = new int[3]; // 각 단계별 획득 점수(다트 게임은 총 3번의 기회)
 
        
        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);
 
            /* 점수일 경우 */
            if (Character.isDigit(c)) {
                n = 0;
                if (c == '1' && dartResult.charAt(i + 1) == '0') {
                    n = 10;
                    i++;
                } else {
                    n = Character.getNumericValue(c);
                }
                
                nowInt++;
                
            /* 보너스 or 옵션일 경우 */
            } else {
                switch (c) {
                case 'S':
                    score[index++] = (int) Math.pow(n, 1);    // 1제곱
                    break;
                    
                case 'D':
                    score[index++] = (int) Math.pow(n, 2);    // 2제곱
                    break;
                    
                case 'T':
                    score[index++] = (int) Math.pow(n, 3);    // 3제곱
                    break;
                    
                case '*':    // 스타상 :  바로 전에 얻은 점수를 각 2배
                    index = index - 2 < 0 ? 0 : index - 2;
                    while (index < nowInt) {
                        score[index++] *= 2;
                    }
                    n = 0;
                    break;
                    
                case '#':    // 아차상 :  해당 점수는 마이너스
                    score[index-1] *= -1;    
                    n = 0;
                    break;
                }
            }
        }
        
        for (int i : score) {
            answer += i;
        }
 
        return answer;
	}

	

}
