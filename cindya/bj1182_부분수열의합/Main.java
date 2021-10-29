package cindya.bj1182_부분수열의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = br.readLine().split(" ");
        int n = Integer.parseInt(numbers[0]), s = Integer.parseInt(numbers[1]);
        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int res = getPartialsNum(s, 0, 0);

        System.out.println(res);

        br.close();
    }

    private static int getPartialsNum(int s, int sum, int start){
        int cnt = 0;
        for(int i = start; i < sequence.length; i++){ // start번째 원소부터 마지막 원소까지
            if(sum + sequence[i] == s) cnt++; // 합이 s와 같으면 증가
            cnt += getPartialsNum(s, sum + sequence[i], i + 1); // i + 1부터 더하도록 호출
        }
        return cnt;
    }
}
