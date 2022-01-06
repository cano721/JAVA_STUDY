package cindya.bj3020_개똥벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
        int[] top = new int[h + 1], bottom = new int[h + 1];
        int min = Integer.MAX_VALUE, cnt = 0;

        for(int i = 0; i < n; i++){
            int x = Integer.parseInt(br.readLine());
            // 석순일 때와 종유석일 때를 구분하여 끝 지점의 수 증가
            if(i % 2 == 0) bottom[x]++;
            else top[x]++;
        }

        // 위에서부터 개수 합치기
        for(int i = h - 1; i > 0; i--){
            top[i] += top[i + 1];
            bottom[i] += bottom[i + 1];
        }

        for(int i = 1; i <= h; i++){
            int v = bottom[i] + top[(h + 1) - i]; // i번째 구간의 석순과 종유석 수
            if(v < min){ // i 구간의 석순과 종유석이 min보다 작으면
                min = v; // min을 v로 대체
                cnt = 1; // cnt를 다시 시작
            }
            else if(v == min) cnt++; // 석순과 종유석의 개수가 min과 같으면 cnt 증가
        }
        System.out.println(String.format("%d %d", min, cnt));
        br.close();
    }
}
