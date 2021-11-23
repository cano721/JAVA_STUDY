package 투포인터;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
/*
    병합정렬
 */
public class Boj11728_배열합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // A 크기
        int M = Integer.parseInt(st.nextToken()); // B 크기
        int[] a = new int[N];
        int[] b = new int[M];

        // 배열 입력
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) { // A배열 넣기
            a[n] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int m=0; m<M; m++) { // B배열 넣기
            b[m] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(a);
        Arrays.sort(b);

        // 합치기
        int aIdx = 0;
        int bIdx = 0;
        while(aIdx<N && bIdx<M) {
            if(a[aIdx] < b[bIdx]) { // 배열 A의 값이 더 작을 때
                sb.append(a[aIdx++] + " "); // 배열 A 값 출력 후 idx++
            } else { // 배열 B의 값이 더 작을 때
                sb.append(b[bIdx++] + " "); // 배열 B 값 출력 후 idx++
            }
        }

        // 한 배열이 먼저 끝난 경우
        // 배열 A의 값이 남아있을 때
        while(aIdx < N) {
            sb.append(a[aIdx++] + " ");
        }
        // 배열 B의 값이 남아있을 때
        while(bIdx < M) {
            sb.append(b[bIdx++] + " ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

        /* 시간이 오래걸리는 코드,,,
        int[] ab = new int[N+M]; // 두 배열을 넣기위한 배열
        int i = 0;
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) { // A배열 넣기
            ab[i++] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int m=0; m<M; m++) { // B배열 넣기
            ab[i++] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ab); // 정렬하기
        for(int n : ab) {
            sb.append(n + " ");
        }
        */
    }
}
