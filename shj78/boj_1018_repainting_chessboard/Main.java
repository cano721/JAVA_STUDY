import java.util.Scanner;

class Main 
{
	static int result = Integer.MAX_VALUE;

	public void solution(int n, int m, int[][] board){
		int a = n+8;//행
		int b = m+8;//열 
		int count=0;
		int color = board[n][m];//첫번째 블록의 색깔 = 1


		
		for(int i=n; i<a; i++){//0~7
			for(int j=m; j<b; j++){//0~7
				if(board[i][j]!=color) {
					count++;
					//System.out.println(i+" "+j+" : "+color);
				}
				if(color==1) color=0;//현재 0번 색이라면 다음엔 1번 색이 나와야
				else color=1;
			}
			//System.out.println();
			if(color==0) color=1;//일전에 0이었다면 줄바꿈 시 0이 또 나와야 하는데 
			else if (color==1) color=0;
		}
		count = Math.min(count, 64-count); 
		result = Math.min(result, count);
	}

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
	
		int[][] board = new int[n][m];

		for(int i=0; i<n; i++){
				String str = sc.next();
			for(int j=0; j<m; j++){
				if(str.substring(j,j+1).equals("W")) {
					board[i][j]=1;			
				}else {
					board[i][j]=0;
				}
			}
		}

		Main T = new Main();

		for(int i=0; i<n-7; i++){
			for(int j=0; j<m-7; j++){
				T.solution(i,j,board);
			}
		}	
		
		System.out.println(result);
	}
}
