package elwlahd555.baekjoon;

import java.util.Scanner;

public class baekjoon17406_배열_돌리기4 {
	
	static class Point{
		int x,y,z;

		public Point(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
	}
	static boolean visit[];
	static int K,N,M;
	static Point cal[];
	static int arr[][];
	static int temparr[][];
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		N=sc.nextInt();
		M=sc.nextInt();
		K=sc.nextInt();
		temp= new Point[K];
		visit=new boolean[K];
		cal=new Point[K];
		arr=new int[N+1][M+1];
		temparr=new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		for (int i = 0; i < K; i++) {
			cal[i]=new Point(sc.nextInt(),sc.nextInt(),sc.nextInt());
		}
		
		solve(0);
		System.out.println(answer);
	}
	static Point temp[];
	static int answer=Integer.MAX_VALUE;
	private static void solve(int cnt) {
		// TODO Auto-generated method stub
		if(cnt==K) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					temparr[i][j]=arr[i][j];
				}
			}
			rotate(temp);
			for (int i = 1; i <= N; i++) {
				int result=0;
				for (int j = 1; j <= M; j++) {
					result+=temparr[i][j];
				}
				if(answer>result) {
					answer=result;
				}
			}
			return;
		}
		
		for(int i=0;i<K;i++) {
			if(visit[i])continue;
			temp[cnt]=cal[i];
			visit[i]=true;
			solve(cnt+1);
			visit[i]=false;
		}
	}
	private static void rotate(Point[] temp) {
		// TODO Auto-generated method stub
		for(int k=0;k<K;k++) {
			
			Point p=temp[k];
			for(int i=1;i<=p.z;i++) {
				int ptype=-1;
				for(int y=p.y-i;y<=p.y+i;y++) {
					if(ptype==-1) {
						ptype=temparr[p.x-i][y];
						
					}else {
						int temp1=ptype;
						ptype=temparr[p.x-i][y];
						temparr[p.x-i][y]=temp1;
					}
				}
				for(int x=p.x-i+1;x<=p.x+i;x++) {
						int temp1=ptype;
						ptype=temparr[x][p.y+i];
						temparr[x][p.y+i]=temp1;
				}
				for(int y=p.y+i-1;y>=p.y-i;y--) {
					int temp1=ptype;
					ptype=temparr[p.x+i][y];
					temparr[p.x+i][y]=temp1;
				}
				for(int x=p.x+i-1;x>=p.x-i;x--) {
					int temp1=ptype;
					ptype=temparr[x][p.y-i];
					temparr[x][p.y-i]=temp1;
				}
			}
		}
	}

}
