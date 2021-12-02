package cindya.bj11726_2xn타일링;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = 10007;
        int[] memory = new int[n];

        // 첫번째 두번째 값 세팅
        memory[0] = 1;
        if(n > 1)
            memory[1] = 2;

        for(int i = 2; i < n; i++)
            memory[i] = (memory[i - 1] + memory[i - 2]) % m; // 이전 두 값을 더한 값을 m으로 나눈 나머지를 취함

        System.out.println(memory[n - 1]);
    }
}
