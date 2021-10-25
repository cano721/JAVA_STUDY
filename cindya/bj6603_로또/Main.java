package cindya.bj6603_로또;

import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedWriter bw;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true){
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            if(n == 0) break; // 입력이 0이면 루프 멈춤

            numbers = Arrays.stream(Arrays.copyOfRange(input, 1, input.length)).mapToInt(Integer::parseInt).toArray();

            printLottoNumbers(6, 0, "");
            bw.write("\n"); // 빈칸 한줄 추가
        }
        bw.flush();

        br.close();
        bw.close();
    }

    private static void printLottoNumbers(int cnt, int start, String s) throws IOException{
        if(cnt == 0){ // 6개 모두 뽑은 경우
            bw.write(s + "\n"); // 뽑은 숫자들 출력
            return;
        }

        // 아직 다 뽑지 않은 경우
        for(int i = start; i < numbers.length; i++){
            printLottoNumbers(cnt - 1, i + 1, s + numbers[i] + " ");
        }
    }
}
