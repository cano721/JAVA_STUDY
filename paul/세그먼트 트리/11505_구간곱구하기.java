import java.io.*;
import java.util.StringTokenizer;

public class BJ2042_구간합구하기_펜윅트리 {
    static int n,m,k;
    static long[] arr, tree;
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n+1];
        for(int i = 1; i <n+1; i ++){
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[n*4];
        init(1,n,1);

        
        for(int i = 0; i < m+k; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){
                arr[b] = c;
                update(1, n, 1, b, c);
            }else{
                sb.append(sum(1, n, 1, b, (int)c) + "\n");
            }
        }
        System.out.println(sb);
    }

    public static long init(int start, int end, int idx) {
		if (start == end) {
			return tree[idx] = arr[start];
		}

		int mid = (start + end) / 2;

		return tree[idx] = init(start, mid, idx * 2) * init(mid + 1, end, idx * 2 + 1)% MOD;
	}

	
	public static long sum(int start, int end, int idx, int left, int right) {
		
		if (left > end || right < start) {
			return 1;
		}

		if (left <= start && end <= right) {
			return tree[idx];
		}

		int mid = (start + end) / 2;
		return (sum(start, mid, idx * 2, left, right) * sum(mid + 1, end, idx * 2 + 1, left, right)) % MOD;
	}
	
	public static long update(int start, int end, int node, int idx, long dif) {
		
		if (idx < start || idx > end) {
			return tree[node];
		}

	
		if (start == end) {
			return tree[node] = dif;
		}

		int mid = (start + end) / 2;
		return tree[node] = (update(start, mid, node * 2, idx, val) * update(mid + 1, end, node * 2 + 1, idx, val))
        % MOD;
	}
    
}