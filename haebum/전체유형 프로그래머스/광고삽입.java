/**
    1. 초 단위로 누적합 진행
    1-1) 1시간 = 3600초
        최대 100시간 = 360,000 초
    1-2) 초단위 배열을 생성 후 누적합으로 각 초에 몇명이 봤는지 계산
    
    2. 초단위 배열에서 각 초 별 누적 재생시간으로 배열 변경
    2-1) 누적합 사용
    
    4. 누적합으로 해당 범위 계산 최대값 중 가장 빠른 시간 반환
    
    *주의*
    
    시청기간은 시작 이상 종료 미만...
**/


class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
         int len = timeParsing(play_time);
        
        long[] sArr = new long[len+2];
        
        for(String log : logs){
            String[] strs = log.split("-");
            
            int start = timeParsing(strs[0]);
            int end = timeParsing(strs[1]);
            
            sArr[start] += 1;
            sArr[end] -= 1;
        }
        
        // 각 시간대별 시청사람 수
        for(int i = 1; i <= len; i++){
            sArr[i] += sArr[i-1];
        }
        
        // 각 시간대별 누적 시청시간
        for(int i = 1; i <= len; i++){
            sArr[i] += sArr[i-1];
        }
        
        int adv = timeParsing(adv_time);
        
        //미만의 누적합값을 초기값 설정
        long max = sArr[adv-1];
        int cur = 0;
        
        // 1초부터 확인
        for(int i = 0; i+adv <= len; i++){
            long sum = sArr[i+adv]-sArr[i];
            
            if(sum > max){
                max = sum;
                cur = i+1;
            }
        }
        
        System.out.println(max);
        String answer = stringParsing(cur);
        
        return answer;
    }
    
    public String stringParsing(int max){
        
        String result = "";
        
        int h = max/3600;
        result += String.format("%02d:",h);
        
        max%= 3600;
        int m = max/60;
        result += String.format("%02d:",m);
        
        max %= 60;
        int s = max;
        result += String.format("%02d",s);
        
        return result;
    }
    
    public int timeParsing(String time){
        int result = 0;
        String[] times = time.split(":");
        
        result += Integer.parseInt(times[0])*3600;
        result += Integer.parseInt(times[1])*60;
        result += Integer.parseInt(times[2]);
        
        return result;
    }
}