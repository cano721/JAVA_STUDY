package cindya.bj10870_피보나치수_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = n > 0 ? findFibonacci(n - 1, 0, 1) : 0;

        System.out.println(answer);

        br.close();
    }

    public static int findFibonacci(int n, int f1, int f2){
        if(n == 0) return f2;
        return findFibonacci(n - 1, f2, f1 + f2);
    }
}
