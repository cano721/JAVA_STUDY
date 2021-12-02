package cindya.bj1309_동물원;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] cases = new int[n][3];
        final int m = 9901;

        // 첫번째 줄에 사자가 없는 경우, 1번 우리에 있는 경우, 2번 우리에 있는 경우 각각 1씩 세팅
        cases[0][0] = cases[0][1] = cases[0][2] = 1;

        // 2번째 줄부터 n번째 줄까지 루프
        for(int i = 1; i < n; i++){
            // 사자가 없는 경우는 모든 경우 뒤에 올 수 있음
            cases[i][0] = (int)(((long)cases[i - 1][0] + cases[i - 1][1] + cases[i - 1][2]) % m);
            // 우리에 있는 경우 이전 줄의 사자가 같은 우리에 있는 경우를 피해야함
            cases[i][1] = (cases[i - 1][0] + cases[i - 1][2]) % m;
            cases[i][2] = (cases[i - 1][0] + cases[i - 1][1]) % m;
        }
        System.out.println(((long)cases[n - 1][0] + cases[n - 1][1] + cases[n - 1][2]) % m);
    }
}
