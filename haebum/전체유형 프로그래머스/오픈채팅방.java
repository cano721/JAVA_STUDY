/**
    1. uid로 구분 가능
    
    2. arrayList에 배열형태로 들어온것과 나간것을 저장
    2-1) [1][uid] 들어온것 [0][uid] 나간것
    
    3. uid에 해당하는 닉네임 맵에 저장
    
    4. 해당하는것으로 변경하여 배열로 반환
**/

import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        ArrayList<String[]> info = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        
        for(String s : record){
            String[] strs = s.split(" ");
            
            String inout = strs[0];
            String id = strs[1];
            String nick;
            
            if(inout.equals("Enter")){
                info.add(new String[] {"1",id});
                nick = strs[2];
                map.put(id,nick);
            }else if(inout.equals("Leave")){
                info.add(new String[] {"0",id});
            }else{
                nick = strs[2];
                map.put(id,nick);
            }
        }
        
        String[] answer = new String[info.size()];
        
        int idx = 0;
        for(Striqng[] s : info){
            if(s[0].equals("1")){
                answer[idx++] = map.get(s[1]) + "님이 들어왔습니다.";
            }else{
                answer[idx++] = map.get(s[1]) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}