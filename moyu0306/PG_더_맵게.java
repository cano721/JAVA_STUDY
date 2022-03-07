import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int a : scoville){
            q.offer(a);
        }
        int answer = 0;
        while(q.size()>1&&q.peek()<K){
            int first = q.poll();
            int second = q.poll();
                first=first+second*2;
                q.offer(first);
                answer++;        
        }
        
        if(q.peek()<K) return -1;
        return answer;
    }
}