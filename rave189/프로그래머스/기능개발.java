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
	 * ������ ��ɵ��� �����Ͽ� �����Ϸ��� �Ѵ�.
	 * ��ɵ��� ���� ��ɵ��� ��� �����Ǿ������ ������ �����ϴ�.
	 * �� ��ɵ��� ������ �Ϸ翡 ������ �� �ִ� ���� �־����� ��,
	 * �� �������� �� ���� ��ɵ��� �����Ǵ����� ���ϴ� ����
	 * �Ϸ翡 �տ��� 2�� �̻��� ��ɵ��� �Ϸ�Ǿ��ٸ� ���ÿ� ������ �����ϴ�.
	 * @param progresses �� ��ɵ��� ����
	 * @param speeds �� ��ɵ��� ���� �ӵ�
	 * @return ������ ��¥���� �� ���� ��ɵ��� �����Ǵ���
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