package cindya.bj15988_123더하기_3;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine()), max = 0;
        int[] numbers = new int[t], memory;

        for(int i = 0; i < t; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, numbers[i]);
        }
        memory = new int[max]; // 가장 큰 값만큼의 크기로 배열 생성
        memory[0] = 1; // 1을 만드는 경우의 수 1
        memory[1] = 2; // 2를 만드는 경우의 수 2
        memory[2] = 4; // 3을 만드는 경우의 수 4

        for(int i = 3; i < max; i++) {
            long sum = 0;
            for (int j = 1; j <= 3; j++)
                sum += memory[i - j]; // i - 3까지 더하기
            memory[i] = (int)(sum % 1000000009); // 결과를 10000000009로 나눈 나머지를 memory[i]에 저장
        }

        // 입력받은 순으로 출력
        for(int n : numbers)
            bw.write(memory[n - 1] + "\n");

        br.close();
        bw.close();
    }
}
