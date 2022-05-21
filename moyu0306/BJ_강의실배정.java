import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int count = 0;
    static int current = 0;
    static PriorityQueue<int[]> unsettledClass;
    static PriorityQueue<int[]> settledClass;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        unsettledClass = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });
        settledClass = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            unsettledClass.offer(new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])});
        }

        while (!unsettledClass.isEmpty()) {
            if (settledClass.isEmpty()) {
                current++;
            } else {
                int[] finishedClass = settledClass.peek();
                int[] plannedClass = unsettledClass.peek();

                if (finishedClass[1] > plannedClass[0]) {
                    current++;
                }else{
                    settledClass.poll();
                }
            }
            settledClass.offer(unsettledClass.poll());

            count = Integer.max(count, current);
        }
        System.out.println(count);
    }
}