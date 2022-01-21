import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long[] arr,tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        tree = new long[4*N];

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(bf.readLine());


        initTree(0,N-1,1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            bw.write(searchTree(0,N-1,1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1)+"\n");
        }

        bw.close();

    }

    public static long initTree(int start, int end, int index){
        if(start == end)
            return  tree[index] = arr[start];
        else{
            int mid = (start+end)/2;
            return tree[index] = Long.min(initTree(start,mid,index*2), initTree(mid+1,end,index*2+1));
        }
    }

    public static long searchTree(int start, int end, int index, int left, int right){
        if(start > right || end < left)
            return Integer.MAX_VALUE;

        if(start>=left && end <= right)
            return tree[index];

        int mid = (start + end)/2;

        return Long.min(searchTree(start,mid,index*2,left,right), searchTree(mid+1,end,index*2+1,left,right));

    }
}