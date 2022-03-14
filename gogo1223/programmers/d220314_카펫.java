package programmers;

public class d220314_카펫 {
//	10	2	[4, 3]
//	8	1	[3, 3]
//	24	24	[8, 6]
	public static void main(String[] args) {
		int[] answer = solution(10, 2);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

	private static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int sum = brown + yellow;
		for (int i = 1; i <= sum; i++) {
			int height = i;
			int width = sum / height;
            
			if(height > width) continue;
            
			if((height - 2) * (width - 2) == yellow) {
				answer[0] = width;
				answer[1] = height;
				return answer;
			}
		}
		
		return answer;
	}

}
