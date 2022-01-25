import java.io.*;
import java.util.*;

public class Main {
	private static ArrayList<Node>[] list;
	private static boolean visit[];
    static int n,m;
	static int start;
	static int end;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list =  new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		int max = 0;
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			max = Math.max(max,c);
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));			
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		int low = 0;
		int high = max+1;
		int mid;
		int ans = 0;
        
		while(low <= high) {
			mid = (low + high) / 2;
			if (bfs(mid)) {
				ans = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.println(ans);
		
		
	}
    
    
    static class Node {		
        int from;
        int to;		
        public Node(int from, int to) {		
            this.from = from;		
            this.to = to;	
        }
    }

	public static boolean bfs(int mid) {
		Queue<Integer> que = new LinkedList<Integer>();
        visit = new boolean[n + 1];
        
		que.add(start);
		visit[start] = true;
        
		while(!que.isEmpty()) {
			int tmp = que.poll();
            
			if (tmp == end) return true;
			for(Node node : list[tmp]) {
				if(!visit[node.from] && node.to >= mid) {
					visit[node.from] = true;
                    que.offer(node.from);
				}				
			}			
		}		
		return false;		
	}
}
