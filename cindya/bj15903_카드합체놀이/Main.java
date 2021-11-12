package cindya.bj15903_카드합체놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        PriorityQueue<Long> pq = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).boxed().collect(Collectors.toCollection(PriorityQueue::new));
        long sum = 0;

        br.close();

        for(int i = 0; i < nm[1]; i++){
            long n1 = pq.poll(), n2 = pq.poll(); // 제일 작은 두 수를 빼고
            // 두 수를 더한 값을 두번 삽입 (각각 덮어쓰기 때문)
            pq.add(n1 + n2);
            pq.add(n1 + n2);
        }

        // 우선순위 큐의 모든 값을 더함
        while (!pq.isEmpty()){
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}
