import java.util.*;
import java.io.*;
import java.lang.*;

public class BJ1927_최소힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        // 우선순위 큐 오름차순으로 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o1-o2);

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());

            // 0이 입력되었을때
            if(num == 0){
                // 큐가 비워져있다면 0 출력
                if(pq.isEmpty()){
                    bw.write(0 + "\n");
                // 아니면 작은 값 출력
                }else{
                    int cur = pq.poll();
                    bw.write(cur + "\n");
                }
            // 0 외의 숫자가 입력되면 큐에 저장
            }else{
                pq.add(num);
            }
        }
        // 다 출력 후 종료
        bw.flush();
        bw.close();
    }    
}


