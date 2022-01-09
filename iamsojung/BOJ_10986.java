import java.util.Scanner;

public class BOJ_10986 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        //long[] pSum = new long[n + 1];
        int pSum = 0;
        int[] remainders = new int[m];
        remainders[0]++;
        for (int i = 0; i < n; i++) {
            pSum += array[i];
            pSum %= m;
            remainders[pSum]++;
        }

        long result = 0;
        for (int i = 0; i < m; i++) {
            if (remainders[i] > 1) {
                result += (long)remainders[i] * (long)(remainders[i] - 1) / 2;
            }
        }

        System.out.print(result);
    }
}