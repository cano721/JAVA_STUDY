import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
class PG_gamemap {
    public boolean [][]checked;
	public int [][]arr;
	
    public int solution(int[][] maps) {
        
        int row = maps.length;
		int col = maps[0].length;
		
		checked = new boolean[row][col];
		arr = new int[row][col];
		
		for(int []i : arr) Arrays.fill(i, -1);
		cal(maps);
        return arr[row-1][col-1];
    }
    public void cal(int [][]maps) {
		int []nx = {1,-1,0,0};
		int []ny = {0,0,-1,1};
		
		Queue<int []> q = new LinkedList<>();
		q.add(new int []{0,0});
		arr[0][0] = 1;
		while(!q.isEmpty()) {
			int []pos = q.poll();
			int x = pos[0];
			int y = pos[1];
			checked[x][y] = true;
			
			for(int i=0; i<4; i++) {
				int x1 = x + nx[i];
				int y1 = y + ny[i];
				
				if(x1 <0 || y1 <0 || x1 >= maps.length || y1 >= maps[0].length) {
					continue;
				}
				if(checked[x1][y1]) continue;
				if(maps[x1][y1] == 0) continue;
				else {
					checked[x1][y1] = true;
					arr[x1][y1] = arr[x][y]+1;
					q.add(new int[] {x1, y1});
				}
			}
			
		}
	}
}