package 전체유형문제풀이;

import java.util.*;

public class PG42586_기능개발 {

	public static void main(String[] args) {
		
		int[] progresses = {93,30,55};
		int[] speeds = {1,30,5};
		
		ArrayList<Integer> res = solution(progresses, speeds);
		
		for (int i = 0; i < res.size(); i++) {
			System.out.print(res.get(i)+" ");
		}

	}

	private static ArrayList<Integer> solution(int[] progresses, int[] speeds) {
		ArrayList<Integer> answer = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        
        int tempMax = calculateRemain(progresses[0], speeds[0]);
        stack.push(tempMax);

        for (int i = 1; i < progresses.length; i++) {
            // 남은 remain
            int remain = calculateRemain(progresses[i], speeds[i]);

            // 핵심은 만약에 peek보다 remain이 더 크면 이전 까지 다 함께 배포해야함.
            if (tempMax < remain) {
                int unit = 0;
                // 스택 갯수만큼 유닛이 쌓임.
                while (!stack.isEmpty()) {
                    unit += 1;
                    stack.pop();
                }
                tempMax = remain;
                answer.add(unit);
            }
            // 매번 스택에 푸쉬 하는건 맞음. 대신에
            stack.push(remain);
        }

        // 남은 스택이 있다면 추가로 작업
        if (!stack.isEmpty()) {
            int unit = 0;
            while (!stack.isEmpty()) {
                unit += 1;
                stack.pop();
            }
            answer.add(unit);
        }

        return answer;
    }

    public static int calculateRemain(int progress, int speed) {
        int remain = (100 - progress) / speed;
        if ((100 - progress) % speed != 0) {
            return remain + 1;
        }
        return remain;
	}

}
