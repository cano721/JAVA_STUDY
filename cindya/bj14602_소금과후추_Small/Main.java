package cindya.bj14602_소금과후추_Small;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken(" ")), n = Integer.parseInt(st.nextToken(" ")),
                k = Integer.parseInt(st.nextToken(" ")), w = Integer.parseInt(st.nextToken());
        int[][] a = new int[m][];
        List<Integer> check = new ArrayList<>();
        int middle = ((w / 2) * w) + (w / 2); // w * w를 1차원 배열로 저장했을 떄의 중간 지점

        // a 초기화
        for(int i = 0; i < m; i++)
            a[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        for(int i = 0; i < m - (w - 1); i++){ // b의 행만큼 루프
            for(int j = 0; j < n - (w - 1); j++){ // b의 열만큼 루프
                check.clear(); // check 내용 초기화
                // w만큼 행, 열 모두 루프
                for(int x = 0; x < w; x++){
                    for(int y = 0; y < w; y++){
                        check.add(a[i + x][j + y]); // i, j 기준으로 w을 더한 범위의 값을 모두 저장
                    }
                }
                Collections.sort(check); // check 정렬
                bw.write(check.get(middle) + " "); // 중앙값 출력
            }
            bw.write("\n"); // 행이 바뀔 때 줄바꿈
        }
        bw.close();
    }
}
