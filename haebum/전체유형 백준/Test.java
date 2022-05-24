import java.io.IOException;
import java.util.Scanner;

/**
 * factorial 구현
 * : 4! = 4*3*2*1
 * 
 * 1) 4*3*2*1  1씩 줄어들면서 곱하기를 해여!
 * 
 * 2) 
 */

public class Test {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int num = Integer.parseInt(str);

        int result = factorial(num);
        System.out.println(result);
    }

    public static int factorial(int num){
        if(num == 1){
            return 1;
        }

        return num*factorial(num-1);
    }
}
