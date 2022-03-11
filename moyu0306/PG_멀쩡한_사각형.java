class Solution {
    public long solution(int w, int h) {
        
        long pre = h;
        long current;
        long cnt = 0;
        for(long i=1; i<w+1; i++){
            current = - ((i * h) /w ) +h;
            if( (i*h) % w == 0) cnt+= (pre -current);
            else cnt+=(pre-current +1);
            pre = current;
        }
        
        long answer = (long)w*(long)h - cnt; // 자료형 조심.
        return answer;
    }
}