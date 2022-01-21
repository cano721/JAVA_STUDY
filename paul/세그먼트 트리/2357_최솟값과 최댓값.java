import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static int[] arr, minTree, maxTree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1];

        for(int i = 1; i <n+1; i ++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int min = getMinValue(1, n, 1, a, b);
            int max = getMaxValue(1, n, 1, a, b);
            sb.append(min + " " + max + "\n");
        }
        System.out.println(sb);
    }

    
    public static int minInit(int start, int end, int node){
        if(start == end) return minTree[node] = arr[start];

        int mid = (start + end) /2;
        return minTree[node] = Math.min(minInit(start, mid, 2*node), minInit(mid+1, end, 2*node +1));
    }

    public static int getMinValue(int start, int end, int node, int left, int right){
        if(right < start || end < left) return Integer.MAX_VALUE;
        
        if(left <= start && end <= right) return minTree[node];

        int mid = (start + end )/2;
        return Math.min(getMinValue(start, mid, 2*node, left, right), getMinValue(mid+1, end, 2*node+1, left, right));
    }

    public static int maxInit(int start, int end, int node){
        if(start == end) return maxTree[node] = arr[start];

        int mid = (start + end) /2;
        return maxTree[node] = Math.max(maxInit(start, mid, node*2), maxInit(mid+1, end, node*2 +1));
    }

    public static int getMaxValue(int start, int end, int node, int left, int right){
        if(right < start || end < left) return Integer.MIN_VALUE;
        
        if(left <= start && end <= right) return maxTree[node];

        int mid = (start + end )/2;
        return Math.max(getMaxValue(start, mid, 2*node, left, right), getMaxValue(mid+1, end, 2*node+1, left, right));
    }
	
	
}