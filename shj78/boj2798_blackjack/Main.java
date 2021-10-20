import java.util.Scanner;

class Main{



	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();

		int b = sc.nextInt();

		int[] arr = new int[a];

		for(int i=0; i<a; i++){
			arr[i]=sc.nextInt();
		}

		
		int lt, mt, rt;
		int max2;
		max2=Integer.MIN_VALUE;

		for(int i=0; i<a-2; i++){

			for(int j=i+1; j<a-1; j++){

				for(int k=j+1; k<a; k++){

					lt=arr[i];
					mt=arr[j];
					rt=arr[k];

					if(max2<lt+mt+rt && b>=lt+mt+rt) max2 = lt+mt+rt;


				}


			}
		}

		System.out.println(max2);
			
	}
}
