package cindya.bj1003_피보나치함수;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int[] ns = new int[t];
        int max;
        int[][] memory;

        // ns 초기화
        for(int i = 0; i < t; i++)
            ns[i] = Integer.parseInt(br.readLine());
        br.close();

        max = Arrays.stream(ns).max().getAsInt(); // 받은 n 중 가장 큰 값을 구함
        memory = new int[max + 1][2]; // max까지 0과 1의 개수를 저장할 배열을 생성

        // max까지 루프
        for(int i = 0; i <= max; i++){
            if(i < 2){ // i가 0이나 1이면
                memory[i][i] = 1; // i번째를 1로 세팅
            }
            else { // 2 이상이면
                // -1번째와 -2번째 0과 1 개수를 더함
                for (int j = 0; j < 2; j++)
                    memory[i][j] = memory[i - 1][j] + memory[i - 2][j];
            }
        }

        // 출력
        for(int n : ns)
            bw.write(memory[n][0] + " " + memory[n][1] + "\n");
        bw.close();
    }
}
