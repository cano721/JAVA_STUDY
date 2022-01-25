import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int start = 0, end = 0, ans = Integer.MAX_VALUE;
        while (end < n && start <= end) {
            int cur = arr[end] - arr[start];
            if (cur >= m && cur < ans ) {
                ans = cur;
            }
            if (cur >= m) {
                start++;
            } else {
                end++;
            }
        }
        bw.write(ans + "");
        br.close();
        bw.close();
    }
}