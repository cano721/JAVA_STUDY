class Solution {
    int[] left;
    int[] right;
    public int solution(int[] a) {
        int len = a.length;
        int cnt = 0;
        left = new int[len];
        right = new int[len];
        int lsmall = a[0];
        int rsmall = a[len-1];
        for(int i=0; i<len; i++){
            if(a[i]<=lsmall) lsmall = a[i];
            if(a[len-i-1]<=rsmall) rsmall = a[len-i-1];
            left[i] = lsmall;
            right[len-i-1] = rsmall;
        }
        
        for(int i= 0; i< len; i++){
            if(a[i]<=left[i]||a[i]<=right[i]) cnt++;
        }
        
        
        int answer = cnt;
        return answer;
    }
    
}