import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1065_한수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(checkNumber(N));
    }
    public static int checkNumber(int num) {
        int cnt; // 한수 수

        if(num < 100) { // 1-99 까지는 모두 등차수열
            return num;
        } else {
            cnt = 99; // 100 이상부터는 한수의 최소 개수는 99

            for(int i = 100; i <= num; i++) {
                int hun = i / 100; // 백의 자리
                int ten = (i / 10) % 10; // 십의 자리
                int one = i % 10; // 일의 자리

                if(hun-ten == ten-one) { // 각 자릿수의 차가 같으면 → 등차수열이라면
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
