package 투포인터;

import java.io.*;
import java.util.*;

/*
     cindya님 참고
    → 함수 부분
 */
public class Boj14602_소금과후추 {
    static int M, N, K, W, midIdx;
    static int[][] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        midIdx = (W*W)/2; // 중앙값 설정
        arr = new int[M][N];

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            for(int n = 0; n<N; n++){
                arr[m][n] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<M-W+1; i++){ // 1 ≤ i ≤ M-W+1
            for(int j=0; j<N-W+1; j++){ // 1 ≤ j ≤ N-W+1
                median(i,j);
            }
            bw.write("\n");
        }

        // 출력 후 종료
        bw.flush();
        bw.close();
    }

    public static void median(int x, int y) throws IOException {
        // 중앙값 출력 리스트
        List<Integer> list = new ArrayList<>();

        // B[i][j] = median(A[i+x][j+y])
        for(int i=0; i<W; i++){
            for(int j=0; j<W; j++){
                list.add(arr[i+x][j+y]);
            }
        }

        // 정렬
        Collections.sort(list);

        // 중앙값 출력
        bw.write(list.get(midIdx)+" ");
    }
}
