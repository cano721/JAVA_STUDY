import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D20211106_2_BJ_16197_두동전 {
	static int sol = Integer.MAX_VALUE-1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		if(n+m <= 3) {
			System.out.println(1);
			return;
		}
		
		char[][] map = new char[n][m];
		String line = "";
		boolean isFirst = true;
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		
		for(int i = 0; i < n; i++) {
			line = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'o' && isFirst) {
					y1 = i;
					x1 = j;
					isFirst = !isFirst;
				} else if (map[i][j] == 'o' && !isFirst) {
					y2 = i;
					x2 = j;
				}
			}
		}
		
		sol(map,y1, x1, y2, x2, 0, n, m);
		
		System.out.println(sol == Integer.MAX_VALUE-1 ? -1 : sol); 
		
	}
	
	public static void sol(char[][] map, int y1, int x1, int y2, int x2, int count, int n, int m) {
		if (sol >= count || count == 10) return ;
		int addArrX[] = { -1, 1, 0, 0 };
		int addArrY[] = { 0, 0, -1, 1 };
		for(int i = 0; i < 4; i++) {
			int dropCoin = 0;
			int addX1 = x1 + addArrX[i];
			int addY1 = y1 + addArrY[i];
			
			int addX2 = x2 + addArrX[i];
			int addY2 = y2 + addArrY[i];
			
			if (addX1 < 0 || addX1 >= m || addY1 < 0 || addY1 >= n) ++dropCoin;
			if (addX2 < 0 || addX2 >= m || addY2 < 0 || addY2 >= n) ++dropCoin;
			
			if(dropCoin == 1) {
				sol = Math.min(sol, count);
				return;
			} 
			if(dropCoin == 2) continue;
			
			if(map[addY1][addX1] == '#') {
				addX1 = x1;
				addY1 = y1;
			}
			if(map[addY2][addX2] == '#') {
				addX2 = x2;
				addY2 = y2;
			}
			sol(map, addY1, addX1, addY2, addX2, ++count, n, m);
		}
	}
}
