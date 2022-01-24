import java.io.*;
import java.util.*;

/**
 * 각 맵위치에 도둑루피가 존재하고
 * 최소한의 도둑루피를 골라서 끝까지 도달해야함
 * 다익스트라로 풀 예정
 */
public class BJ4485_녹색옷입은애가젤다지 {

  public static int n;
  public static int[][] map;
  public static int[][] dist;
  public static int[] dirX = {0,0,1,-1};
  public static int[] dirY = {1,-1,0,0};

  public static class Node{
      int x;
      int y;
      int cost;
      Node(int x, int y, int cost){
          this.x = x;
          this.y = y;
          this.cost = cost;
      }
  }
  public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int cnt = 0;
      while(true){
          cnt++;
          n = Integer.parseInt(br.readLine());
          if(n == 0) break;

          map = new int[n][n];
          StringTokenizer st;
          for(int i = 0; i < n; i++){
              st = new StringTokenizer(br.readLine());
              for(int j = 0; j < n; j++){
                  map[i][j] = Integer.parseInt(st.nextToken());
              }
          }

          dist = new int[n][n];
          for(int i = 0; i < n; i++){
              Arrays.fill(dist[i],Integer.MAX_VALUE);
          }

          dijkstra();
          bw.write("Problem " + cnt + ": " + dist[n-1][n-1] + "\n");
      }
      bw.flush();
      bw.close();
  }
  
  public static void dijkstra(){
      dist[0][0] = map[0][0];
      PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);

      q.offer(new Node(0,0,map[0][0]));

      while(!q.isEmpty()){
          Node curNode = q.poll();

          if(curNode.cost > dist[curNode.x][curNode.y]) continue;

          for(int idx = 0; idx < 4; idx++){
              int newX = curNode.x + dirX[idx];
              int newY = curNode.y + dirY[idx];
              if(0 <= newX && newX < n && 0 <= newY && newY <n){
                  if(dist[newX][newY] > curNode.cost + map[newX][newY]){
                      dist[newX][newY] = curNode.cost + map[newX][newY];
                      q.offer(new Node(newX,newY,dist[newX][newY]));
                  }
              }
          }
      }
  }

}
