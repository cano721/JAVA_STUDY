import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static class NumInfo implements Comparable<NumInfo>{
		int number;
		int count;
		public NumInfo(int number, int count) {
			super();
			this.number = number;
			this.count = count;
		}
		@Override
		public String toString() {
			return "NumInfo [number=" + number + ", count=" + count + "]";
		}
		@Override
		public int compareTo(NumInfo o) {
			if(this.count > o.count)
				return 1;
			else if(this.count == o.count) {
				if(this.number > o.number)
					return 1;
			}
			return -1;
		}
		
	}

	static int r, c, k, maxR=3, maxC=3;
	static int map[][], numArr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[100][100];
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayList<NumInfo> list;
		int time = 101;
		while(time --> 0) { // 100부터 1까지 내려오면서 0이되는순간 100초가 지났으므로 -1출력 후 리턴
			if(map[r-1][c-1] == k) {
				System.out.println((100-time));
				return;
			}
			if(time == 0) {
				System.out.println(-1);
				return;
			}
//			System.out.println((101-time)+"초 경과");
			char nowCalc = maxR >= maxC ? 'R' : 'C';
			switch(nowCalc) {
			case 'R':
				int maxColumn = Integer.MIN_VALUE;
				for (int r = 0; r < maxR; r++) {
					list = new ArrayList<>();
					numArr = new int[1000];
					for (int c = 0; c < maxC; c++) {
						int n = map[r][c];
						numArr[n]++;
						
						//현재 숫자가 list에 적재돼있다면 카운트++; 아니면 추가
						if(numArr[n] == 1 && n != 0) {
							list.add(new NumInfo(n, 1));
						} else {
							int size = list.size();
							for (int idx = 0; idx < size; idx++) {
								if(list.get(idx).number == n && n != 0) {
									list.get(idx).count++;
									break;
								}
							}
						}
						map[r][c] = 0;
					}
					Collections.sort(list);
//					System.out.println(list.toString());
					//한 행의 작업이 다 끝났다면 해당 행 변화를 줌
					int size = list.size();
					maxColumn = Math.max(maxColumn, size*2);
					for (int c = 0,idx=0; c < size*2; c=c+2,idx++) {
						map[r][c] = list.get(idx).number;
						map[r][c+1] = list.get(idx).count;
					}
					
					
				}
				maxC = maxColumn;
				break;
			case 'C':
				int maxRow = Integer.MIN_VALUE;
				for (int c = 0; c < maxC; c++) {
					list = new ArrayList<>();
					numArr = new int[1000];
					for (int r = 0; r < maxR; r++) {
						int n = map[r][c];
						numArr[n]++;
						
						//현재 숫자가 list에 적재돼있다면 카운트++; 아니면 추가
						if(numArr[n] == 1 && n != 0) {
							list.add(new NumInfo(n, 1));
						} else {
							int size = list.size();
							for (int idx = 0; idx < size; idx++) {
								if(list.get(idx).number == n && n != 0) {
									list.get(idx).count++;
									break;
								}
							}
						}
						map[r][c] = 0;
					}
					Collections.sort(list);
//					System.out.println(list.toString());
					//한 행의 작업이 다 끝났다면 해당 행 변화를 줌
					int size = list.size();
					maxRow = Math.max(maxRow, size*2);
					for (int r = 0,idx=0; r < size*2; r=r+2,idx++) {
						map[r][c] = list.get(idx).number;
						map[r+1][c] = list.get(idx).count;
					}
					
					
				}
				maxR = maxRow;
				break;
			}
//			for (int r = 0; r < maxR; r++) {
//				for (int c = 0; c < maxC; c++) {
//					System.out.print(map[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
		}
	}

}
