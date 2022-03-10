/**
    1. 가로*2 + (세로-2)*2 = 갈색 타일 수
    
    2. (가로-2)*(세로-2) = 노랑 타일 수
    
    3. 세로를 증가하면서 확인하기
**/

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer;
        
        int sum = brown + yellow;
        
        int h = 1;
        
        while(true){
            if(sum%h == 0){
                int w = sum/h;
                if(w*2+(h-2)*2 == brown && (w-2)*(h-2) == yellow){
                    answer = new int[] {w,h};
                    break;
                }
            }
            h++;
        }
        
        return answer;
    }
}