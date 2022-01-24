import java.util.*;
import java.io.*;

/* 
    맵에 있는 숫자를 다 우선순위큐에 넣고 (1500*1500)
    n번만큼 빼내기
*/

public class BJ2075_N번째큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 우선순위 큐 내림차순으로 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o2-o1);

        // 큐에 집어 넣기
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                pq.offer(num);
            }
        }

        // n번 만큼 빼내기
        int cnt = 0;
        int answer = 0;
        while(! pq.isEmpty()){
            cnt++;
            answer = pq.poll();
            // n번째일때 종료
            if(cnt == n) break;
        }
        System.out.println(answer);
    }    
}

