package cindya.bj2470_두용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] solutions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int min = Integer.MAX_VALUE, row = 0, high = n - 1;
        int[] minSolutions = new int[2];

        // row가 high와 같아지면 루프 종료
        while (row < high){
            // 두 용액의 합이 min보다 0에 가까우면 대체
            if(Math.abs(solutions[row] + solutions[high]) < min){
                min = Math.abs(solutions[row] + solutions[high]);
                minSolutions[0] = solutions[row];
                minSolutions[1] = solutions[high];
            }
            // row번째 용액이 high번째 용액보다 특성값이 크면 row 증가
            if(Math.abs(solutions[row]) > Math.abs(solutions[high]))
                row++;
            else // 아니면 high 감소
                high--;
        }

        System.out.println(minSolutions[0] + " " + minSolutions[1]);

        br.close();
    }
}