package week1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_2143 {
    static int T, N, M;
    static long ans;
    static int[] A, B;

    static List<Integer> a, b;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        N = sc.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        M = sc.nextInt();
        B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = sc.nextInt();
        }

        a = new ArrayList<>();
        get(A, a);
        b = new ArrayList<>();
        get(B, b);

        Collections.sort(b);

        ans = 0;
        for (Integer i : a) {
            int val = T - i;
            int high = upper_bound(b, val);
            int low = lower_bound(b, val);
            ans += high - low;
        }
        System.out.println(ans);
    }

    private static void get(int[] data, List<Integer> list) {
        for (int size = 1; size <= data.length; size++) {
            int sum = 0;
            for (int i = 0; i < size; i++) {
                sum += data[i];
            }
            for (int i = size; i < data.length; i++) {
                list.add(sum);
                sum += data[i];
                sum -= data[i - size];
            }
            list.add(sum);
        }
    }

    private static int lower_bound(List<Integer> list, int val) {
        int start = 0;
        int end = list.size();
        int mid;
        while (start < end) {
            mid = (start + end) >> 1;
            if (list.get(mid) >= val) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private static int upper_bound(List<Integer> list, int val) {
        int start = 0;
        int end = list.size();
        int mid;
        while (start < end) {
            mid = (start + end) >> 1;
            if (list.get(mid) <= val) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
