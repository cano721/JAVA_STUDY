import java.util.Scanner;

public class Main2 {
 
	//한자리수나 둘째자리의 자연수는 모두 등차수열로 간주한다.
	//등차수열의 정의는 두 수의 차이로 이루어지는 수열이지만.
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