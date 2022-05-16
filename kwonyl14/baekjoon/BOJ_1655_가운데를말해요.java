package day220516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * maxPQ : 5 1 2 -99
 * minPQ : 5 7 10
 * 1 크기가 같으니 maxPQ
 * 5 maxPQ보다 큰데 maxPQ.size > minPQ.size -> minPQ에 저장
 * 2 maxPQ보다 큰데 maxPQ.size == minPQ.size -> minPQ에 넣고 하나 빼서 maxPQ저장
 * 10 maxPQ보다 큰데 maxPQ.size > minPQ.size -> minPQ에 저장
 * -99 maxPQ보다 작은데 maxPQ.size == minPQ.size -> maxPQ에 저장
 * 7 maxPQ보다 큰데 maxPQ.size > minPQ.size -> minPQ에 저장
 * 5 maxPQ보다 큰데 같음 -> minPQ에 저장
 * 5 maxPQ와 같은데 maxPQ.size > minPQ = minPQ에 저장
 */

public class BOJ_1655_가운데를말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        maxPQ.offer(Integer.parseInt(br.readLine()));
        StringBuilder sb = new StringBuilder();
        sb.append(maxPQ.peek()).append("\n");
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > maxPQ.peek()) { //minPQ에 넣어야함
                minPQ.offer(num);
            } else if (num <= maxPQ.peek()) { //maxPQ에 넣어야함
                maxPQ.offer(num);
            }
            if (maxPQ.size() - minPQ.size() > 1) {
                minPQ.offer(maxPQ.poll());
            } else if (minPQ.size() > maxPQ.size()) {
                maxPQ.offer(minPQ.poll());
            }
            sb.append(maxPQ.peek()).append("\n");
        }

        System.out.println(sb);

    }
}
