package cindya.bj9251_LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(""), b = br.readLine().split("");
        int[][] count = new int[a.length + 1][b.length + 1];
        int max = 0;

        for(int i = 1; i <= a.length; i++){ // a를 count의 행 헤더로 간주
            for(int j = 1; j <= b.length; j++){ // b를 count의 열 헤더로 간주
                if(a[i - 1].equals(b[j - 1])) {  // 만약 a와 b가 같다면
                    count[i][j] = count[i - 1][j - 1] + 1; // 이전 행/열에 1을 더한 값을 저장
                    max = Math.max(max, count[i][j]); // 기존의 max와 지금 갱신한 값 중 큰 값을 취함
                }
                else {
                    count[i][j] = Math.max(count[i][j - 1], count[i - 1][j]); // 이전 행/열 값을 그대로 저장
                }
            }
        }

        System.out.println(max); // 가장 큰 값을 출력

        br.close();
    }
}