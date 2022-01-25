import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int arr1[] = new int[n];
        int arr2[] = new int[m];

        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        int sn = 0, sm = 0;
        while (true) {
            if (sn == n && sm == m) {
                break;
            } else if (sn == n) {
                for (int i = sm; i < m; i++) {
                    sb.append(arr2[i] + " ");
                }
                break;
            } else if (sm == m) {
                for (int i = sn; i < n; i++) {
                    sb.append(arr1[i] + " ");
                }
                break;
            } else {
                if (arr1[sn] < arr2[sm]) {
                    sb.append(arr1[sn] + " ");
                    sn++;
                } else {
                    sb.append(arr2[sm] + " ");
                    sm++;
                }
            }

        }
        System.out.println(sb);
    }
}