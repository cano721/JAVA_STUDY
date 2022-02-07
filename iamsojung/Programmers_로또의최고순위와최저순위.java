import java.util.HashSet;
class Solution {
    static public int[] solution(int[] lottos, int[] win_nums) {

        int[] answer = new int[2];
        HashSet<Integer> hs = new HashSet<>();

        for(int i=0;i<win_nums.length;i++){
            hs.add(win_nums[i]);
        }

        int ansMax = 7;
        int ansMin = 7;

        for(int j=0;j<lottos.length;j++){
            if(hs.contains(lottos[j])){
                //System.out.println(lottos[j]);
                ansMin--;
                ansMax--;
            }else if(lottos[j]==0){
                ansMax--;
            }
        }

        if(ansMax==7){
            ansMax=6;
        }
        if(ansMin==7){
            ansMin=6;
        }
        answer[0] = ansMax;
        answer[1] = ansMin;



        return answer;
    }
}