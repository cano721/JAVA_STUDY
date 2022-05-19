class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
       
    
    long start = 0;
    long end = (long)(1e9 * 4 *1e5);
    long answer = end;
    
    while( start <= end) {
        long mid = (long)Math.floor((start + end) / 2);
        long gold = 0;
        long silver = 0;
        long add = 0;
        
        for( int i =0; i < s.length; i++ ) {
            int now_g = g[i];
            int now_s = s[i];
            int now_w = w[i];
            int now_t = t[i];
            
            long move_cnt = (mid / (long)(now_t * 2));
            if(mid % (now_t * 2) >= t[i]) move_cnt++;

            gold += (now_g < move_cnt * now_w) ? now_g : move_cnt * now_w;
            silver += (now_s < move_cnt * now_w) ? now_s : move_cnt * now_w;
            add += (now_g + now_s < move_cnt * now_w) ? now_g + now_s : move_cnt * now_w;
        }
        
        
        if(gold >= a && silver >= b && add >= a + b) {
            end = mid - 1;
            answer = Math.min(mid, answer);
        }else {
            start = mid + 1;
        }
    }
    
    return answer;
}
    }