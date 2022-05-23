import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int[][] reservation;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]>pq1 = new PriorityQueue<>((a,b)->{return -a[1]+b[1];});
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        reservation = new int[N][2];
        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            pq1.offer(new int[]{Integer.parseInt(input[0]),Integer.parseInt(input[1])});
        }
        int day = 0;
        while(!pq1.isEmpty()){
            if(pq2.isEmpty()){
                day = pq1.peek()[1];
            }
            if(pq1.peek()[1] == day) pq2.offer(pq1.poll()[0]);
            else{
                sum+=pq2.poll();
                day--;
            }
        }
        while(day>0 &&!pq2.isEmpty()){
            sum+=pq2.poll();
            day--;
        }
        System.out.println(sum);
    }
}