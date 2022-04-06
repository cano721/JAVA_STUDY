
/**
 * 1. 50*50 맵 크기. 최대 13개의 치킨집 고를 수 있음
 * 
 * 2. m개의 치킨집 선택.
 * 2-1) 50*50 에서 m개를 고를려면 시간복잡도 문제가 됨..
 * 2-2) 맵에서 치킨집리스트를 별도로 빼고, 그 안에서 선택!(최대 13개 있다고 함.)
 * 2-3) 13개중에서 m개 선택 시간복잡도. 13Cm
 * 
 * 3. 선택한 치킨집에서 집리스트를 돌면서 거리 계산.
 *    전체 치킨집(13) * 집리스트(최대2n)
 * 3-1) 치킨집마다 각 집으로의 거리 중 최소값을 저장
 * 
 * 4. m개의 치킨집에서 다 해본 결과의 전체 치킨 거리를 정답에 저장
 * 
 * 5. m개를 고를 수 있는 모든 경우의 수 수행한 후 최소값 출력
 * 
 * 13Cm * m*2n
 */

import java.util.*;
import java.io.*;


public class 치킨배달 {

    public static int n,m,answer = Integer.MAX_VALUE;
    public static int[][] map;
    public static ArrayList<int[]> chicken,home;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        chicken = new ArrayList<>();
        home = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());

                if(num == 1){
                    home.add(new int[] {i,j});
                }else if(num == 2){
                    chicken.add(new int[] {i,j});
                }
                map[i][j] = num;
            }
        }

        dfs(0,0,-1);

        System.out.println(answer);
    }

    // 치킨집 고르는 함수
    public static void dfs(int stage,int check,int pidx){
        // 다 고른 경우
        if(stage == m){
            calc(check);
            return;
        }

        // 고르기(비트마스킹을 통해 방문여부 체크)
        for(int i = pidx+1; i < chicken.size(); i++){
            if((check & (1<<i)) == 0){
                dfs(stage+1,check + (1<<i),i);
            }
        }
    }

    // 고른 치킨집 돌면서 거리 계산 함수
    public static void calc(int check){
        int[] dis = new int[home.size()]; // 각 집까지의 거리 저장
        Arrays.fill(dis,Integer.MAX_VALUE);

        for(int i = 0; i < chicken.size(); i++){
            if((check & (1<<i)) != 0){ // 선택된 치킨집일때 체크
                for(int j = 0; j <home.size(); j++){
                    int[] chicken_pos = chicken.get(i);
                    int[] home_pos = home.get(j);

                    int curDis = Math.abs(chicken_pos[0]-home_pos[0])
                                + Math.abs(chicken_pos[1] - home_pos[1]);
                    
                    dis[j] = Math.min(dis[j],curDis);
                }
            }
        }

        int temp = 0;
        for (int i : dis) {
            temp += i;
        }

        answer = Math.min(answer, temp);
    }
}
