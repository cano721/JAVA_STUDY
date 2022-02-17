package programmers;

import java.util.PriorityQueue;

class CarHistory implements Comparable<CarHistory> {
	int time;
	String carNum;
	String inOut;
	
	public CarHistory(int time, String carNum, String inOut) {
		this.time = time;
		this.carNum = carNum;
		this.inOut = inOut;
	}
	@Override
	public int compareTo(CarHistory o) {
		if(Integer.parseInt(this.carNum) == Integer.parseInt(o.carNum)) {
			return this.time - o.time;	//오름차순
		}

		return Integer.parseInt(this.carNum) - Integer.parseInt(o.carNum);	//오름차순
	}
}
public class d220217_주차요금계산 {

	public static void main(String[] args) {
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		int[] answer = solution(fees, records);//[14600, 34400, 5000]
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

	private static int[] solution(int[] fees, String[] records) {
		PriorityQueue<CarHistory> pq = new PriorityQueue<CarHistory>();
		int[] timeSum = new int[records.length];
		int lastTime = 23*60 + 59;
		int carCnt = 0;
		for(String record : records) {
			String[] rec = record.split(" ");
			int hour = Integer.parseInt(rec[0].split(":")[0]);
			int min = Integer.parseInt(rec[0].split(":")[1]);
			int time = hour * 60 + min;
			pq.add(new CarHistory(time, rec[1], rec[2]));
		}
		String carNum = pq.peek().carNum;
		String inOut = pq.peek().inOut;
		int inTime = 0;
		int totTime = 0;
		
		while(!pq.isEmpty()) {
			CarHistory carHistory = pq.poll();
			if(!carNum.equals(carHistory.carNum)) {	//차번호 달라질 때 전 차 금액 계산하기
				carNum = carHistory.carNum;
				if("IN".equals(inOut)) {	//마지막이 IN 이면 23:59
					totTime += lastTime - inTime;
				}
				timeSum[carCnt++] = totTime;
				totTime = 0;
			}

			if("IN".equals(carHistory.inOut)) {		//차 들어올 때
				inTime = carHistory.time;
				inOut = carHistory.inOut;
			}else if("OUT".equals(carHistory.inOut)) {
				totTime += carHistory.time - inTime;
				inOut = carHistory.inOut;
			}
		}//while end
		if("IN".equals(inOut)) {	//마지막이 IN 이면 OUT은 23:59
			totTime += lastTime - inTime;
		}
		timeSum[carCnt++] = totTime;
		
		//요금 계산
		int[] answer = new int[carCnt];
		for (int i = 0; i < carCnt; i++) {
			if(timeSum[i] <= fees[0]) {
				answer[i] = fees[1];
			}else {
				answer[i] = fees[1] + (int)Math.ceil((double)(timeSum[i]-fees[0])/fees[2]) * fees[3];
			}
		}
		return answer;
	}

}
