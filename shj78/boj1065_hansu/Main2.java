import java.util.Scanner;

public class Main2 {
 
	//���ڸ����� ��°�ڸ��� �ڿ����� ��� ���������� �����Ѵ�.
	//���������� ���Ǵ� �� ���� ���̷� �̷������ ����������.
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();

		int answer=0;

		for(int i=1; i<=n; i++){
			int num=i;
			int k=0, g=0, l=0;
			
			if(num<100){
				answer++;
			}
			else{
				int a=1;
				while(num!=0){
				
					k=num%10;

					if(a>2) if(g!=(l-k)) break;

					if(a>1) g=l-k;

					l=k;

					num=num/10; 
					a++;

					if(num==0) answer++;											
				}				
			}
		}
		System.out.println(answer);	
	}
}