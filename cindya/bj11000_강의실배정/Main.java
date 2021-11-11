package cindya.bj11000_강의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> starts = new PriorityQueue<>(), ends = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        int max = 0, cnt = 0;

        // 시작 시간과 끝나는 시간을 각각 우선순위큐에 삽입
        for(int i = 0; i < n; i++){
            String[] time = br.readLine().split(" ");
            starts.offer(Integer.parseInt(time[0]));
            ends.offer(Integer.parseInt(time[1]));
        }

        int start = starts.poll();
        int end = ends.poll();
        // 시작시간 우선순위큐가 빌 때까지
        while(!starts.isEmpty()){
            if(start <= end){ // 시작 시간이 더 빠르면
                cnt++; // 강의실 개수를 늘리고
                start = starts.poll(); // start를 다음 시작 시간으로 바꾸고
                max = Math.max(max, cnt); // 이전의 max와 비교해 더 큰 값 선택
            }
            if(start >= end) { // 끝나는 시간이 더 빠르면
                cnt--; // 강의실 개수를 줄이고
                end = ends.poll(); // end를 다음 수업 끝나는 시간으로 변경
            }
        }
        System.out.println(max);
    }
}
