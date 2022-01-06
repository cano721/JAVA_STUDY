package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 개수
        int H = Integer.parseInt(st.nextToken()); // 높이
        int[] top = new int[H + 1]; //종유석 저장
        int[] bottom = new int[H + 1]; //석순 저장

        for (int i = 0; i < N / 2; i++) {
            top[Integer.parseInt(br.readLine())]++; //종유석일 때 입력값 (높이) 개수 증가
            bottom[Integer.parseInt(br.readLine())]++; //석순일 때 입력값 (높이) 개수 증가
        }
        for (int j = 1; j <= H; j++) { //누적합 구하기
            bottom[j] = bottom[j] + bottom[j - 1];
            top[j] = top[j] + top[j - 1];
        }
        int min = N;
        int count = 0;
        for (int i = 1; i <= H; i++) { //높이가 1일때부터 총 합의 최소값 구해준다.
            int total = 0;

            total += bottom[H] - bottom[i - 1];
            total += top[H] - top[H - i];

            if (min > total) { //최소값 갱신 - 최소값 보장되는 이유 : 당연히 그동안 갱신될 최소값이 나온적이 없었으니까!
                min = total;
                count = 1;
            } else if (min == total) {
                count++;

            }
        }
        System.out.println(min + " " + count);

    }

}

