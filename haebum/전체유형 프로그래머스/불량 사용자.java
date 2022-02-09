/**
    1. dfs를 통해 전체 경우의 수 확인
    
    2. dfs를 통한 선택여부 확인은 비트마스크 사용
    
    3. 선택할땐 유저 아이디가 불량아이디가 될 수 있는지 체크
    
    5. 다 선택했을때, set에 그 경우의 수 저장(중복제거)
    
    6. set 사이즈반환
    

    다른 풀이(참고)

    3번에서 정규식 사용..
    String reg = banned_id[stage].replace("*", "[\\w\\d]");
    형태를 두고
    user_id[i].matches(reg) 로 비교 가능..
    
**/

import java.util.*;

class Solution {
    
    public int answer = 0;
    public Set<Integer> set;
    
    public int solution(String[] user_id, String[] banned_id) {
        
        set = new HashSet<>();
        
        dfs(0,0,user_id,banned_id);
        
        return set.size();
    }
    
    public void dfs(int stage, int bit,String[] user_id, String[] banned_id){
        
        if(stage == banned_id.length){
            set.add(bit);
            return;
        }
        
        for(int i = 0; i < user_id.length; i++){
            if(((bit>>i) & 1) != 1 && checkId(user_id[i],banned_id[stage])){
                dfs(stage+1,bit|(1<<i),user_id, banned_id);
            }
        }
    }
    
    public boolean checkId(String user, String ban){
        
        if(user.length() != ban.length()){
            return false;
        }
        
        for(int i = 0; i < ban.length(); i++){
            if(ban.charAt(i) == '*') continue;
            if(ban.charAt(i) != user.charAt(i)){
                return false;
            }
        }
        
        return true;
    }
    
}