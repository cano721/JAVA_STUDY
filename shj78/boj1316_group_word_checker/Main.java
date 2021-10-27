import java.util.Scanner;
import java.util.Arrays;



class Main
{
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int answer=0;

		int n=sc.nextInt();

		String[] arr = new String[n];

		for(int i=0; i<n; i++) arr[i]=sc.next();
			
		boolean[] alpa = new boolean[26];
		char temp='@';
		for(int i=0; i<n; i++){
			Arrays.fill(alpa, false);
			//h a p p y
			char[] x = arr[i].toCharArray();
			for(int j=0; j<x.length; j++){
				if(!alpa[x[j]-97]) alpa[x[j]-97]=true;
				else if(temp!=x[j]) break;
				
				if(j==x.length-1) answer++;

				temp=x[j];

			}
		}

		

		System.out.println(answer);
				
		

	}
}
