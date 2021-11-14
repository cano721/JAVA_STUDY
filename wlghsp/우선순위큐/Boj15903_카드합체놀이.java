package baekjoon.silverⅡ;

/*


3 1
3 2 6

16

4 2
4 2 3 1

19

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj15903_카드합체놀이 {

    // 1. 일반 배열 및 Arrays.sort로 풀이
    // public static void main(String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // StringTokenizer st = new StringTokenizer(br.readLine());
    // int n = Integer.parseInt(st.nextToken()); // 카드의 갯수
    // int m = Integer.parseInt(st.nextToken()); // 카드 합체 횟수
    // long sum = 0; // 최종 합계
    // long[] arr = new long[n];
    // st = new StringTokenizer(br.readLine());
    // for (int i = 0; i < n; i++)
    // arr[i] = Long.parseLong(st.nextToken());
    // for (int i = 0; i < m; i++) {
    // Arrays.sort(arr); // 아래코드에서 값 변경이 일어나므로 매번 오름차순 정렬 필요
    // arr[0] = arr[1] = arr[0] + arr[1];
    // }
    // for (long i : arr)
    // sum += i;
    // System.out.println(sum);
    // }

    // 2.우선순위큐 풀이. 위에보다 속도 훨씬 빠름
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] sarr = br.readLine().split(" ");

        int n = Integer.parseInt(sarr[0]);
        int m = Integer.parseInt(sarr[1]);

        PriorityQueue<Long> pq = new PriorityQueue<>();

        sarr = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            pq.offer(Long.parseLong(sarr[i]));
        }

        int cnt = m;

        while (cnt > 0) {
            long a = pq.poll();
            long b = pq.poll();
            long sum = a + b;
            pq.offer(sum);
            pq.offer(sum); // 우선순위큐에서 알아서 오름차순 정렬 됨.
            cnt--;
        }
        long res = 0;
        while (!pq.isEmpty()) {
            res += pq.poll();
        }
        System.out.println(res);
    }
}
