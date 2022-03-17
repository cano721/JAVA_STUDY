import java.io.*;
import java.util.*;

public class Main {

    static int N, M, right, left;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
            left = Math.max(left, arr[i]);
        }

        

        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            int cnt = isPossible(mid);
            if (cnt > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        sb.append(left + "\n");
        int cnt = 0, sum = 0, i;
        for (i = 1; i <= N; i++) {
            sum += arr[i];
            if (sum > left) {
                M--;
                cnt = (cnt == 0 ? 1 : cnt);
                sb.append(cnt + " ");
                sum = arr[i];
                cnt = 0;
            }
            cnt++;

            if (M == N - i + 1) {
                break;
            }
        }
        for (; i <= N; i++) {
            sb.append(cnt + " ");
            cnt = 1;
        }
        
        System.out.println(sb);
    }

    static int isPossible(int mid) {
        int start = 0, cnt = 1;
        for (int i = 1; i <= N; i++) {
            start += arr[i];
            if (start > mid) {
                start = arr[i];
                cnt++;
            }
        }

        return cnt;
    }
}