package programmers;

public class d220208_로또의최고순위와최저순위 {

	public static void main(String[] args) {
		int[] lottos = {44, 1, 0, 0, 31, 25}; 
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		int[] answer = solution(lottos, win_nums);
		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i]+" ");
		}
	}

	private static int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];
		int zeroNum = 0;	//모르는 수 개수
		int lottoNum = 0;	//lotto 맞춘 개수
		
		for (int i = 0; i < lottos.length; i++) {
			if(lottos[i] == 0) {
				zeroNum++;
				continue;
			}
			for (int j = 0; j < win_nums.length; j++) {
				if(lottos[i] == win_nums[j]) {
					lottoNum++;
					break;
				}
			}
		}
		answer[0] = 7 - (lottoNum + zeroNum);	//최고등수
		answer[1] = 7 - lottoNum;				//최저등수
		
		if(lottoNum == 0) {
			answer[1] = 6;
			if(zeroNum == 0) {
				answer[0] = 6;
			}
		}
		return answer;
	}

}
