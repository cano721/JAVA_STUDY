import java.io.*;
import java.util.*;

/*
유니온 파인드 - 집합의 표현 
*/

public class Main{
	
	static int[] parent;
		
	public static void main(String[] args) throws Exception{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		
		for(int i = 0; i <= n; i++){
            parent[i] = i;            
        }
					
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(c == 0)
				union(a, b);
			else {
				if(find(a) == find(b))
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}		
	}
    
    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a == b) return false;
        parent[b] = a;
        
        return true;
	}
	
	static int find(int a) {
		
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
	}
}