package BOJ;

import java.io.*;
import java.util.*;

public class Main {

	static int cnt = 0;
	static int max = 0;
	static int max2 = 0;
	
	static int[] drow = {0, -1, 0, 1};
	static int[] dcol = {-1, 0, 1, 0};
	static int[][] map;
	static int N, M;
	
	static boolean[][] visited;
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < M; j++) {
        		if(!visited[i][j]) {
        			BFS(i, j);
        		}
        	}
        }
        
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < M; j++) {
        		for(int k = 0; k < 4; k++) {
        			int temp = (int) Math.pow(2, k);
        			if((map[i][j]&temp) == temp) {
        				int CP = map[i][j];
        				map[i][j] &= ~(1<<k);
        				BFS2(i, j, new boolean[N][M]);
        				map[i][j] = CP;
        			}
        		}
        	}
        }
        
        
        
        
        System.out.println(cnt);
        System.out.println(max);
        System.out.println(max2);
        
      
        
        
    }
    static void BFS(int row, int col) {
    	cnt++;
    	Queue<Node> q = new LinkedList<>();
    	q.add(new Node(row, col));
    	visited[row][col] = true;
    	int maxT = 0;
    	
    	while(!q.isEmpty()) {
    		Node now = q.poll();
    		
    		maxT++;
    		
    		for(int i = 0; i < 4; i++) {
    			int tR = now.row + drow[i];
    			int tC = now.col + dcol[i];
    			

    			
    			if(tR < 0 || N <= tR || tC < 0 || M <= tC || visited[tR][tC] || (map[now.row][now.col]&(int)Math.pow(2, i)) == (int)Math.pow(2, i)) continue;
    			
    			
    			
    			q.add(new Node(tR, tC));
    			visited[tR][tC] = true;
    		}
    	}
    	
    	max = Math.max(max, maxT);
    	
    }
    static void BFS2(int row, int col, boolean[][] visited2) {

    	Queue<Node> q = new LinkedList<>();
    	q.add(new Node(row, col));
    	visited2[row][col] = true;
    	int maxT = 0;
    	
    	while(!q.isEmpty()) {
    		Node now = q.poll();
    		
    		maxT++;
    		
    		for(int i = 0; i < 4; i++) {
    			int tR = now.row + drow[i];
    			int tC = now.col + dcol[i];
    			

    			
    			if(tR < 0 || N <= tR || tC < 0 || M <= tC || visited2[tR][tC] || (map[now.row][now.col]&(int)Math.pow(2, i)) == (int)Math.pow(2, i)) continue;
    			
    			
    			
    			q.add(new Node(tR, tC));
    			visited2[tR][tC] = true;
    		}
    	}
    	
    	if(max2 < maxT) {
    		max2 = maxT;
    	}
    }
    
    static class Node{
    	int row;
    	int col;
    	
    	public Node(int r, int c) {
    		this.row = r;
    		this.col = c;	
    	}
    }
}