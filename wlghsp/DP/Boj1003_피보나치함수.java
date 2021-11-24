package baekjoon;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Boj1003_피보나치함수 {

    static int[] result = new int[41];

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        result[0] = 0;
        result[1] = 1;
        result[2] = 1;

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                System.out.println("1 0");
            } else if (n==1) {
                System.out.println("0 1");
            } else {
                fibonacci(n);
                System.out.println(result[n-1] +" " + result[n]);
            }
        }
    }

        static int fibonacci(int n) {
            if (n == 0) {
                return 0;
            } else if (n==1) {
                return 1;
            } else {
                if (result[n]!=0) {
                    return result[n];
                } else{
                    result[n] = fibonacci(n-1) + fibonacci(n-2);
                    return result[n];
                }
            }
        }

}
