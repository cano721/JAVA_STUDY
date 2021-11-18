package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
8 30 4 30
7
9
7
30
2
7
9
25

5

*/

public class Boj15961_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가지수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // chk 배열에는 각 초밥 종류별 갯수를 확인.
        int[] chk = new int[d+1];
        chk[c]++;
        int kind = 1;

        for (int i = 0; i < k; i++) {
            // 0이면 먹은 적 없는 종류로 kind를 증가시키고, 후위연산자로 chk배열에서도 증가됨.
            if (chk[arr[i]]++ == 0) { // 같은 수가 들어오면 chk배열에서 증가는 하나 종류는 늘어나지 않음.
                kind++;
            }
        }
        int ans = kind;

        // 블로그 참고함. https://m.blog.naver.com/1ilsang/221505885296
        // 코드는 짧은데 이해하기 어려움...
        for (int i = k; i < n + k; i++) {
            if (--chk[arr[i-k]] == 0) kind--;
            if (chk[arr[i%n]]++ == 0) kind++; // 회전초밥이라서 n을 넘어서  0~k-1까지 오므로 i % n
            if (kind > ans) ans = kind; // 최댓값 갱신
        }

        System.out.println(ans);

    }
}
