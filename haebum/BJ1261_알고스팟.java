
/**
 * 다익스트라로 해당 위치에 벽 부순 횟수를 저장해가면서 이동
 * 변경될때 큐에 삽입
 * 
 */

import java.util.*;
import java.io.*;

public class BJ1261_알고스팟 {
    
    public static int n,m;
    public static int[][] dist;
    public static int[][] map;
    public static int[] dirX = {0,0,1,-1};
    public static int[] dirY = {1,-1,0,0};


    static class Node{
        int x; 
        int y; 
        int cost; 

        Node(int x,int y, int cost){
            this.x = x;
            this.y= y;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // 최소비용 저장배열
        dist = new int[n][m];
        for(int i = 0; i < n; i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        
        // 맵 채우기
        map = new int[n][m];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j) -'0';
            }
        }

        dikjstra();

        bw.write(dist[n-1][m-1] + "\n");
        bw.flush();
        bw.close();
    }

    public static void dikjstra(){
        dist[0][0] = 0;
        // 부순횟수 기준 우선순위큐
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);

        q.offer(new Node(0,0,0));

        while(!q.isEmpty()){
            Node curNode = q.poll();

            // 4방향 이동
            for(int i = 0; i < 4; i++){
                int newX = curNode.x + dirX[i];
                int newY = curNode.y + dirY[i];

                
                // 맵안에 있다면
                if(0 <= newX && newX < n && 0 <= newY && newY < m){
                        // 이동한곳에 저장되어있는 벽부순값이 현재값(벽이 있으면 +1)보다 많으면 변경 큐에 추가
                        if(dist[newX][newY] > curNode.cost + map[curNode.x][curNode.y]){
                            dist[newX][newY] = curNode.cost+map[curNode.x][curNode.y];
                            q.offer(new Node(newX,newY,dist[newX][newY]));
                        }
                }
            }
        }
    }
}

