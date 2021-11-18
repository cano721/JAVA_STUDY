import java.util.*;
import java.io.*;

public class Main {
    static int n,d,k,c;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int arr[] = new int[n];
        int vis[] = new int[d+1];

        for (int i=0; i<n; i++)
        arr[i] = Integer.parseInt(br.readLine());

        int cnt = 0;
        
        for (int i=0; i<k; i++) 
            if (++vis[arr[i]]==1) cnt++;

        int ans = cnt;
        
        for (int i=1; i<n; i++) {
            if (--vis[arr[i-1]]==0) cnt--;
            if (++vis[arr[(i+k-1)%n]]==1) cnt++;

            if (ans<=cnt)
                ans = vis[c]==0? cnt+1: cnt;
        }
        System.out.println(ans);
    }
}