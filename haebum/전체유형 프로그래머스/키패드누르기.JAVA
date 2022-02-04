import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        
        // 초기값
        int[] lhand = {3,0};
        int[] rhand = {3,2};
        
        // 각 번호 위치 저장 맵
        Map<Integer,int[]> map = new HashMap<>();
        
        int cnt = 1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                map.put(cnt++,new int[] {i,j});
            }
        }
        map.put(0,new int[] {3,1});
            
            
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){
                lhand = map.get(numbers[i]);
                answer += "L";
            }else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
                rhand = map.get(numbers[i]);
                answer += "R";
            }else{
                int lhandNum, rhandNum;
                
                int[] number = map.get(numbers[i]);
                
                lhandNum = Math.abs(number[0]-lhand[0]) + Math.abs(number[1]-lhand[1]);
                rhandNum = Math.abs(number[0]-rhand[0]) + Math.abs(number[1]-rhand[1]);
                
                if(lhandNum == rhandNum){
                    if(hand.equals("right")){
                        answer += "R";
                        rhand = number;
                    }else{
                        answer += "L";
                        lhand = number;
                    }
                }else if(lhandNum > rhandNum){
                    answer += "R";
                    rhand = number;
                }else{
                    answer += "L";
                    lhand = number;
                }
                
            }
        }
        return answer;
    }
}