import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static Point tree[];
    static int arr[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        tree = new Point[N*4];
        arr = new int[N+1];
        for(int i = 1 ; i < N+1 ; i++)
            arr[i] = Integer.parseInt(br.readLine());
        init(1,N,1);
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            Point getMinMax = find(1,N,l,r,1);
            bw.write(getMinMax.y+" "+getMinMax.x);
            bw.newLine();
        }
        bw.close();
    }

    static Point init(int s,int e,int idx) {
        if(s==e) return tree[idx] = new Point(arr[s],arr[e]);
        int mid = (s+e)/2;
        Point left = init(s,mid,idx*2);
        Point right = init(mid+1,e,idx*2+1);
        return tree[idx] = new Point(Math.max(left.x, right.x),Math.min(left.y, right.y));
    }
    static Point find(int s, int e , int l, int r, int idx) {
        if(e<l || s>r) return new Point(Integer.MIN_VALUE,Integer.MAX_VALUE);
        if(l<=s && e <= r) return tree[idx];
        int mid = (s+e)/2;
        Point left = find(s, mid, l, r, idx*2);
        Point right = find(mid+1, e, l, r, idx*2+1);
        return new Point(Math.max(left.x, right.x),Math.min(left.y, right.y));
    }
}