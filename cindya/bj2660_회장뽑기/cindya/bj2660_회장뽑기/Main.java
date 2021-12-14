package cindya.bj2660_회장뽑기;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()), min = 50, cnt = 0;
        int[][] score = new int[n][n];

        for(int i = 0; i < n; i++) {
            Arrays.fill(score[i], min); // 점수를 50점을 세팅
            score[i][i] = 0; // 자기자신은 0으로 세팅
        }

        // 친구 정보 입력받기
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(st.nextToken(" ")), f2 = Integer.parseInt(st.nextToken());
            if(f1 == -1 && f2 == -1) break;
            score[--f1][--f2] = 1;
            score[f2][f1] = 1;
        }

        for(int k = 0; k < n; k++){ // 경유
            for(int i = 0; i < n; i++){ // 친구 1
                if(k == i) continue; // 같은 사람인 경우 넘어감
                for(int j = 0; j < n; j++){ // 친구 2
                    if(k == j || i == j) continue; // 같은 사람인 경우 넘어감
                    if(score[i][j] > score[i][k] + score[k][j]) // 바로는 친구가 아니지만 건너서 친구라면
                        score[i][j] = score[i][k] + score[k][j]; // 점수 갱신
                }
                min = Math.min(min, Arrays.stream(score[i]).max().getAsInt()); // i와 가장 먼 친구와의 거리가 min보다 적으면 갱신
            }
        }

        String candidates = "";

        for(int i = 0; i < n; i++)
            if(min == Arrays.stream(score[i]).max().getAsInt()) { // i의 가장 높은 점수가 min이면
                cnt++; // 카운트 증가
                candidates += (i + 1) + " "; // 후보자 이름으로 추가
            }

        // 출력
        bw.write(String.format("%d %d\n%s", min, cnt, candidates));

        br.close();
        bw.close();
    }
}