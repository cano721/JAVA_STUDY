package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class d220317_숫자구슬 {
	static int N, M, end, start = 0;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			end += arr[i];
			start = Math.max(start, arr[i]);
		}
        
        //이분탐색
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            int cnt = isPossible(mid);
            if (cnt > M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        //최대값
        bw.write(start + "\n");
        int cnt = 0, sum = 0, i;
        for (i = 1; i <= N; i++) {
            sum += arr[i];
            if (sum > start) {
                M--;
                cnt = (cnt == 0 ? 1 : cnt);
                bw.write(cnt + " ");
                sum = arr[i];
                cnt = 0;
            }
            cnt++;

            // 1개씩 배치할 만한 구슬은 남겨둬야한다.
            if (M == N - i + 1) {
                break;
            }
        }
        for (; i <= N; i++) {
            bw.write(cnt + " ");
            cnt = 1;
        }
        bw.flush();
    }
	private static int isPossible(int mid) {
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
