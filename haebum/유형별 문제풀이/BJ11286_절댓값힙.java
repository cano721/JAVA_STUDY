import java.util.*;
import java.io.*;

public class BJ11286_절댓값힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        // 우선순위 큐 절대값 오름차순으로 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 절대값 기준 정렬
                if(Math.abs(o1) < Math.abs(o2)){
                    return -1;
                }else if(Math.abs(o1) > Math.abs(o2)){
                    return 1;
                // 절대값이 동일할땐 실제로 적은게 왼쪽으로 정렬(오름차순)
                }else{
                    if (o1 < o2){
                        return -1;
                    }else{
                        return 1;
                    }
                }
            }   
        });

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());

            // 0이 입력되었을때
            if(num == 0){
                // 큐가 비워져있다면 0 출력
                if(pq.isEmpty()){
                    bw.write(0 + "\n");
                // 아니면 최대 값 출력
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