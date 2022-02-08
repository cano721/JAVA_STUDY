class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int cnt = 0; 
        int zeroCnt = 0;

        for(int a : lottos){
            if(a == 0) {
                zeroCnt++;
                continue;
            }
            
           for(int b : win_nums){
               if(a == b) cnt++;
           }
        }

        answer[0] = Math.min(7 - cnt - zeroCnt, 6);
        answer[1] = Math.min(7 - cnt, 6);
        return answer;
    }
}
