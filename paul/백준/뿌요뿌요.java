import java.io.*;
import java.util.*;

public class Main {
	static char[][] puyoMap = new char[12][6];
	static boolean[][] visited = new boolean[12][6];
	static int[] dx = {1,-1,0,0}, dy = {0,0,-1,1};
	static int maxX=0;
	static ArrayList<PuyoPoint> puyoTarget = new ArrayList<PuyoPoint>();
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		
		createMap(br);

		while(true){
		
			if(!findTarget()) break;
			
			removeTarget();
			ans++;
		}
		
        System.out.println(ans);
		
	}

	private static void removeTarget() {
		for(int row = 0 ; row < 6 ; row ++){
			for(int column = maxX; column >= 0  ; column--){
				visited[column][row] = false; 
				if(puyoMap[column][row] == 'T'){
					if(column == 11) {
						puyoMap[11][row] = '.';
						continue;
					}
					goDown(row, column);
				}
			}
		}
	}

	private static void goDown(int row, int column) {
		for(int i = column ; i < 11 ;i++){
			puyoMap[i][row] = puyoMap[i+1][row];
		}
		puyoMap[11][row] = '.';
	}
	
	private static boolean findTarget() {
		boolean flag = false;
		for(int column = 0; column < maxX ; column++){
			for(int row = 0 ; row < 6 ; row ++){
				if(visited[column][row] || puyoMap[column][row] == '.') continue;
				puyoTarget = new ArrayList();
				getTarget(column,row);
				if(puyoTarget.size() >= 4)  {
					flag = true;
					for(PuyoPoint puyoPoint : puyoTarget){
						puyoMap[puyoPoint.x][puyoPoint.y] = 'T';
					}
				}
			}
		}
		return flag;
	}

	private static void getTarget(int column, int row) {
		visited[column][row] = true; 
		puyoTarget.add(new PuyoPoint(column,row));
		for(int i = 0 ; i < 4 ; i++){
			int nx = column + dx[i];
			int ny = row + dy[i];
			if(!isValidIndex(nx,ny) || visited[nx][ny] || puyoMap[nx][ny] == '.' || puyoMap[nx][ny] != puyoMap[column][row]) continue;
			getTarget(nx,ny);
		}
	}

	private static boolean isValidIndex(int x, int y) {
		return (x < 12 && x >= 0 && y < 6 && y >= 0);
	}

	private static void createMap(BufferedReader br) throws IOException {
		for(int column = 11 ; column >= 0 ; column--){
			String line = br.readLine();
			for(int row = 0 ; row < 6 ; row ++){
				puyoMap[column][row] = line.charAt(row);
				if(puyoMap[column][row] != '.') maxX = Math.max(column, maxX); 
			}
		}
	}
}

class PuyoPoint{
	int x; 
	int y;
	
	public PuyoPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
}