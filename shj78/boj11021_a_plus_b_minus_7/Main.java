import java.util.Scanner;

class Main
{


	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);

		int a = sc.nextInt();

		int b,c;
		for(int i=0; i<a; i++){
			b=sc.nextInt();
			c=sc.nextInt();

			System.out.printf("Case #%d: %d\n", i+1, b+c);
		}
	}
}
