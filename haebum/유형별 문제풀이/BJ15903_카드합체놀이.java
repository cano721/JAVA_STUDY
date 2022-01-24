import java.util.*;
import java.io.*;
import java.math.BigInteger;

/*
    두개의 카드를 골라서 더한 값을 두 카드에 덮어쓴다.
    이러한 과정을 m번 했을때 총 카드의 합이 놀이의 점수!
    최저점수를 구해야하므로 최저 숫자 2개의 카드를 더해서 덮어씌워야함.

    우선순위큐에서 2개의 카드를 꺼내서 더한 후 그 값으로 2번 넣음.

    최종 m번 했을때 전체 합계 출력

    자연수 최대 백만 원소 천개 만오천번 합치기 가능
    21억 넘을 가능성 있으므로 bigInteger로 설정
*/
public class BJ15903_카드합체놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        // n과 m 설정
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // 우선순위 큐 생성
        PriorityQueue<BigInteger> pq = new PriorityQueue<>();
        // 카드 숫자 큐에 담기
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st2.nextToken());
            BigInteger bigNum = BigInteger.valueOf(num);
            pq.offer(bigNum);
        }
        // m번 카드 골라서 합체하여 다시 넣기
        for(int i = 0; i < m; i++){
            BigInteger card1 = pq.poll();
            BigInteger card2 = pq.poll();
            BigInteger sumCard = card1.add(card2);
            pq.offer(sumCard);
            pq.offer(sumCard);
        }
        // 전체 카드 합계 구하기
        BigInteger sum = new BigInteger("0");
        for(int i = 0; i < n; i++){
            sum = sum.add(pq.poll());
        }
        bw.write(sum + "\n");

        // 다 출력 후 종료
        bw.flush();
        bw.close();
    }    
}
