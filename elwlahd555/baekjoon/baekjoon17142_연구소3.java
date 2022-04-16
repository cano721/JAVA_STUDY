package elwlahd555.baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pos{
	int y,x;

	public Pos(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}
	
}

public class baekjoon17142_연구소3 {
	static int n,m;//연구소 크기,바이러스 개수
	static int[][] map;
	static int[] xpos= {0,0,1,-1};
	static int[] ypos= {1,-1,0,0};
	static ArrayList<Pos> virus=new ArrayList<>();
	static ArrayList<Integer> list=new ArrayList<>();
	static int zero;
	static int answer=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		map=new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j]=sc.nextInt();//0 빈칸 1 벽 2바이러스
				if(map[i][j]==2) {
					virus.add(new Pos(i,j));//바이러스 좌표 m개 이상이다.
				}
				if(map[i][j]==0) {
					zero++;
				}
			}
		}
		if(zero==0) {
			System.out.println(0);
			return;
		}
		select(0,0);
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	private static void select(int cnt,int start) {
		if(cnt==m) {
			answer=Math.min(answer, bfs());
			return;
		}
		
		for (int i = start; i <virus.size(); i++) {
			list.add(i);
			select(cnt+1,i+1);
			list.remove(list.size()-1);

		}
		
	}
	private static int bfs() {
		boolean[][] vis=new boolean[n][n];
		int[][] copy=new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j]==1 || map[i][j]==2) {
					copy[i][j]=-map[i][j];
				}else {
					copy[i][j]=map[i][j];
				}
			}
		}
		

		
		Queue<Pos> q=new LinkedList<Pos>();
		for (int i = 0; i < list.size(); i++) {
			int num=list.get(i);
			q.add(new Pos(virus.get(num).y,virus.get(num).x));
			copy[virus.get(num).y][virus.get(num).x]=0;//바이러스 표시
			vis[virus.get(num).y][virus.get(num).x]=true;
			
		}
		
		
		int time=1;
		int cnt=0;
		while(q.size()>0) {
			int len=q.size();
			for (int i = 0; i < len; i++) {
				Pos cur=q.poll();
				int y=cur.y;
				int x=cur.x;
				for (int k = 0; k < 4; k++) {
					int yy=y+ypos[k];
					int xx=x+xpos[k];
					if(xx<0 || yy<0 || xx>=n || yy>=n)continue;
					if(vis[yy][xx])continue;
					if(copy[yy][xx]==0) {
						vis[yy][xx]=true;
						copy[yy][xx]=time;
						q.add(new Pos(yy,xx));
						cnt+=1;
						if(cnt==zero)return time;
					}else if(copy[yy][xx]==-2) {
						vis[yy][xx]=true;
						copy[yy][xx]=time;
						q.add(new Pos(yy,xx));
					}
				}
			}
			time+=1;
		}
		
		return Integer.MAX_VALUE;
	}
}
