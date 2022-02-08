import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        Set<Integer> set = new HashSet<>();
        for(int i : win_nums) set.add(i);
        
        int zeroCnt = 0;
        int conCnt = 0;
        for(int i =0; i<lottos.length; i++){
            if(lottos[i] == 0){
                zeroCnt++;
            }
            else{
                if(set.contains(lottos[i])) conCnt++;
            }
        }
        
        
        answer[0] = getRank(conCnt + zeroCnt);
        answer[1] = getRank(conCnt);
        return answer;
    }
    
    static int getRank(int k){
        return 7-k >= 6 ? 6 : 7-k;
    }
    
}