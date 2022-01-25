import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int tree[];
    static int N, S, A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        S=1;
        while(S<1000000) {
            S*=2;
        }
        tree= new int[S*2];

        N= Integer.parseInt(br.readLine());
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<N; i++) {
            st= new StringTokenizer(br.readLine());
            A= Integer.parseInt(st.nextToken());
            if(A==1) {
                B= Integer.parseInt(st.nextToken());
                //사탕 꺼내기, B번째
                int index= pickup(1, S, 1, B);
                sb.append(index+"\n");
                update(1, S, 1, index, -1);
            }else {
                B= Integer.parseInt(st.nextToken());
                C= Integer.parseInt(st.nextToken());
                update(1, S, 1, B, C);
            }
        }
        System.out.println(sb.toString());
    }

    public static int pickup(int left, int right, int node, int target) {
        if(left==right) return left;

        int mid= (left+right)/2;
        if(tree[node*2]>=target) {
            return pickup(left, mid, node*2, target);
        }else {
            return pickup(mid+1, right, node*2+1, target-tree[node*2]);
        }
    }

    public static void update(int left, int right, int node, int target, int diff) {
        if(target<left||right<target) return;

        tree[node]+=diff;
        if(left!=right) {
            int mid= (left+right)/2;
            update(left, mid, node*2, target, diff);
            update(mid+1, right, node*2+1, target, diff);
        }
    }
}