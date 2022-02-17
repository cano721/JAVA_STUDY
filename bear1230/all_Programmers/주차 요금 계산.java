import java.util.*;

class Solution {
    public  int[] solution(int[] fees, String[] records) {

        int day = toTime("23:59");

        Map<String, Integer> parking = new HashMap<>();
        Map<String, Integer> times = new HashMap<>();
        List<String> car = new ArrayList<>();

        int b_time = fees[0];
        int b_fee = fees[1];
        int p_time = fees[2];
        int p_fee = fees[3];
        
        for(String record : records){
            String [] rc = record.split(" ");
            int time = toTime(rc[0]);
            String carNum = rc[1];

            if(!car.contains(carNum)){
                car.add(carNum);
                times.put(carNum, 0);
            }
            
            if(parking.containsKey(carNum)){
                // 주차 o
                int usedTime = times.get(carNum) + (time - parking.get(carNum));
                times.put(carNum, usedTime);
                parking.remove(carNum);
            }else{
                // 주차 x
                parking.put(carNum, time);
            }
        }

        int[] answer = new int[car.size()];

        //차 번호순 정렬
        Collections.sort(car);

        for(int i = 0; i < car.size(); i++){
            answer[i] = b_fee;
            String ParkedCarNum = car.get(i);

            // 주차한 시간 중 기본 요금 시간 제외
            int time = times.get(ParkedCarNum) - b_time;

            if(parking.containsKey(ParkedCarNum)){
                time += (day - parking.get(ParkedCarNum));
            }

            if(time > 0){
                answer[i] += (Math.ceil(time / (p_time * 1.0)) * p_fee);
            }
        }
        return answer;
    }

    // 시간변환
    public  int toTime(String time){
        String [] temp = time.split(":");
        int hour = Integer.parseInt( temp[0] )  * 60;
        int min = Integer.parseInt( temp[1]);
        return hour + min;
    }
}
