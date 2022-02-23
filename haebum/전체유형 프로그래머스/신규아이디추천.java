/**
    1. toLowerCase()로 소문자 치환
    
    2. replaceAll(정규식,"") 으로 제거
    
    3. replaceAll([.]{2,},".") 으로 2번이상 .을 제거
    
    4. 시작부분이나 끝부분 . 제거
    
    5. id가 비어 있으면, "a"로 변경
    
    6. id 길이 16이상이면 15까지 substring으로 제거
    6-1) 제거 후 끝부분 .이면 제거
    
    7. 2자이하면 id마지막 글자 추가
**/

class Solution {
    public String solution(String new_id) {
        
        new_id = new_id.toLowerCase();
        
        new_id = new_id.replaceAll("[^a-z0-9-_.]","");
        
        new_id = new_id.replaceAll("[.]{2,}",".");
        
        new_id = new_id.replaceAll("^[.]|[.]$","");
        
        if(new_id.equals("")){
            return "aaa";
        }
        
        new_id = new_id.length() > 15 ? new_id.substring(0,15) : new_id;
        new_id = new_id.replaceAll("^[.]|[.]$","");
        
        while(new_id.length() <= 2){
            new_id = new_id + new_id.charAt(new_id.length()-1);
        }
        
        return new_id;
    }
}