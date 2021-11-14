import java.util.*;
import java.io.*;

/*
    소설이 여러개의 파일로 나누어져있고,
    이 여러 파일들을 2개씩 합쳐나가면서 비용이 발생할때
    최종 1개의 파일 만들때 최소비용 구하기

    제일 작은 값 두개를 더해가면 됨.
    우선순위 큐에 다 넣고 큐 사이즈가 2개이상일때
    2개를 꺼내고 합치기
    합치면서 발생한 비용을 큐에 다시 넣고
    별도의 변수에도 비용값을 더해두기
*/

public class BJ13975_파일합치기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            int num = Integer.parseInt(br.readLine());
            long answer = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            // 우선순위 큐에 원소담기
            while(st.hasMoreTokens()){
                long el = Long.parseLong(st.nextToken());
                pq.offer(el);
            }
            // 우선순위큐에 원소가 2개이상일때
            while(pq.size() > 1){
                long minNum1 = pq.poll();
                long minNum2 = pq.poll();
                long sumNum = minNum1+minNum2;
                pq.offer(sumNum);
                answer+= sumNum;
            }
            bw.write(answer + "\n");
        }
        // 다 출력 후 종료
        bw.flush();
        bw.close();
    }    
}


