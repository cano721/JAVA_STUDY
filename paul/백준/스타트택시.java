import java.util.*;

class Taxi{
	int y,x;
	public Taxi(int y,int x) {
		this.y=y;
		this.x=x;
	}
}

class Customer implements Comparable<Customer>{
	int num;
	int sy,sx;
	int ay,ax;
	public Customer(int num, int sy, int sx, int ay, int ax) {
		super();
		this.num = num;
		this.sy = sy;
		this.sx = sx;
		this.ay = ay;
		this.ax = ax;
	}
    
	@Override
	public int compareTo(Customer o) {
		if(this.sy==o.sy) {
			return this.sx-o.sx;
		
		}else {
			return this.sy-o.sy;
		}
	}
	
}



public class Main {
	static int n,m,fuel;
	static int[][] map;
	static Customer[] customer;
	static Taxi taxi;
	static int[] xpos= {1,-1,0,0};
	static int[] ypos= {0,0,1,-1};
	static int cnt; //도착 성공한 승객 수
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		fuel=sc.nextInt();
		map=new int[n+1][n+1];
		customer=new Customer[m+1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				//벽은 -1표시
				map[i][j]=sc.nextInt();
				if(map[i][j]==1) {
					map[i][j]=-1; //벽 표시
				}
				
			}
		}
		taxi=new Taxi(sc.nextInt(),sc.nextInt()); //택시 위치 
		
		// 각 고객의 정보
		for (int i = 1; i <= m; i++) {
			int sy=sc.nextInt();
			int sx=sc.nextInt();
			int ay=sc.nextInt();
			int ax=sc.nextInt();
			customer[i]=new Customer(i,sy,sx,ay,ax);
			map[sy][sx]=i;// 출발지에는 택시 번호를 붙인다. (도착지는 표시 안함)
		}
		
		for (int i = 0; i < m; i++) {
			drive();
		}
		if(cnt!=m) {
			System.out.println(-1);
		}else {
			System.out.println(fuel);
		}
		
		
	}
	
	private static void drive() {
		int ty=taxi.y;
		int tx=taxi.x;
		if(map[ty][tx]>0) {//도착지나 출발지에 사람이 있다면 연료 소비 x 바로 도착지만 찾는다
			Customer cur=customer[map[ty][tx]];
			arrive(cur);
			return;
		}
		
		boolean[][] vis=new boolean[n+1][n+1];
		PriorityQueue<Customer> list=new PriorityQueue<>();
		Queue<Taxi> q=new LinkedList<>();
		q.add(new Taxi(ty,tx));
		vis[ty][tx]=true;
		int dist=1;//거리 1시작 (출발지=택시 경우 처리는 위에서 해줌)
		while(q.size()!=0) {
			int len=q.size();
			for (int i = 0; i < len; i++) {
				Taxi cur=q.poll();
				int y=cur.y;
				int x=cur.x;
				for (int k = 0; k < 4; k++) {
					int yy=y+ypos[k];
					int xx=x+xpos[k];
					
					if(xx<1 || yy<1 ||xx>n || yy>n)continue;
					if(map[yy][xx]==-1)continue;//벽
					if(vis[yy][xx])continue;
					vis[yy][xx]=true;
					

					if(map[yy][xx]==0) {//빈공간
						q.add(new Taxi(yy,xx));
					}else if(map[yy][xx]>0) {// 승객만남
						list.add(customer[map[yy][xx]]);
					}
				}
				
			}
			
			if(list.size()!=0)break; //가장 가까운 거리의 승객이 있음
			dist++;//비용증가
		}

		if(list.size()==0)return; //더이상 태울 승객이 없다
		if(fuel-dist<0)return; //도중 연료 미달. 여기서 -1 출력후 exit해도 될 거 같다
		fuel-=dist;
		arrive(list.poll());// 가장 가까운 고객 정보 넘김
			
	}
		
		

	private static void arrive(Customer cur) {

		boolean[][] vis=new boolean[n+1][n+1];
		Queue<Taxi> q=new LinkedList<>();
		q.add(new Taxi(cur.sy,cur.sx));//출발
		map[cur.sy][cur.sx]=0; // 더 이상 이승객 출발지 표시 없음
		
		int cost=1;
		while(q.size()!=0) {
			int len=q.size();
			if(cost>fuel) {// 도중에 연료 부족
				System.out.println(-1);
				System.exit(0);
			}
			for (int p = 0; p < len; p++) {
				Taxi t=q.poll();
				int y=t.y;
				int x=t.x;
				
				for (int i = 0; i < 4; i++) {
					int yy=y+ypos[i];
					int xx=x+xpos[i];
					if(xx<1 || yy<1 || xx>n || yy>n)continue; //범위 주의
					if(vis[yy][xx])continue;
					if(map[yy][xx]==-1)continue;
					if(yy==cur.ay && xx==cur.ax) {
						cnt+=1;
						taxi.y=yy;
						taxi.x=xx;
						fuel+=cost;
						//도착함. 택시 위치와 연료 갱신. 도착한 승객수 1 증가
						return;
					}else {
						q.add(new Taxi(yy,xx));
						vis[yy][xx]=true;
					}
				}
			}
			cost+=1;
			
		}
		
		
	}
}