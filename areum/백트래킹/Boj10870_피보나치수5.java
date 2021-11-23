package 백트래킹;

import java.util.Scanner;

public class Boj10870_피보나치수5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(fibo(n));
    }

    // n 번째를 찾을 때까지 재귀호출
    public static int fibo(int n) {
        if(n <= 1) {
            return n;
        } else {
            return fibo(n-1) + fibo(n-2);
        }
    }
}
