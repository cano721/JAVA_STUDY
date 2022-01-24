import java.util.*;
import java.io.*;

/*
    거인들의 키를 우선순위큐에 넣고
    가장 큰 키의 거인을 뽑아서 뿅망치로 때린 후 넣기
    현재 뽑은 거인의 키가 센티의 키보다 작다면
    YES와 사용횟수 출력
    횟수제한만큼 사용했으나 가장 큰 거인이 센티의 키보다 크다면
    NO와 가장 큰 거인의 키 출력
*/

public class BJ19638_센티와마법의뿅망치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        // 내림차순 우선순위큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o2-o1);
        for(int i = 0; i < n; i++){
            int key = Integer.parseInt(br.readLine());
            pq.offer(key);
        }
        // 모든 거인이 센티보다 작냐 안작냐 체크변수
        Boolean flag = false;
        // 뿅망치 사용횟수
        int cnt = 0;
        //가장 큰 거인의 키
        int giant;
        while(true){
            giant = pq.poll();
            // 가장 키 큰 거인이 센티보다 작으면 종료
            if(giant < h){
                flag = true;
                break;
            }
            // 뿅망치 횟수를 다 썼거나 키가 1이면 종료
            if(cnt == t || giant == 1) break;
            // 뿅망치사용
            cnt++;
            pq.offer(giant/2);
        }
        // 센티보다 작을때
        if(flag) bw.write("YES\n" +cnt);
        // 센티보다 크거나 같을때
        else bw.write("NO\n" + giant);

        
        // 다 출력 후 종료
        bw.flush();
        bw.close();
    }    
}
