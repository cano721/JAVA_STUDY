import java.util.*;

public class Solution {
    public int solution(int distance) {
        int answer=0;

        while(distance != 0 ){
            if(distance%2==0){
                distance=distance/2;
            }else{
                distance=distance-1;
                answer++;
            }

        }
        return answer;
    }
}