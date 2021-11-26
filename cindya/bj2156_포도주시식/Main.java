package cindya.bj2156_포도주시식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wines = new int[n], max = new int[n];
        for(int i = 0; i < n; i++)
            wines[i] = Integer.parseInt(br.readLine());

        max[0] = wines[0];
        if(n >= 2) // 잔 개수가 2 이상일 때
            max[1] = max[0] + wines[1]; // 첫번째  + 두번째
        if(n >= 3) { // 3 이상일 때
            max[2] = Math.max(wines[1] + wines[2], wines[0] + wines[2]); // 두번째 + 세번째 vs 첫번째 + 세번째
            max[2] = Math.max(max[2], max[1]); // 두번째 max vs 세번째 max
        }

        for(int i = 3; i < n; i++){
            max[i] = Math.max(max[i - 2] + wines[i], max[i - 3] + wines[i - 1] + wines[i]); // 이전 잔 빼고 vs 두번째 이전 잔 빼고
            max[i] = Math.max(max[i], max[i - 1]); // i번째 잔을 포함시킬지 말지
        }

        System.out.println(max[n - 1]);
        br.close();
    }
}
