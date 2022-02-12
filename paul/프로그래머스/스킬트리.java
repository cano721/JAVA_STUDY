class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(int i =0; i< skill_trees.length; i++){
            answer += isPossible(skill, skill_trees[i]);
        }
        return answer;
    }
    
    static int isPossible(String skill, String target){
        int idx =0;
        for(int i=0; i< skill.length(); i++){
            int k = skill.charAt(i);
            int now = target.indexOf(k);
            if(now == -1){
                idx = -1;
                continue;
            }
            if(idx == -1 && now != -1) return 0;
            if(idx > now) return 0;
            idx = now;
        }
        return 1;
    }
}