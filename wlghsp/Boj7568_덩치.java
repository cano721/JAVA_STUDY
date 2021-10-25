package baekjoon.silverⅤ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
5
55 185
58 183
88 186
60 175
46 155

2 2 1 2 5

*/

public class Boj7568_덩치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        StringTokenizer st;
        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " "); // br.readLine().split(" ") 도 가능함.
            arr[i][0] = Integer.parseInt(st.nextToken()); // [i][0] : 몸무게
            arr[i][1] = Integer.parseInt(st.nextToken()); // [i][1] : 키
        }

        StringBuilder sb = new StringBuilder(); // StringBuilder 사용하여 출력하기
        // 출력의 반복 횟수가 많아질 수록 StringBuilder 을 통해 출력 문구들을 하나로 묶어 마지막에 한 번에 출력하는게 성능이 월등히
        // 좋아진다.

        for (int i = 0; i < N; i++) {
            int rank = 1; // rank는 1부터 시작

            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue; // 같은 사람은 비교할 필요가 없음
                }

                /*
                 * i 번째 사람과 j번째 사람을 비교하여 i가 j보다 덩치가 작을 경우 rank값을 1 증가시킨다.
                 */
                if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
                    rank++;
                }
            }

            sb.append(rank).append(' '); // 매번 출력하지 않고 StringBuilder로 하나로 만듬.
            // bw.write(rank+ " ");

        }
        bw.write(sb + "\n");

        bw.flush();
        bw.close();
    }
}
