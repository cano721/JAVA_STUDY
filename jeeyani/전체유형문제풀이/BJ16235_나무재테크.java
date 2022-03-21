package 전체유형문제풀이;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 * https://www.youtube.com/watch?v=lfA1iSUw34E
 * */

public class BJ16235_나무재테크 {

	static int n, m, k = 0;
    static int[][] A = new int[10][10]; 
    static int[][] temp = new int[10][10]; //현재 양분을 저장할 배열
    
    static List<tree> treeList = new LinkedList<>();
    
    static int[] dr = {-1, -1, -1, 0,0,1,1,1};
    static int[] dc = {-1, 0, 1,-1,1,-1,0,1};
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				
				//가장 처음 양분은 모든 칸에 5만큼
				temp[i][j] = 5; 
			}
		}
		
		int x=0;
		int y=0;
		int z=0;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			
			treeList.add(new tree(x-1, y-1, z, true));
		}
		
		int ans = getAliveTree();
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		
	}
	
	public static int getAliveTree() {
		
		for (int i = 0; i < k; i++) {
			//1.봄
			for (tree t : treeList) {
				
				if(t.age <= temp[t.r][t.c]) {
					//나이만큼 양분먹고 나이가 1씩 증가
					temp[t.r][t.c] -= t.age;
					t.age++;
				}
				// 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 경우
				else {
					t.alive = false;
				}
				
			}
			
			//2.여름
			for(Iterator<tree> tt = treeList.iterator(); tt.hasNext();) {
				tree t = tt.next();
				//봄에 죽은 나무를 양분으로 바꾸기
				if(!t.alive) {
					temp[t.r][t.c] += t.age/2;
					tt.remove();
				}
			}
			
			//3.가을
			List<tree> tempTree = new LinkedList<BJ16235_나무재테크.tree>();
			for(tree t : treeList) {
				//번식하는 나무는 나이가 5의 배수
				if(t.age % 5 == 0) {
					//인접한 8개의 칸에 나이가 1인 나무가 생김
					for (int j = 0; j < 8; j++) {
						int nr = t.r+dr[j];
						int nc = t.c+dc[j];
						
						if(nr < 0 || nr > n-1 || nc <0 || nc >n-1) continue; //범위 벗어나는 경우 무시
						
						tempTree.add(new tree(nr,nc,1,true));
						
					}
				}
			}
			
			//나이가 적은 나무부터 처리하기
			//새로운 나무는 나이가 1임으로 제일 적음 따라서 새로운 나무를 맨 앞에 담기
			treeList.addAll(0, tempTree);
			
			//4.겨울
			//각 칸에 A[r][c]만큼의 양분을 추가하기
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					temp[j][k] += A[j][k];
				}
			}
			
			
		}
		
		//리스트에 남아있는 나무의 갯수 리턴
		return treeList.size();
	}

	public static class tree{
		int r;
		int c;
		int age;
		boolean alive;
		
		public tree(int r, int c, int age, boolean alive) {
			this.r = r;
			this.c = c;
			this.age = age;
			this.alive = alive;
		}
		
	}

}
