import java.util.*;


class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
       int len = progresses.length;
       ArrayList<Integer> list = new ArrayList<>();
        int idx = 0;
        for(int i= idx; i<len; i = idx){
            int current  = idx;
            System.out.println(i);
            int left = (99-progresses[i]+speeds[i])/speeds[i]; // 계산방법
            // if(left <=0) break;
            
            for(int j = i; j<len;j++){
                progresses[j]+= speeds[j]*left; 
            }
            
            for(int k= i; k<len;k++){
                if(progresses[k]>=100) idx++;
                else break;
            }
            
            list.add(idx-current);
        }
       int[] answer = new int[list.size()];
       for(int i=0 ; i< list.size(); i++){
           answer[i] = list.get(i);
       }
        
        return answer;
    }
}