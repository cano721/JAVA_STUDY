import java.util.*;
class Solution {
    public int solution(int[] scoville, int k) {
        int answer = 0;
        PriorityQueue<Integer> pq= new PriorityQueue<>();
        
        for(int i : scoville){
            pq.add(i);
        }
        
        while(pq.size() > 1 && pq.peek() <k){
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + 2*b);
            answer++;
        }
        if(pq.peek() < k) return -1 ;
        return answer;
    }
}