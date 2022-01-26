package BOJ.Day3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 수가 주어진다.
 * 이 N개의 수는 중간에 숫자가 변경될 수 있다.
 * 이러한 상태에서 중간의 어떤 부분의 합을 구하는 문제
 */
public class Main {
    static int s;
    static long[] arr;

    /**
     * 세그먼트 트리(인덱스 트리)를 사용하여 푸는 문제
     * 입력데이터 N이 주어지면 N보다 큰 2^S승을 찾는다.
     * 이후 N개의 데이터를 S부터 S+N에 넣는다.(leaf node)
     * 이제 S-1부터 1까지 idx*2(left node) + idx*2+1(right node)의 결과를 저장한다. (부분 합)
     * x번째 데이터가 변경되면 x와 연관이 있는 부모 노드들의 값을 변경 시켜 준다.
     * x부터 y까지의 합을 구한다고 하면 부모 노드부터 차례대로 x부터 y사이의 값들을 더해준다.
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
        for (int i = s - 1; i > 0; i--)
            arr[i] = arr[i * 2] + arr[i * 2 + 1];
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
            arr[cur] = arr[cur * 2] + arr[cur * 2 + 1];
    }

    public static long query(int left, int right, int node, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft)
            return 0;
        else if (queryLeft <= left && right <= queryRight)
            return arr[node];
        else {
            if (left != right) {
                int mid = (left + right) / 2;
                long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
                long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
                return leftResult + rightResult;
            } else
                return arr[node];
        }
    }
}