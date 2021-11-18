package baekjoon.silverⅤ;

/*
정렬되어있는 두 배열 A와 B가 주어진다. 두 배열을 합친 다음 정렬해서 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 배열 A의 크기 N, 배열 B의 크기 M이 주어진다. (1 ≤ N, M ≤ 1,000,000)
둘째 줄에는 배열 A의 내용이, 셋째 줄에는 배열 B의 내용이 주어진다. 배열에 들어있는 수는 절댓값이 109보다 작거나 같은 정수이다.

출력
첫째 줄에 두 배열을 합친 후 정렬한 결과를 출력한다.

2 2
3 5
2 9

2 3 5 9

2 1
4 7
1

1 4 7

4 3
2 3 5 9
1 4 7

1 2 3 4 5 7 9

 */


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11728_배열합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] aArr = new int[N];
        int[] bArr = new int[M];
        int[] result = new int[N + M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) aArr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) bArr[j] = Integer.parseInt(st.nextToken());
        Arrays.sort(aArr);
        Arrays.sort(bArr);
        int a = 0, b = 0, c = 0;
        while (a < N && b < M ) {
            if (aArr[a] < bArr[b]) result[c++] = aArr[a++];
            else result[c++] = bArr[b++];
        }
        while (a < N) result[c++] = aArr[a++];
        while (b < M) result[c++] = bArr[b++];
        for (int k = 0; k < result.length; k++) sb.append(result[k] +" ");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }


//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        int[] result = new int[N + M];
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            result[i] = Integer.parseInt(st.nextToken());
//        }
//        st = new StringTokenizer(br.readLine());
//        for (int i = N; i < N+M; i++) {
//            result[i] = Integer.parseInt(st.nextToken());
//        }
//        Arrays.sort(result);
//        for (int i = 0; i < result.length; i++) {
//            sb.append(result[i] +" ");
//        }
//        bw.write(sb.toString());
//        bw.flush();
//        bw.close();
//        br.close();
//    }
}
