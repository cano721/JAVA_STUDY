package cindya.bj11403_경로찾기;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] route = new int[n][];

        for(int i = 0; i < n; i++)
            route[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        for(int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // i -> j 직행이 없고, k를 경유하는 경로가 있으면 경로가 있음을 표시
                    if (route[i][j] == 0 && route[i][k] == 1 && route[k][j] == 1)
                        route[i][j] = 1;
                }
            }
        }

        // 결과 출력
        for(int[] arr : route){
            for(int i : arr)
                bw.write(i + " ");
            bw.write("\n");
        }
        bw.close();
    }
}
