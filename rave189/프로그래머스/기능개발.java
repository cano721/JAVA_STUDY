package Programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * 각각의 기능들을 개발하여 배포하려고 한다.
	 * 기능들은 앞의 기능들이 모두 배포되어야지만 배포가 가능하다.
	 * 각 기능들의 진도와 하루에 개발할 수 있는 양이 주어졌을 때,
	 * 각 배포마다 몇 개의 기능들이 배포되는지를 구하는 문제
	 * 하루에 앞에서 2개 이상의 기능들이 완료되었다면 동시에 배포가 가능하다.
	 * @param progresses 각 기능들의 진도
	 * @param speeds 각 기능들의 개발 속도
	 * @return 각각의 날짜마다 몇 개의 기능들이 배포되는지
	 */
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        Queue<Task> q = new LinkedList<>();
        for(int i=0; i<progresses.length; i++) {
            q.add(new Task(progresses[i], speeds[i]));
        }
        while(!q.isEmpty()) {
            Task cur = q.poll();
            int day = (int)Math.ceil((100-cur.progress) / (double)cur.speed);
            int size = q.size();
            while(size-- > 0) {
                Task next = q.poll();
                next.run(day);
                q.add(next);
            }
            int result = 1;
            while(!q.isEmpty() && q.peek().progress >= 100) {
                q.poll();
                result++;
            }
            answer.add(result);
        }
        return answer.stream().mapToInt(v -> v.intValue()).toArray();
    }
}

class Task {
    int progress, speed;
    public Task(int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
    }
    
    public void run(int day) {
        this.progress += speed * day;
    }
}