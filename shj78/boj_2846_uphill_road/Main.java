import java.util.Scanner;

class Main 
{

	public int solution(int n, int[] arr){
		int max=0;

		int flag;

		int std;

		for(int i=0;i<n;i++){
			flag = arr[i]; //12
			std = flag; //12
			for(int j=i+1;j<n;j++){
				//if문을 더 쪼개야 겠지만 일단 스킵 
				if(arr[j]>flag&&std<arr[j]){//20
					max=Math.max(max, arr[j]-flag);//오르막길
					std=arr[j];
				}else {//내리막길 확실!
					flag=arr[j];
					std=flag;	
				}
			}
		}

		return max;
		
	}

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[n];

		for(int i=0; i<n; i++) arr[i]=sc.nextInt();
		
		Main T = new Main();

		System.out.println(T.solution(n, arr));
	}
}
