import java.util.LinkedList;
import java.util.Queue;
class PG_거리두기확인하기 {
    public int[] solution(String[][] places) {
        int []answer = new int[5];

		for(int k=0; k<5; k++) {
			String []now = places[k];
			boolean ch = true;
			a : for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					if(now[i].charAt(j) == 'P') {
						if(!cal(i, j, now)) {
							ch = false;
							break a;
						}
					}
				}
			}
			answer[k] = ch? 1 : 0;
		}
        return answer;
    }
    public boolean cal(int x, int y, String []now) {
		int []px = {-1,1,0,0};
		int []py = {0,0,-1,1};
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{x,y});
		
		while(!q.isEmpty()) {
			int[]pos = q.poll();
			
			for(int i=0; i<4; i++) {
				int x1 = pos[0] + px[i];
				int y1 = pos[1] + py[i];
				
				int dist = Math.abs(x-x1)+Math.abs(y-y1);
				
				if(x1 >= 5 || x1 <0 || y1 >= 5 || y1< 0 || (x1 == x && y1 == y)) continue;
				
				if(dist <= 2 && now[x1].charAt(y1) == 'P') return false;
				else if(dist <2 && now[x1].charAt(y1)== 'O'){
					q.add(new int[]{x1,y1});
				}
			}
		}
		return true;
	}
}