package cindya.bj13975_파일합치기_3;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int k;
        long sum;
        PriorityQueue<Long> files;

        // t번 루프
        for(int i = 0; i < t; i++){
            k = Integer.parseInt(br.readLine());
            sum = 0;
            // 입력을 우선순위 큐에 저장
            files = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).boxed().collect(Collectors.toCollection(PriorityQueue::new));

            // 파일 개수가 1이 될 때까지
            while(files.size() > 1){
                long v = files.poll() + files.poll(); // 가장 작은 수 두개를 더해서
                sum += v; // 더하고
                files.add(v); // 큐에 삽입
            }
            bw.write(sum + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
