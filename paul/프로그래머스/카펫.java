class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int y=0, x=0;
        for(int i = 3; i<= brown/4+1; i++){
            x = i;
            y = brown/2+2-i;
            if(x*y == brown+yellow) break;
        }
        
        answer[0] = y;
        answer[1] = x;
        
        return answer;
    }
}
