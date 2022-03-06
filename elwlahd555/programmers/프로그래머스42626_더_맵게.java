package elwlahd555.programmers;

import java.util.PriorityQueue;

public class 프로그래머스42626_더_맵게 {
	public static void main(String[] args) {
		int scoville[]= {1, 2, 3, 9, 10, 12};
		int k=7;
		System.out.println(solution(scoville, k));
	}
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        		PriorityQueue<Integer>que=new PriorityQueue<Integer>();
		for (int i = 0; i < scoville.length; i++) {
			que.add(scoville[i]);
		}
		int temp=que.poll();
		while(temp<K) {
                        			if(que.size()==0) {
				answer=-1;
				break;
			}
			int number=temp+(que.poll()*2);
			que.add(number);
			temp=que.poll();
			answer++;

		}
        return answer;
    }
}
