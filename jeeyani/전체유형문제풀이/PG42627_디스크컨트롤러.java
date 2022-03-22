package 전체유형문제풀이;

import java.util.*;

/*
[우선순위 큐]

1. 요청되는 시점을 오름차순으로 정렬
2. 작업의 소요시간이 짧은 것부터 시작하기

*/

public class PG42627_디스크컨트롤러 {

	public static void main(String[] args) {

		int[][] jobs = { { 0, 3 }, { 2, 6 }, { 1, 9 } };

		int result = solution(jobs);

		System.out.println(result);

	}

	private static int solution(int[][] jobs) {
		int answer = 0;

		//1.요청되는 시점을 오름차순으로 정렬
		Arrays.sort(jobs, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		answer = getAvgTime(jobs);

		return answer;
	}

	private static int getAvgTime(int[][] jobs) {
		int end = 0; //작업종료시간
        int diskCnt = 0;
        int sum =0;
        int idx = 0;
        
        //2.작업의 소요시간이 짧은 것부터 시작하기
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1]-o2[1];
            }
        });
    
        
        while(diskCnt < jobs.length){
            
            //한 작업이 완료되는 시점 전까지, 모든 작업을 담기
            while(idx < jobs.length && jobs[idx][0] <= end){
                pq.offer(jobs[idx++]);
            }
            
            //작업완료 이후에 다시 요청 들어옴
            if(pq.isEmpty()){
                end = jobs[idx][0];
            }
            else{
                int[] job = pq.poll();
                sum += job[1] + end-job[0]; //작업종료시간 + (이전 작업종료시간-대기위치 = 대기시간)
                end += job[1]; //해당 작업의 종료시간
                diskCnt++;
            }
        }
        
        return (int)Math.floor(sum/jobs.length);
	}

}