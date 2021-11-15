package baekjoon.silverⅠ;


/*

센티는 마법 도구들을 지니고 여행을 떠나는 것이 취미인 악당이다.
거인의 나라에 도착한 센티는 자신보다 키가 크거나 같은 거인들이 있다는 사실이 마음에 들지 않았다.
센티가 꺼내 들은 마법 도구는 바로 마법의 뿅망치로, 이 뿅망치에 맞은 사람의 키가 ⌊ 뿅망치에 맞은 사람의 키 / 2 ⌋로 변하는 마법 도구이다. 
단, 키가 1인 경우 더 줄어들 수가 없어 뿅망치의 영향을 받지 않는다.
하지만 마법의 뿅망치는 횟수 제한이 있다. 그래서 센티는 마법의 뿅망치를 효율적으로 사용하기 위한 전략을 수립했다. 바로 매번 가장 키가 큰 거인 가운데 하나를 때리는 것이다.

과연 센티가 수립한 전략에 맞게 마법의 뿅망치를 이용한다면 거인의 나라의 모든 거인이 센티보다 키가 작도록 할 수 있을까?

입력
첫 번째 줄에는 센티를 제외한 거인의 나라의 인구수 N (1 ≤ N ≤ 105)과 센티의 키를 나타내는 정수 Hcenti (1 ≤ Hcenti ≤ 2 × 109), 마법의 뿅망치의 횟수 제한 T (1 ≤ T ≤ 105)가 빈칸을 사이에 두고 주어진다. 
두 번째 줄부터 N개의 줄에 각 거인의 키를 나타내는 정수 H (1 ≤ H ≤ 2 × 109)가 주어진다.

출력
마법의 뿅망치를 센티의 전략대로 이용하여 거인의 나라의 모든 거인이 센티보다 키가 작도록 할 수 있는 경우, 
첫 번째 줄에 YES를 출력하고, 두 번째 줄에 마법의 뿅망치를 최소로 사용한 횟수를 출력한다.
마법의 뿅망치를 센티의 전략대로 남은 횟수 전부 이용하고도 거인의 나라에서 센티보다 키가 크거나 같은 거인이 있는 경우, 
첫 번째 줄에 NO를 출력하고, 두 번째 줄에 마법의 뿅망치 사용 이후 거인의 나라에서 키가 가장 큰 거인의 키를 출력한다.

1 10 1
20

NO
10

2 10 3
16
32

YES
3

2 10 3
127
8

NO
15

1 1 100000
1

NO
1


*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Boj19638_센티와마법의뿅망치 {

    // paul님 코드 참조함. 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 내림차순 우선순위 큐 (높은 숫자부터 정렬)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken()); // 거인의 인구 수
        int hcenti = Integer.parseInt(st.nextToken()); // 센티의 키 
        int t = Integer.parseInt(st.nextToken()); // 뿅망치 사용 가능 횟수 
        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        int cnt = 0; // 뿅망치 사용한 횟수 
        while (t-- > 0) { // paul님 코드 참조 
            if (pq.peek() < hcenti || pq.peek() == 1) break; // 큐안의 첫 값이 센티보다 키가 작거나 1이면 뿅망치 사용 불가로 반복문 탈출 
            cnt++;
            int tmpH = pq.poll();
            tmpH /= 2;  // 뿅망치에 맞은 사람의 키 / 2
            pq.add(tmpH);
        }
        // 큐에 첫 값이 작은 경우 YES
        if (hcenti > pq.peek()) sb.append("YES\n").append(cnt);
        else sb.append("NO\n").append(pq.peek());
        System.out.println(sb.toString());
    }
}
