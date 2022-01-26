package BOJ.Day3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 수가 있다.
 * 이 수들은 중간에 변경될 수 있다.
 * 이때, 중간 부분의 곱을 구하는 문제
 */
public class Main {
    static int s, mod = 1000000007;
    static long[] arr;

    /**
     * 세그먼트 트리를 사용하여 중간 곱을 구한다.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        s = 1;
        while (s < n)
            s *= 2;
        arr = new long[s * 2];
        for (int i = 0; i < n; i++)
            arr[s + i] = Long.parseLong(br.readLine());
        for (int i = s + n; i < arr.length; i++)
            arr[i] = 1;
        for (int i = s - 1; i > 0; i--)
            arr[i] = (arr[i * 2] * arr[i * 2 + 1]) % mod;
        int testCase = m + k;
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if (command == 1)
                swap(a, b);
            else
                answer.append(query(1, s, 1, a, (int) b)).append('\n');
        }
        System.out.print(answer);
    }

    public static void swap(int target, long num) {
        int cur = s + target - 1;
        arr[cur] = num;
        while ((cur /= 2) > 0)
            arr[cur] = (arr[cur * 2] * arr[cur * 2 + 1]) % mod;
    }

    public static long query(int left, int right, int node, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft)
            return 1;
        else if (queryLeft <= left && right <= queryRight)
            return arr[node];
        else {
            if (left != right) {
                int mid = (left + right) / 2;
                long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
                long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
                return (leftResult * rightResult) % mod;
            } else
                return arr[node];
        }
    }
}