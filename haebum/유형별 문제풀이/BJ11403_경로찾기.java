
/**
 * 각정점에서 정점으로 갈 수 있는지 여부를 저장
 * 플로이드와샬로 풀 예정(각정점에서 정점으로 최소비용 이동)
 */

import java.util.*;
import java.io.*;
public class BJ11403_경로찾기 {

    public static int n;
    public static int[][] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());

        dist = new int[n][n];

        // 초기화
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dist[i][j] = 1_000_000;
            }
        }

        StringTokenizer st;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 1){
                    dist[i][j] = Math.min(dist[i][j],num);
                }
            }
        }

        floyd();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(dist[i][j]  < 1_000_000){
                    bw.write(1 + " ");
                }else{
                    bw.write(0 + " ");
                }
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    public static void floyd(){

        for(int mNode = 0; mNode < n; mNode++){
            for(int start = 0; start < n; start++){
                for(int end = 0; end < n; end++){
                    dist[start][end] = Math.min(dist[start][end], dist[start][mNode] + dist[mNode][end]);
                }
            }
        }
    }
}
