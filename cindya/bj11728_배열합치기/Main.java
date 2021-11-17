package cindya.bj11728_배열합치기;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int ai = 0, bi = 0;

        // a, b 중 하나를 다 출력할 때까지
        while (ai < n && bi < m) {
            if (a[ai] < b [bi]) { // a가 더 작으면 a를 출력하고 인덱스 증가
                bw.write(a[ai++] + " ");
            } else { // b가 더 작으면 b를 출력하고 인덱스 증가
                bw.write(b[bi++] + " ");
            }
        }

        // 남은 수 출력
        while (ai < n){
            bw.write(a[ai++] + " ");
        }
        while (bi < m){
            bw.write(b[bi++] + " ");
        }

        br.close();
        bw.close();
    }
}
