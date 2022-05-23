import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N, T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        T = Integer.parseInt(input[1]);
        long sum = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->{return -a[1]+b[1];});
        for(int i=0; i<N; i++){
           String[] nums = br.readLine().split(" ");
           pq.offer(new int[]{Integer.parseInt(nums[0]),Integer.parseInt(nums[1])});
        }

        while(!pq.isEmpty()){
            int[] carrotInfo = pq.poll();
            sum+=((long)carrotInfo[0] + (long)carrotInfo[1] * (long)(--T));
        }
        System.out.println(sum);
    }

}