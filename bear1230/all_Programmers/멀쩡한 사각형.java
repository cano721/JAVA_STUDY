class Solution {
    public long solution(int w, int h) {
        long answer = 0;
        for(int i=0; i<w; i++) {
            answer += (long)i*h/w ;
        }
        
        return answer * 2;
    }
}
