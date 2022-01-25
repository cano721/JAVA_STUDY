import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 100000;
    static int offset = 1048576;

    static int[] tree = new int[2*offset];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        for (int i=0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) {
                int m = search(b);
                System.out.println(m);
                update(m, -1);
            }
            else {
                int c = Integer.parseInt(st.nextToken());
                update(b, c);
            }
        }
    }

    static void update(int idx, int cnt) {
        idx += offset;

        while (idx > 0) {
            tree[idx] += cnt;
            idx /= 2;
        }
    }

    static int search(int fidx) {
        int idx = 1, left, right;

        while (idx < offset) {
            left = idx*2; right = left+1;
            if (tree[left] >= fidx) idx = left;
            else {
                fidx -=tree[left]; idx = right;
            }
        }
        return idx - offset;
    }
}