import java.util.*;
import java.io.*;

/*
유니온파인드 - 친구 네트워크
*/
public class Main {

	static int t, f;
	static int[] parent, count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < t; i++) {
			f = Integer.parseInt(br.readLine());
			parent = new int[f*2+1];
			count = new int[f*2+1];
			HashMap<String, Integer> friend = new HashMap<>();
			
			for (int j = 0; j < parent.length; j++) {
				parent[j] = j;
				count[j] = 1;
			}
			
			int index = 1;
			for (int k = 0; k < f; k++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!friend.containsKey(a)) {
					friend.put(a, index++);
				}
				if(!friend.containsKey(b)) {
					friend.put(b, index++);
				}
                
                System.out.println(union(friend.get(a), friend.get(b)));
			}
		}

	}
	
	static int union(int a, int b) {
		a = find(a);
        b = find(b);

        if(a != b) {
			parent[b] = a;
			count[a] += count[b]; 
		}
		
		return count[a];
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
	}
}
