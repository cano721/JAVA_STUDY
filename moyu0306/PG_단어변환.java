class Solution {
    char[][] chars;
    boolean[] isVisited;
    int totCnt = 51;
    public int solution(String begin, String target, String[] words) {
        int len = words.length;
        int lenw = words[0].length();
        isVisited= new boolean[len];
        chars = new char[len][lenw];
        char[] beginChars = begin.toCharArray();
        char[] endChars = target.toCharArray();
        for(int i=0;i<len ;i++){
            chars[i] = words[i].toCharArray();
        }
        
        
        convertString(beginChars,0,endChars);

        return (totCnt == 51) ? 0 :totCnt;
    }

    public void convertString(char[] current, int cnt, char[] target){
        if(compareString(current,target) == 0) {
            totCnt = Integer.min(cnt,totCnt);
            return;
        }
        for(int i=0; i<chars.length; i++){
            if(isVisited[i] || compareString(current,chars[i])>=2) continue;
            isVisited[i] = true;
            convertString(chars[i],cnt+1,target);
            isVisited[i] = false;
        }
        return;
    }
    
    public int compareString(char[] c1, char[] c2){
        int cnt =0;
        for(int i=0; i< c1.length; i++){
            if(c1[i]!= c2[i]) cnt++;
        }
        return cnt;
    }
}