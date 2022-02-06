package programmers;

public class d220206_키패드누르기 {

	public static void main(String[] args) {
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		String answer = solution(numbers, hand);	//LRLLLRLLRRL
		System.out.println(answer);

	}

	private static String solution(int[] numbers, String hand) {
		String defaultHand = hand.equals("right") ? "R" : "L";
		int leftHand = 10;
		int rightHand = 12;
		String answer = "";
		for (int i = 0; i < numbers.length; i++) {
			if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				answer += "L";
				leftHand = numbers[i];
			}
			else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				answer += "R";
				rightHand = numbers[i];
			}
			else {
				if(numbers[i] == 0) numbers[i] = 11;
                
				int distL = dist(leftHand-1, numbers[i]-1);
                int distR = dist(rightHand-1, numbers[i]-1);
                
                if(distL == distR) {
                	answer += defaultHand;
                	if(hand.equals("right")) rightHand = numbers[i];
                	else leftHand = numbers[i];
                }
                else if(distL > distR) {
                	answer += "R";
                	rightHand = numbers[i];
                }
                else {
                	answer += "L";
                	leftHand = numbers[i];
                }
			}
		}
		return answer;
	}

	private static int dist(int a, int b) {
        int x1 = a / 3, y1 = a % 3;
        int x2 = b / 3, y2 = b % 3;
        
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}

}
