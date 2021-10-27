import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	//static�� �̿��� ���� ������ �����.
	static int k;
	static int[] arr;
	static boolean[] skip; //��ŵ �Ǻ�
	
	static void dfs(int line,int count) {
		
		if(count == 6) {
			for(int i = 0; i < k; i++) {
				if(skip[i] == true)
					System.out.print(arr[i] + " ");
			}
			System.out.println("");
		}
		
		for(int j = line; j < k; j++) {
			skip[j] = true;
			dfs(j+1, count+1); //���ȣ��
			skip[j] = false; //���ȣ�� ������ �ش� �迭�� Ž�������Ѵ�.
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			k = sc.nextInt(); //���� ����, 6~13���� ��
			if(k == 0) //0�Է½� ����
				break;
			
			arr = new int[k]; //������ŭ �迭 ����
			skip = new boolean[k]; //��Ʈ��ŷ
			
			for(int i = 0; i < arr.length; i++) { //�迭 �Է�
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			
			dfs(0,0);
			System.out.println();
		}
		sc.close();
	}
}