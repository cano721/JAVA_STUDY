package cindya.bj1094_막대기;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt(), res = 64;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(res); // 처음 가지고 있는 막대를 pq에 삽입

        while (res != x){ // 가진 막대의 합이 x이면 루프 중지
            int a = pq.poll() >> 1; // 가장 작은 막대를 반으로 자름
            if(res - a >= x){ // 총 막대의 합에서 자른 쪽을 뺀 것이 x와 같거나 x보다 크면
                res -= a; // 반쪽을 버리고
                pq.offer(a); // 반은 다시 pq에 삽입
            }
            else{ // 반쪽을 뺀 것이 x보다 작으면 자른 막대를 모두 pq에 삽입
                pq.offer(a);
                pq.offer(a);
            }
        }
        System.out.println(pq.size()); // pq 사이즈 == 붙인 막대 개수
        scanner.close();
    }
}
