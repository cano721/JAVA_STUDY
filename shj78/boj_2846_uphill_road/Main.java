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
				//if���� �� �ɰ��� ������ �ϴ� ��ŵ 
				if(arr[j]>flag&&std<arr[j]){//20
					max=Math.max(max, arr[j]-flag);//��������
					std=arr[j];
				}else {//�������� Ȯ��!
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
