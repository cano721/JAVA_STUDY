import java.util.*;
class Solution {
    static class Pair{
        int y,x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }
    }
    
    static Pair[] arr = new Pair[12];
    
    static void init(){
        arr[0] = new Pair(3,1);
        arr[10] = new Pair(3,0);
        arr[11] = new Pair(3,2);
        
        for(int i = 1; i<= 9; i++){
            int k = i-1;
            arr[i] = new Pair(k/3, k%3 );
        }
    }
    
    public String solution(int[] numbers, String hand) {
        init();
        String answer = "";
        int left =10, right = 11;
        String standardHand;
        if(hand.equals("left")) standardHand = "L";
        else standardHand = "R";
        
        for(int i = 0; i< numbers.length; i++){
            if( numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){
                answer += "L";
                left = numbers[i];
            }
            else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
                answer += "R";
                right = numbers[i];
            }else{
                int leftLen = getLen(numbers[i], left);
                int rightLen = getLen(numbers[i], right);
                
                if(leftLen > rightLen) {
                    answer += "R";
                    right = numbers[i];
                }
                else if(leftLen < rightLen){
                    answer += "L";  
                    left = numbers[i];
                } 
                else {
                    answer += standardHand;
                    if(standardHand.equals("R")) right = numbers[i];
                    else left= numbers[i];
                } 
            }
        }
        return answer;
    }
    
    static int getLen(int i, int j){
        int dy = Math.abs(arr[i].y - arr[j].y);
        int dx = Math.abs(arr[i].x - arr[j].x);
        return dy+dx;
    }
}