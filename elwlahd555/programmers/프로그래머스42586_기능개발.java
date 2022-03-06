package elwlahd555.programmers;

import java.util.ArrayList;

public class 프로그래머스42586_기능개발 {
	public static void main(String[] args) {
		int progresses[]= {93,30,55};
		int speeds[]= {1,30,5};
		System.out.println(solution(progresses, speeds));
	}
    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
		int cnt = 0;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		outer: while (cnt < progresses.length) {
			if (progresses[cnt] >= 100) {
				int temp = 0;
				for (int i = cnt; i < progresses.length; i++) {
					temp++;
					cnt++;
					if (cnt < progresses.length && progresses[cnt] < 100) {
						arr.add(temp);
						break;
					} else if (cnt == progresses.length) {
						arr.add(temp);
						break outer;
					}
				}
			}
			for (int i = 0; i < progresses.length; i++) {
				progresses[i] += speeds[i];
			}
		}
		answer = new int[arr.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = arr.get(i);
		}
        return answer;
    }
}
