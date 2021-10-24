package cindya.bj2846_오르막길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] pi = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int min = pi[0], max = pi[0]; // min과 max를 pi[0]으로 초기화
        int highest = 0;

        br.close();

        for(int i = 1; i < n; i++){
            if(max < pi[i]) max = pi[i]; // 오르막이면 max에 pi[i]값 대입
            else{ // 내리막이면
                highest = Math.max(highest, max - min); // 더 높은 값 선택
                min = max = pi[i]; // min과 max를 pi[i]으로 초기화
            }
        }
        highest = Math.max(highest, max - min); // 더 높은 값 선택

        System.out.println(highest);
    }
}
