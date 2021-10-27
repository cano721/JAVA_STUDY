import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	//static을 이용해 공유 변수를 만든다.
	static int k;
	static int[] arr;
	static boolean[] skip; //스킵 판별
	
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
			dfs(j+1, count+1); //재귀호출
			skip[j] = false; //재귀호출 끝나고 해당 배열을 탐색종료한다.
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			k = sc.nextInt(); //숫자 갯수, 6~13사이 값
			if(k == 0) //0입력시 종료
				break;
			
			arr = new int[k]; //갯수만큼 배열 생성
			skip = new boolean[k]; //백트래킹
			
			for(int i = 0; i < arr.length; i++) { //배열 입력
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			
			dfs(0,0);
			System.out.println();
		}
		sc.close();
	}
}