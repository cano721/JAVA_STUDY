import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D20211104_1_BJ_촌수계산 {
	static List<Integer>[] list;
	static boolean[] checked;
	static int result = -1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		checked = new boolean[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<N+1; i++) list[i] = new ArrayList<>();
		
		int one = Integer.parseInt(st.nextToken());
		int two = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}
		
		dfs(one, two, 0);
		System.out.println(result);
	}
	
	static void dfs(int start, int end, int cnt) {
		if(start == end) {
			result = cnt;
			return; 
		}
		checked[start] = true;
		
		for(int i=0; i<list[start].size(); i++) { 
			int next = list[start].get(i);
			if(!checked[next]) dfs(next, end, cnt+1);
		}
	}
}
