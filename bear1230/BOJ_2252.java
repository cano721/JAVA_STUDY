import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
        
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] inDegree = new int[n+1];
		ArrayList<Integer>[] list = new ArrayList[n+1];
        
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<m; i++) {
                                    st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			inDegree[b]++;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=1; i<n+1; i++) {
			if(inDegree[i]==0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			System.out.print(q.peek()+" ");		
			int cur = q.poll();
			
			for(int i=0; i<list[cur].size(); i++) {
				int next = list[cur].get(i);
				inDegree[next]--;
				
				if(inDegree[next]==0) {
					q.add(next);
				}
			}
		}
		
	}

}