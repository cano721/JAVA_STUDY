package cindya.bj19638_센티와마법의뿅망치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), h = Integer.parseInt(st.nextToken(" ")), t = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> hpq = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위큐 내림차순으로 생성
        int cnt = 0;

        // 거인들 키 우선순위 큐에 삽입
        for(int i = 0; i < n; i++)
            hpq.offer(Integer.parseInt(br.readLine()));
        br.close();

        // 뿅망치 사용 횟수가 제한을 넘거나, 가장 큰 거인의 키가 센티의 키보다 작거나 1이면 루프 멈춤
        while (cnt < t && hpq.peek() >= h && hpq.peek() > 1){
            cnt++; // 사용한 횟수 증가
            hpq.offer(hpq.poll() / 2); // 가장 큰 값을 2로 나눠서 삽입
        }

        if(hpq.peek() < h) // 센티가 성공했을 때
            System.out.println("YES\n" + cnt);
        else // 실패했을 때
            System.out.println("NO\n" + hpq.peek());
    }
}