import java.util.*;
import java.io.*;

class Main {
    static int[] input;
    static long[] tree;
    static int MOD;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        input=new int[n+1];
        tree=new long[n*4];
        MOD=1000000007;
        for(int i=1;i<=n;i++) {
            st=new StringTokenizer(br.readLine());
            input[i]=Integer.parseInt(st.nextToken());
        }

        init(1, 1, n);

        for(int i=0;i<m+k;i++) {
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            if(a==1) {
                input[b]=c;
                update(1, 1, n, b,  c);
            }
            else {
                System.out.println(sum(1, 1, n, b, c));
            }
        }
    }

    public static long init(int node, int left, int right) {
        if(left==right) return tree[node]=input[left];

        int mid=(left+right)/2;
        return tree[node]=init(node*2, left, mid)*init(node*2+1, mid+1, right)%MOD;
    }

    public static long update(int node, int left, int right, int idx, int c) {
        if(idx<left || idx>right) return tree[node];
        if(left==right) return tree[node]=c;

        int mid=(left+right)/2;
        return tree[node]=update(node*2, left, mid, idx, c)*update(node*2+1, mid+1, right, idx, c)%MOD;
    }

    public static long sum(int node, int left, int right, int rangeA, int rangeB) {
        if(right<rangeA || left>rangeB) return 1;
        if(rangeA<=left && right<=rangeB) return tree[node];

        int mid=(left+right)/2;
        return sum(node*2, left, mid, rangeA, rangeB)*sum(node*2+1, mid+1, right, rangeA, rangeB)%MOD;

    }

}