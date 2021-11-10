import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Boj11286_절댓값힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int a = Math.abs(o1);
            int b = Math.abs(o2);

            if(a == b) // 절댓값이 같다면 더 작은 수 출력
                return o1 > o2 ? 1 : -1;

            return a - b;
        });

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0) {
                if(queue.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(queue.poll());
                }
            } else {
                queue.add(num);
            }
        }
    }
}
