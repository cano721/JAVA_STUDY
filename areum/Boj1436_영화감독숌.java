import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1436_영화감독숌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 666; // 가장 작은 666부터 탐색
        int chk = 0;

        while(true) {
            String str = String.valueOf(ans);

            if(str.contains("666")) { // 666을 찾은 경우
                chk++;
                if(chk == N) { // chk 값이 N 과 같아 지면 출력
                    System.out.println(ans);
                    return;
                }
            }
            ans++;
        }
    }
}
