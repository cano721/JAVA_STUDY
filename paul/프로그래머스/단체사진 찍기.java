import java.util.*;
class Solution {
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static int answer =0;
    public int solution(int n, String[] data) {
        boolean[] vis = new boolean[8];
        answer = 0;
        dfs(0, vis, "", data);
        return answer;
    }
    static void dfs(int picked, boolean[] vis, String line, String[] data){
        if(picked == 8){
           
            answer += check(line, data);
            
            return;
        }
        
        for(int i =0; i< 8; i++){
            if(vis[i]) continue;
            vis[i] = true;
            dfs(picked+1, vis, line + friends[i], data);
            vis[i] = false;
        }
    }
    
    static int check(String line, String[] data ){
        
        //라인별 위치를 저장하기 위함.
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i< line.length(); i++){
            map.put(line.charAt(i), i);
        }
        
        for(int i=0; i< data.length; i++){
            int to = map.get(data[i].charAt(0));
            int from = map.get(data[i].charAt(2));
            char op = data[i].charAt(3);
            int k = data[i].charAt(4) - '0';
            if(!isPossible(to, from, op, k)) return 0;
        }
        
        return 1;
    }
    
    static boolean isPossible(int to, int from, char op, int k){
        int gap = Math.abs(from - to)-1;
        switch(op){
            case '=':
                if(gap == k) return true;
                break;
            case '>':
                if(gap > k) return true;
                break;
            case '<':
                if(gap < k) return true;
                break;
        }
        return false;
    }
}