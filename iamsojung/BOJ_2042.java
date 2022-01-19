import java.util.*;
import java.io.*;

class Main {
    static long[] input, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        tree=new long[N*4];
        input=new long[N+1];
        for(int i=1;i<=N;i++)
            input[i]=Long.parseLong(br.readLine());
        init(1, 1, N);

        for(int i=0;i<M+K;i++) {
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            if(a==1) {
                int b=Integer.parseInt(st.nextToken());
                long c=Long.parseLong(st.nextToken());
                long diff=c-input[b];
                input[b]=c;
                update(1, 1, N, b, diff);
            }
            else {
                int b=Integer.parseInt(st.nextToken());
                int c=Integer.parseInt(st.nextToken());
                System.out.println(sum(1, 1, N, b, c));
            }
        }

    }

    public static long init(int node, int left, int right) {
        if(left==right) return tree[node]=input[left];
        int mid=(left+right)/2;
        return tree[node]=init(2*node, left, mid)+init(2*node+1, mid+1, right);
    }

    public static void update(int now, int left, int right, int idx, long diff) {
        if(idx<left || idx>right) return;

        tree[now]+=diff;
        if(left!=right) {
            int mid=(left+right)/2;
            update(2*now, left, mid, idx, diff);
            update(2*now+1, mid+1, right, idx, diff);
        }
    }

    public static long sum(int now, int left, int right, int rangeA, int rangeB) {
        if(right<rangeA || left>rangeB) return 0;
        if(rangeA<=left && right<=rangeB) return tree[now];

        int mid=(left+right)/2;
        return sum(2*now, left, mid, rangeA, rangeB)+sum(2*now+1, mid+1, right, rangeA, rangeB);
    }
}