/**
    1. queue를 사용하여 변경가능한 단어 변경
    
    2. 해당 단어를 썻는지 안썻는지 체크
    
    3. 변경할때 변경회수 증가
    
    4. target과 동일 시에 변경횟수 반환
    
**/

import java.util.*;

class Solution {
    public class Word{
        String word;
        int cnt;
        public Word(String word, int cnt){
            this.word = word;
            this.cnt = cnt;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        Queue<Word> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        
        q.offer(new Word(begin,0));
        
        while(!q.isEmpty()){
            Word cur = q.poll();
            
            // target과 동일하면 현재까지 변경횟수 반환
            if(cur.word.equals(target)){
                return cur.cnt;
            }
            
            for(int i = 0; i < words.length; i++){
                String word = words[i];
                // 이미 사용한 단어이면 패스
                if(visited[i] == true){
                    continue;
                }
                
                // 같은 글자가 몇개인지 체크
                int pos = 0;
                for(int j = 0; j < word.length(); j++){
                    if(cur.word.charAt(j) == word.charAt(j)){
                        pos++;
                    }
                }
                
                // 한글자만 변경된 단어
                if(pos == word.length() -1){
                    visited[i] = true; // 사용한 단어 체크
                    q.offer(new Word(word,cur.cnt+1));
                }
            }
        }
        
        // 다돌았는데 탈출 못했으면 0
        return 0;
    }
}