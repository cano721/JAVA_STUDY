import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        int searchIdx =0;
        int time =0;
        int tot =0;
        int len = jobs.length;
        Arrays.sort(jobs,(x,y)->{return x[0] -y[0];}); // 들어온 시간 순서대로 정렬
        PriorityQueue<int[]> q = new PriorityQueue<>((x,y) -> {return x[1] - y[1] ;}); // 실행시간이 짧은 순으로 정렬
           
        int i=0;
        
        while(!q.isEmpty()||i<len){
            while(i<len&&jobs[i][0]<=time) q.offer(jobs[i++]); // 해당 시간까지 도착한 작업들을 모두 enq
            
            if(q.isEmpty()){
                time= jobs[i][0];
                continue; // 같은 시간에 중복 접수 가능.
            }           
           
                
            int[] job = q.poll();
            time += job[1];
            tot +=(time-job[0]);
        }
        tot = tot/len;
         return tot;
    }
}