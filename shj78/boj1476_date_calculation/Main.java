import java.util.Scanner;
 
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int e = sc.nextInt();
        int s = sc.nextInt();
        int m = sc.nextInt();

        //int n=(e%16)+ (s%29)+ (m%20);
			 //1 + 16 + 16
		int n=(e/15)+ (s/28)+ (m/19);
			//1 이라는 건,, 1년일 수도 있고 15가 넘은 16년일 수도 있다... 그래서 31년 일 수도 있다...
			//

		// ?년 
        System.out.println(n);
    }
    
   
    

}