package day2205.day24;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2109_순회강연 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            points[i] = new Point(p, d);
        }

        // 비용을 기준으로 내림차순 정렬하되, 비용이 같으면 날짜를 기준으로 내림차순 정렬.
        Arrays.sort(points, (p1, p2) -> (p1.x == p2.x) ? p2.y - p1.y : p2.x - p1.x);

        int ans = 0;
        boolean[] check = new boolean[10001];
        for (int i = 0; i < N; i++) {
            // 비용에 해당하는 날짜를 D라고 하자.
            // D일부터 1일까지 역순으로 해당 비용을 받는 강연 스케줄이 들어갈
            // 자리가 있는지 확인한다.
            for (int j = points[i].y; j >= 1; j--) {
                if (!check[j]) {
                    check[j] = true;
                    ans += points[i].x;
                    break;
                }
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}