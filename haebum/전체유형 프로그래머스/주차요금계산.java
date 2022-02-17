/**
    1. 주차시간 구하기
    1-1) 최대 23:59분(또는 출차시간) - 입차시간 = 주차시간
    1-2) 인트형으로 파싱
    1-3) 입차 시에 해시 저장. 출차 시에 해시 삭제하면서 계산
    1-4) 다 돌았는데 해시에 남아있는건 23:59분으로 계산
    
    2. 요금계산
    2-1) 주차시간이 기본시간 내면 기본요금
    2-2) 기본시간초과면 기본요금 + 올림(초과시간/단위시간)*단위요금
    
    3. 정답 배열 반환
    
**/

import java.util.*;

class Solution {
    
    public Map<String,Integer> temp = new HashMap<>();
    public Map<String,Integer> parkTime;
    
    public int[] solution(int[] fees, String[] records) {
        
        // 해시맵 정렬
        Comparator<String> comparator = (o1,o2) -> o1.compareTo(o2);
        parkTime = new TreeMap<>(comparator);
        
        for(int i = 0; i < records.length; i++){
            String[] data = records[i].split(" ");
            // 출차등록
            if(data[2].equals("IN")){
                int time = parsingTime(data[0]);
                temp.put(data[1],time);
            // 시간등록
            }else{
                int end = parsingTime(data[0]);
                int start = temp.get(data[1]);
                parkTime.put(data[1],parkTime.getOrDefault(data[1],0)+end-start);
                temp.remove(data[1]);
            }
        }
        // 입차시간만 있는것 처리
        for(String car : temp.keySet()){
            int start = temp.get(car);
            int end = parsingTime("23:59");
            parkTime.put(car,parkTime.getOrDefault(car,0)+end-start);
        }
        
        
        int[] answer = new int[parkTime.size()];
        int idx = 0;
        //주차요금 계산
        for(String car : parkTime.keySet()){
            int pay = countFee(fees,parkTime.get(car));
            answer[idx++] = pay;
        }
        return answer;
    }
    
    public int countFee(int[] fees, int time){
        if(time <= fees[0]){
            return fees[1];
        }else{
            time -= fees[0];
            return fees[1] +((int)Math.ceil((double)time/fees[2])*fees[3]);
        }
    }
    
    public int parsingTime(String times){
        String[] time = times.split(":");
        return Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]); 
    }
}