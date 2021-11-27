package cindya.bj15990_123더하기_5;

import java.io.*;

public class Main {
    private static final int m = 1000000009;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine()), max = 0;
        int[] numbers = new int[t];
        int[][] memory;

        for(int i = 0;  i < t; i++){
            numbers[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, numbers[i]); // 입력받은 수 중 가장 큰 값 구하기
        }
        br.close();
        memory = new int[max + 1][4]; // 가장 큰 값 크기로 배열 생성

        // 각 숫자를 만드는 방법들의 끝나는 숫자를 기준으로 1, 2, 3 세팅
        memory[1][1] = memory[2][2] = memory[3][1] = memory[3][2] = memory[3][3] = 1;

        // 4부터 가장 큰 수까지 루프
        for(int i = 4; i <= max; i++){
            memory[i][1] = (memory[i - 1][2] + memory[i - 1][3]) % m; // 끝에 1을 붙이는 경우
            memory[i][2] = (memory[i - 2][1] + memory[i - 2][3]) % m; // 2를 붙이는 경우
            memory[i][3] = (memory[i - 3][1] + memory[i - 3][2]) % m; // 3을 붙이는 경우
        }

        // 입력받은 순서대로 출력
        for(int n : numbers)
            bw.write(((long)memory[n][1] + memory[n][2] + memory[n][3]) % m + "\n");
        bw.close();
    }
}
