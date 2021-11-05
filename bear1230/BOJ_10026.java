import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static int dx[] = {1,-1,0,0}; 
    static int dy[] = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		char map[][] = new char[n][n];
		char arr[][]= new char[n][n];
        int cnt = 0;
		int cnt2= 0;
        
		for(int i = 0 ; i < n ; i ++){
			String row = br.readLine();
			for(int j = 0 ; j < n ; j++){
					map[i][j] = row.charAt(j);
					if(row.charAt(j) == 'R' || row.charAt(j) == 'G')
						arr[i][j]= 'R';
					else
						arr[i][j] = row.charAt(j);
				}
		}
		
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < n ; j++){
					if(map[i][j] != 'O'){
						cnt++;
						dfs(i,j,map[i][j],map);
					}
					if(arr[i][j] !='O'){
						cnt2++;
						dfs(i,j,arr[i][j],arr);
					}
				}
        }
					
		System.out.println(cnt+" "+cnt2);
	}
	
	public static void dfs(int y, int x,char color,char[][]map){
		map[y][x] = 'O';
		for(int i = 0 ; i < 4; i++){
			int ny = y + dy[i];
			int nx = x + dx[i];
            
			if(ny<0 || nx <0 || nx >= n || ny >= n || map[ny][nx] == 'O' || map[ny][nx] != color) continue;
			dfs(ny,nx,map[ny][nx],map);
			
		}
	}
	
}