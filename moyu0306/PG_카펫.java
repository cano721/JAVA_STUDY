import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int tot = brown +yellow;
        int max = 72;
        for(int h=3; h<1500;h++){
           if(tot%h==0){
               int w = tot / h;
               if((w-2)*(h-2)==yellow) return new int[]{w,h};
           } 
            
        }
        return new int[]{-1,-1};
    }
}