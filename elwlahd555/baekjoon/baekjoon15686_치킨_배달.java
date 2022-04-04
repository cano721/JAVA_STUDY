package elwlahd555.baekjoon;

import java.util.Scanner;

public class baekjoon15686_치킨_배달 {
	
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static Point chickenarr[];
	static Point housearr[];
	static Point temp[];
	static int cnt,cnt2;
	static int N,M;
	static int result=99999999;
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		N=sc.nextInt();
		M=sc.nextInt();
		temp=new Point[M];
		chickenarr=new Point[13];
		housearr=new Point[2*N];
		for(int i=0;i<N;i++) {
			for (int j = 0; j < N; j++) {
				int temp=sc.nextInt();
				if(temp==2) {
					chickenarr[cnt++]=new Point(i,j);
				}else if(temp==1) {
					housearr[cnt2++]=new Point(i,j);
				}
			}
		}
		solve(0,0);
		System.out.println(result);
	}
	private static void solve(int count,int start) {
		// TODO Auto-generated method stub
		if(count==M) {
			int answer=0;
			for(int i=0;i<cnt2;i++) {
				int no=9999999;
				
				for(int j=0;j<M;j++) {
					if(no>Math.abs(housearr[i].x-temp[j].x)+Math.abs(housearr[i].y-temp[j].y)) {
						no=Math.abs(housearr[i].x-temp[j].x)+Math.abs(housearr[i].y-temp[j].y);
					}
				}
				answer+=no;
			}
			if(result>answer) {
				result=answer;
			}
			return;
		}
		
		for (int i = start; i < cnt; i++) {
			temp[count]=chickenarr[i];
			solve(count+1,i+1);
		}
	}

}
