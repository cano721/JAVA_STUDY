
/**
 * 각 간선(친구사이)의 비용을 1로 두고
 * 플로이드와샬을 돌려서 각각과 연결이 어떻게 되어있는지 확인
 * 최소비용의 회장후보를 출력
 */

import java.io.*;
import java.util.*;

public class BJ2660_회장뽑기 {

    public static int n;
    public static int[][] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dist = new int[n+1][n+1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        StringTokenizer st;

        // 간선 초기값 넣기
        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1){
                break;
            }

            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        floyd();
        int c_score = Integer.MAX_VALUE; // 회장 후보 점수
        ArrayList<Integer> a = new ArrayList<>(); //회장 후보들


        for(int i = 1; i <= n; i++){
            //현재 후보 점수 구하기
            int score = 0;
            for(int j = 1; j <= n; j++){
                //본인이면 패스
                if(i == j) continue;
                score = Math.max(score,dist[i][j]);
            }
            //기존점수보다 현재후보점수 적으면 변경
            if(c_score > score){
                c_score = score;
                a.clear();
                a.add(i);
            //동일하면 후보 추가
            }else if(c_score == score){
                a.add(i);
            }
        }

        bw.write(c_score + " " + a.size() +"\n");
        for(int v: a){
            bw.write(v +" ");
        }
        bw.flush();
        bw.close();
    }
    
    //플로이드 와샬 돌리기
    public static void floyd(){
        for(int Node = 1; Node <= n; Node++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(dist[i][Node] == Integer.MAX_VALUE || dist[Node][j] == Integer.MAX_VALUE){
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j],dist[i][Node]+dist[Node][j]);
                }
            }
        }
    }
}
