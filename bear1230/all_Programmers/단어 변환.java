import java.util.*;
import java.io.*;

class Solution {
    static class Node{
        String word;
        int edge;      
        public Node(String word, int edge){
            this.word = word;
            this.edge = edge;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        Queue<Node> que = new LinkedList<>();
        boolean[] visit = new boolean[words.length];
        
        que.add(new Node(begin, 0));
        
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.word.equals(target)){
                answer = cur.edge;
                break;
            }
            
            for(int i=0; i<words.length; i++){
                if(!visit[i] && isNext(cur.word, words[i])){
                    visit[i] = true;
                    que.add(new Node(words[i], cur.edge + 1));
                }
            }
        }
        
        return answer;
    }
    
    static boolean isNext(String cur, String next){
        int count = 0;
        for(int i=0; i<cur.length(); i++){
            if(cur.charAt(i) != next.charAt(i))
                count++;
            if(count > 1) 
                return false;
        }
        return true;
    }
    
}
