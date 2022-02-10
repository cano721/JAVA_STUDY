/**
    1. 2중포문으로 처리
    
    1. user스킬내에서 스킬을 돌면서 해당하는 스킬 순서대로 되어있는지 체크
**/

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String stree : skill_trees){
            int pnum = -1;
            boolean check = true;
            for(int i = 0; i < skill.length(); i++){
                int num = stree.indexOf(skill.charAt(i));
                if(num == -1){
                    pnum = 21;
                    continue;
                }
                if(num > pnum){
                    pnum = num;
                }else{
                    check = false;
                    break;
                }
            }
            if(check) answer++;
        }
        return answer;
    }
}