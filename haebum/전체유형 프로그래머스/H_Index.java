/**
    1. 이분탐색으로 h 지정
    
    2. 지정된 h로 각 논문 체크
    
    3. h의 최대값 반환
**/

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        int start = 0;
        int end = 10_000;
        
        answer = binarySearch(start,end,citations);
        
        return answer;
    }
    
    public int binarySearch(int start, int end, int[] citations){
        
        int answer = 0;
        while(start <= end){
            int mid = (start+end)/2;
            int cnt = 0;
            int ncnt = 0;
            for(int v: citations){
                if(v >= mid){
                    cnt++;
                }else{
                    ncnt++;
                }
            }
            
            if(cnt >= mid && ncnt <= mid){
                answer = mid;
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        
        return answer;
    }
}