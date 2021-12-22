package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n2110_공유기설치 {
	static int n, m;
	static int[] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n];
        for (int i = 0; i < n; i++) {
        	map[i] = Integer.parseInt(br.readLine());
		}
        
        Arrays.sort(map);
        
        int lo = 1;
        int hi = map[n-1] - map[0] + 1;
        while(lo < hi) {
        	int mid = (hi + lo) / 2;
        	
        	if(canInstall(mid) < m) {
        		hi = mid;
        	}
        	else {
        		lo = mid + 1;
        	}
        }
        System.out.println(lo - 1);
	}
	private static int canInstall(int distance) {
		int count = 1;
		int lastLoc = map[0];
		for (int i = 1; i < map.length; i++) {
			int locate = map[i];
			if(locate - lastLoc >= distance) {
				count++;
				lastLoc = locate;
			}
		}
		return count;
	}
	

}
