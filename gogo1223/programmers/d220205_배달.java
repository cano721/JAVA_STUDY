package programmers;

import java.util.ArrayList;
import java.util.HashMap;


class RoadInfo{
	private int a;
	private int b;
	private int cost;
	
	public RoadInfo(int a, int b, int cost) {
		this.a = a;
		this.b = b;
		this.cost = cost;
	}
	
}
public class d220205_배달 {
	
	static final int INF = 1000000;
	
	public static void main(String[] args) {
		//int N = 5;
		//int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
		//int K = 3;
		int N = 6;
		int[][] road = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
		int K = 4;
		int answer = solution(N, road, K);
		System.out.println(answer);

	}
	static int solution(int N, int[][] road, int K) {
        int answer = 0;
        //roadMap 초기화
        int[][] roadMap = new int[N][N];
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j) continue;
				roadMap[i][j] = INF;
			}
        }
        //roadMap 생성
        for (int i = 0; i < road.length; i++) {
        	//같은 출발/도착지 다른 경로가 존재한다.
        	if(roadMap[road[i][0]-1][road[i][1]-1] != 0) {
        		int temp = roadMap[road[i][0]-1][road[i][1]-1];
        		roadMap[road[i][0]-1][road[i][1]-1] = Math.min(temp, road[i][2]);
        		roadMap[road[i][1]-1][road[i][0]-1] = Math.min(temp, road[i][2]);
        		continue;
        	}
			roadMap[road[i][0]-1][road[i][1]-1] = road[i][2];
			roadMap[road[i][1]-1][road[i][0]-1] = road[i][2];
		}
        //최소거리, 방문 초기화
        int[] dist = new int[N];
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
			dist[i] = roadMap[0][i];
		}
        //시작점 방문
        visited[0] = true;
        //다익스트라 알고리즘 적용
        for (int i = 0; i < N; i++) {
        	//방문하지 않은 노드 중 가장 적은 비용
            int min = INF;
            int index = 0;
            for (int j = 0; j < N; j++) {
    			if(dist[j] < min && !visited[j]) {
    				min = dist[j];
    				index = j;
    			}
    		}
            //방문 체크
            visited[index] = true; 
            //비용 적은 길로 최소비용 변환
            for (int j = 0; j < N; j++) {
				if(!visited[j] && dist[index] + roadMap[index][j] < dist[j]) {
					dist[j] = dist[index] + roadMap[index][j];
				}
			}
		}
        for (int i = 0; i < N; i++) {
			if(dist[i] <= K) {
				answer++;
			}
		}
        
        return answer;
    }
}
