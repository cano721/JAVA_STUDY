import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		
		int n = 9;
		int[] arr = new int[n];
		int sum=0;
		boolean check =false;


		for(int i=0; i<n;i++){
			arr[i]=sc.nextInt();
			sum+=arr[i];
		}

		//continue - continue가 나온 이후의 코드를 무시
		for(int i=0;i<n;i++) {
			if(check)break;
			for(int j=0;j<n;j++) {
				if(i==j)continue;
				if(sum-arr[j]-arr[i]==100) {
					arr[i]=0;
					arr[j]=0;
					check=true;
					break;
				}
			}
		}

		Arrays.sort(arr);
		for(int i =0;i<9;i++) {
			if(arr[i]!=0)System.out.println(arr[i]);
		}

    }
}