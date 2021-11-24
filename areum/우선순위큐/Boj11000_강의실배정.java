package 우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj11000_강의실배정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] times = new int[N][2];
        for(int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            times[i][0] = Integer.parseInt(str[0]);
            times[i][1] = Integer.parseInt(str[1]);
        }

        // 시간순 정렬
        Arrays.sort(times, (o1, o2) -> {
            // 시작시간이 같을 경우 빨리 끝나는 순서로 정렬
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        // 강의실 개수 구하기
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i=0; i<N; i++) {
            int start = times[i][0];
            int end = times[i][1];

            // 시작시간이 일찍 끝나는 시간보다 같거나 크다면 기존 강의실 이용
            if (!queue.isEmpty() && queue.peek() <= start) {
                queue.poll();
            }
            queue.offer(end);
        }

        System.out.println(queue.size());
    }
}
