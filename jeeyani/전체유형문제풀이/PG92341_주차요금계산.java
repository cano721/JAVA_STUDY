package 전체유형문제풀이;

import java.util.*;


/*
 * 
 * [구현]
 *  
 *  1. set을 이용하여 입출차 기록이 있는 차량 기록
 *  2. 각 차 마다의 입출력 기록 정보 확인하기
 *  2-1. [입차-출차] 정보 모두 존재: 출잡-입차 시간 계산
 * 	2-2. [입차]정보만 존재: 출차 정보 23:59로 시간 계산
 * 
 * 
 * */


public class PG92341_주차요금계산 {

	public static void main(String[] args) {

		int[] fees = {180,5000,10,600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

		int[] result = solution(fees, records);
		
		for(int ans : result) {
			System.out.print(ans+" ");
		}
		

	}

	private static int[] solution(int[] fees, String[] records) {
		//입출차 기록이 있는 차 기록하기
        HashSet<String> setCar = new HashSet<>();
        for(String record : records){
            String[] car = record.split(" ");
            setCar.add(car[1]);
        }
              
        int[] answer = new int[setCar.size()];
        
        List<String> carList = new ArrayList<>();
        carList.addAll(setCar);
        //차량번호가 작은 자동차부터 정렬
        Collections.sort(carList);
       
        //해당 기록이 있는 차들마다 주차시간 계산하기
        for(int i = 0; i<carList.size(); i++){
            
            String carNum = carList.get(i);
            int inTime = 0;
            int outTime = 0;
            int totalTime = 0;
            boolean inOutCHK = false;
            
            for(String record : records){
                
                String[] car = record.split(" ");

                //해당 차량의 입출차 시간 계산 하기
                if(carNum.equals(car[1])){
                    
                    if("IN".equals(car[2])){
                        inOutCHK = true;
                        inTime = toMin(car[0]);
                    }
                    
                    else{
                        inOutCHK = false;
                        outTime = toMin(car[0]);
                        totalTime += outTime-inTime;
                    }   
                }  
            }
            
            //출차기록이 없는 경우
            if(inOutCHK){
                outTime = 1439; //23:59
                totalTime += outTime-inTime;
            }
            int calFee = getFee(fees,totalTime);
            //list.add(new parkFee(carNum,calFee));
            answer[i] = calFee;
        }

        return answer;
	}
	
	private static int getFee(int[] fees, int totalTime) {
		int feeRes = fees[1];
        
        //기본시간을 넘길 경우
        if(totalTime > fees[0]){
            feeRes += Math.ceil((double)(totalTime-fees[0])/fees[2]) * fees[3]; //** 강제형변환
        }
        
        return feeRes;
	}

	//문자 -> 분으로 바꾸기
	private static int toMin(String time) {
		String[] tt = time.split(":");
        int hh = Integer.parseInt(tt[0])*60;
        int mm = Integer.parseInt(tt[1]);
        
        return hh+mm; 
	}

	
}