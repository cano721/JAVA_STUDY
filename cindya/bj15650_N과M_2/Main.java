package cindya.bj15650_N과M_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken());

        getCombination(0, n, m, "");
    }

    public static void getCombination(int start, int n, int m, String res){
        if(m == 0){ // m번 다 돈 경우
            System.out.println(res);
            return;
        }

        for(int i = start; i < n; i++){ // 수열이 오름차순이어야하므로 이전 값보다 큰 값 중 선택하여 재귀
            getCombination(i + 1, n, m - 1, res + (i + 1) + " ");
        }
    }
}
