/*
난쟁이 두명고르는 경우의수 N^2, 나머지 난쟁이 키 합 고르는 시간 복잡도 O(N)
-> O(N^3)
*/

import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = 9;
        int[] a = new int[n];
        int sum = 0;
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
            sum += a[i]; // sum에 9명 키합 저장
        }
        Arrays.sort(a); // 정렬
        for (int i=0; i<n; i++) { //포함x i번 j번 뽑기
            for (int j=i+1; j<n; j++) {
                if (sum - a[i] - a[j] == 100) {
                    for (int k=0; k<n; k++) {
                        if (i == k || j == k) continue;
                        System.out.println(a[k]);
                    }
                    System.exit(0);
                }
            }
        }
    }
}