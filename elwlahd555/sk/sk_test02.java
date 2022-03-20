package elwlahd555.sk;

import java.util.LinkedList;

public class sk_test02 {
	public static void main(String[] args) {
		int n=9;
		boolean clockwise=false;
		System.out.println(solution(n, clockwise));
	}
	private static class Point{
		int x,y,direction;

		public Point(int x, int y, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
		
	}
    public static int[][] solution(int n, boolean clockwise) {
        int[][] answer = new int[n][n];
        int count=1;
        int size=n-1;
        LinkedList<Integer>sizecount=new LinkedList<Integer>();
        sizecount.add(1);
        sizecount.add(1);
        
        if(clockwise) {
        	Point p1=new Point(0,0,0);
        	Point p2=new Point(0,n-1,1);
        	Point p3=new Point(n-1,n-1,2);
        	Point p4=new Point(n-1,0,3);
        	LinkedList<Point> que=new LinkedList<Point>();
        	que.add(p1);
        	que.add(p2);
        	que.add(p3);
        	que.add(p4);
        	answer[0][0]=count;
        	answer[n-1][0]=count;
        	answer[n-1][n-1]=count;
        	answer[0][n-1]=count;
        	count++;
        	while(size>0) {
        		int temp=sizecount.poll();
        		size-=temp;
        		sizecount.add(temp+1);
        		for (int i = 0; i < size; i++) {
        			for (int j = 0; j < que.size(); j++) {
        				Point p=que.poll();
        				if(p.direction==0) {
        					p.y++;
        					answer[p.x][p.y]=count;
        					que.add(p);
        				}else if(p.direction==1) {
        					p.x++;
        					answer[p.x][p.y]=count;
        					que.add(p);
        				}else if(p.direction==2) {
        					p.y--;
        					answer[p.x][p.y]=count;
        					que.add(p);
        				}else {
        					p.x--;
        					answer[p.x][p.y]=count;
        					que.add(p);
        				}
        			}
        			count++;
        		}
        		for (int i = 0; i < que.size(); i++) {
        			Point p=que.poll();
        			p.direction++;
        			if(p.direction>3) {
        				p.direction=0;
        			}
        			que.add(p);
        		}
        	}
        }else {
        	LinkedList<Point> que=new LinkedList<Point>();
        	que.add(new Point(0,0,0));
        	que.add(new Point(n-1,0,1));
        	que.add(new Point(n-1,n-1,2));
        	que.add(new Point(0,n-1,3));
        	answer[0][0]=count;
        	answer[n-1][0]=count;
        	answer[n-1][n-1]=count;
        	answer[0][n-1]=count;
        	count++;
        	while(size>0) {
        		int temp=sizecount.poll();
        		size-=temp;
        		sizecount.add(temp+1);
        		for (int i = 0; i < size; i++) {
        			for (int j = 0; j < que.size(); j++) {
        				Point p=que.poll();
        				if(p.direction==0) {
        					p.x++;
        					answer[p.x][p.y]=count;
        					que.add(p);
        				}else if(p.direction==1) {
        					p.y++;
        					answer[p.x][p.y]=count;
        					que.add(p);
        				}else if(p.direction==2) {
        					p.x--;
        					answer[p.x][p.y]=count;
        					que.add(p);
        				}else {
        					p.y--;
        					answer[p.x][p.y]=count;
        					que.add(p);
        				}
        			}
        			count++;
        		}
        		for (int i = 0; i < que.size(); i++) {
        			Point p=que.poll();
        			p.direction++;
        			if(p.direction>3) {
        				p.direction=0;
        			}
        			que.add(p);
        		}
        	}
        }
        if(n%2==1) {
        	answer[n/2][n/2]=count;
        }
        return answer;
    }
}
