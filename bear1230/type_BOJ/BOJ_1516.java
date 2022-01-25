import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
		int n = Integer.parseInt(br.readLine());
		int inDegree[] = new int[n+1];
		int time[] = new int[n+1];
		ArrayList<Integer> list[] = new ArrayList[n+1];
		int result[] = new int[n+1];
        
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            time[i] = tmp;
			while(st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				if(num == -1) break;
                list[num].add(i);
                inDegree[i]++; 
            }
        }
		
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=1; i<=n; i++) {
			if(inDegree[i] == 0) {
				q.add(i);
                result[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int node = q.poll();
			for(int next :list[node]) {
				inDegree[next]--;
				result[next] = Math.max(result[next], result[node] + time[next]);
				if(inDegree[next] == 0) {
					q.add(next);
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			System.out.println(result[i]);
		}
	}
}