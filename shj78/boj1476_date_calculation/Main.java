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
			//1 �̶�� ��,, 1���� ���� �ְ� 15�� ���� 16���� ���� �ִ�... �׷��� 31�� �� ���� �ִ�...
			//

		// ?�� 
        System.out.println(n);
    }
    
   
    

}