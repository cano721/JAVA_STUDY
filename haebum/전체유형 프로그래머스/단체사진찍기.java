/**
    1. 전체 경우의 수를 파악(8명이 있으므로, 8!)
    
    2. 경우의 수마다 조건에 맞는 안맞는지 확인
    
    3. 조건에 맞는 개수 반환
**/

class Solution {
    
    public int answer = 0;
    public boolean[] visited = new boolean[8];
    public int solution(int n, String[] data) {
        
        dfs(0,"",data);
        
        return answer;
    }
    
    public void dfs(int stage, String sequence,String[] data){
        
        if(stage == 8){
            if(check(sequence,data)){
                answer++;
            }
            return;
        }
        
        for(int i = 0; i < 8; i++){
            if(visited[i] == false){
                visited[i] = true;
                dfs(stage+1,sequence+String.valueOf(i),data);
                visited[i] = false;
            }
        }
    }
    
    public boolean check(String sequence,String[] data){
        
        String str = parsing(sequence);
        
        for(String s : data){
            
            char start = s.charAt(0);
            char end = s.charAt(2);
            char condition = s.charAt(3);
            int num = s.charAt(4) - '0';
            
            int first = str.indexOf(start);
            int second = str.indexOf(end);
            
            int distance = Math.abs(first - second)-1;
            
            if(condition == '='){
                if(distance != num) return false;
            }else if(condition == '<'){
                if(distance >= num) return false;
            }else{
                if(distance <= num) return false;
            }
        }
        
        return true;
    }
    
    public String parsing(String sequence){
        
        sequence = sequence.replace("0","A")
            .replace("1","C")
            .replace("2","F")
            .replace("3","J")
            .replace("4","M")
            .replace("5","N")
            .replace("6","R")
            .replace("7","T");
        
        return sequence;
    }
}