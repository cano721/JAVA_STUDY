import java.util.Scanner;

public class BOJ_1094 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int length = 64;
        int x = sc.nextInt();
        int count = 0;
        int sum = 0;

        while (x > 0) {

            if (length > x) {
                length /= 2;
            } else {
                count++;
                x -= length;
            }
        }
        System.out.println(count);
    }
}
