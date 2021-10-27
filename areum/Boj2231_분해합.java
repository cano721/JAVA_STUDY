import java.util.Scanner;

public class Boj2231_분해합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int res = 0;

        for(int i=0; i<N; i++) {
            int num = i;
            int sum = 0; // 자릿수 합

            while(num != 0) {
                sum += num % 10; // 자릿수 더하기
                num /= 10;
            }

            // i와 sum 이 같을 경우 → 생성자를 찾았을 경우
            if(i + sum == N) {
                res = i;
                break;
            }
        }
        System.out.println(res);
    }
}
