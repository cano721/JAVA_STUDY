package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
    final int mod = 1000000007;

	public int solution(int m, int n, int[][] puddles) {
		int[][] map = new int[m+1][n+1];
        for(int[] puddle: puddles)
            map[puddle[0]][puddle[1]] = -1;
        map[1][0] = 1;
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(map[i][j] == -1)
                    continue;
                int left = Math.max(map[i-1][j], 0) % mod;
                int up = Math.max(map[i][j-1], 0) % mod;
                map[i][j] = (left + up) % mod;
            }
        }
        return map[m][n] % mod;
	}
}