package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class n2660_회장뽑기 {
	static int n;
	static int INF = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    arr[i][j] = INF;
                }
            }
        }

        while(true) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	if(x == -1 && y == -1) break;
        	
        	arr[x][y] = arr[y][x] = 1;
        }
        // 플로이드 와샬 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
        
        int minValue = Integer.MAX_VALUE;
        int[] candidate = new int[n+1];
        for (int i = 1; i <= n; i++) {
        	int score = 0;
			for (int j = 1; j <= n; j++) {
				if(arr[i][j] != INF && i != j) {
					score = Math.max(arr[i][j], score);
				}
			}
			candidate[i] = score;
			minValue = Math.min(minValue, score);
		}
        
        System.out.print(minValue+" ");
        StringBuilder body = new StringBuilder();
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
			if(minValue == candidate[i]) {
				cnt++;
				body.append(i + " ");
			}
		}
        System.out.println(cnt);
        bw.write(body.toString() + "\n");
        bw.flush();
        bw.close();
        br.close();
	}

}
