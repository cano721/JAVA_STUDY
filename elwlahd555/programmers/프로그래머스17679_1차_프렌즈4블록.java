package elwlahd555.programmers;

import java.util.LinkedList;

public class 프로그래머스17679_1차_프렌즈4블록 {
	public static void main(String[] args) {
		int m=6;
		int n=6;
		String board[]= {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		System.out.println(solution(m, n, board));
	}
	private static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        char map[][]=new char[m][n];
		int dx[]= {0,0,1,1};
		int dy[]= {0,1,0,1};
        for (int i = 0; i < board.length; i++) {
			map[i]=board[i].toCharArray();
		}
        while(check(map)) {
        	LinkedList<Point> que=new LinkedList<Point>();
    		for (int i = 0; i < map.length-1; i++) {
    			for (int j = 0; j < map[0].length-1; j++) {
    				int count=0;
    				char temp=map[i][j];
    				if(map[i][j]=='0') {
    					continue;
    				}else {
    					for (int k = 0; k < 4; k++) {
    						if(map[i+dx[k]][j+dy[k]]==temp) {
    							count++;
    						}else {
    							break;
    						}
    					}
    					if(count==4) {
    						que.add(new Point(i,j));
    					}
    				}
    			}
    		}
    		while(!que.isEmpty()) {
    			Point p=que.poll();
    			for (int i = 0; i < 4; i++) {
					map[p.x+dx[i]][p.y+dy[i]]='0';
				}
    		}
    		for (int i = 1; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j]=='0') {
						for (int k = i; k > 0; k--) {
							map[k][j]=map[k-1][j];
						}
						map[0][j]='0';
					}
				}
			}
        }
        for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j]=='0') {
					answer++;
				}
			}
		}
        return answer;
    }
	private static boolean check(char[][] map) {
		int dx[]= {0,0,1,1};
		int dy[]= {0,1,0,1};
		for (int i = 0; i < map.length-1; i++) {
			for (int j = 0; j < map[0].length-1; j++) {
				int count=0;
				char temp=map[i][j];
				if(map[i][j]=='0') {
					continue;
				}else {
					for (int k = 0; k < 4; k++) {
						if(map[i+dx[k]][j+dy[k]]==temp) {
							count++;
						}else {
							break;
						}
					}
					if(count==4) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
