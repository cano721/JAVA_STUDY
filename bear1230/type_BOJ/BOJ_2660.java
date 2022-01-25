import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int n;
    static int INF = 51;
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        
        for(int i = 1; i < n+1; i++) {
			for(int j = 1; j < n+1; j++) {
				if(i != j)
					map[i][j] = 987654321;
			}
		}
        
        while(true) {
            st = new StringTokenizer(br.readLine(), " ");
            int a =  Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());

			if(a == -1 && b == -1) break;
			map[a][b] = map[b][a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 1) continue;
                map[i][j] = INF;
            }
        }

        floyd();

        int min = Integer.MAX_VALUE;
        ArrayList<Integer> list = new ArrayList<>();

		int[] idx = new int[n+1];
		
		// 점수를 조회하여 가장 낮은 점수를 찾음 
		for(int i = 1; i<=n; i++) {
			int score = 0;
			for(int j = 1; j<=n; j++) {
				if(i == j) continue;
				score = Math.max(score, map[i][j]);
			}
			idx[i] = score;
			min = Math.min(score, min);
		}
        for(int i = 1; i<=n; i++) {
			if(min == idx[i]) list.add(i);
		}

        System.out.println(min + " " + list.size());
        for (Integer c : list) {
            System.out.print(c + " " );
        }
    }

    private static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] <= map[i][k] + map[k][j]) continue;
                    map[i][j] = map[i][k] + map[k][j];
                }
            }
        }
    }
}