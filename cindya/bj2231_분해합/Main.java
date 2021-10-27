package cindya.bj2231_분해합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sn = br.readLine();
        int n = Integer.parseInt(sn), m;

        br.close();

        int i = n - (sn.length() * 9); // 시작 지점을 n에서 자릿수 * 9 뺀 수로 지정
        for(i = Math.max(i, 0); i < n; i++){
            m = i;
            for(int j = i, r = j % 10; j > 0; j /= 10, r = j % 10){ // 한 자리씩 더하기
                m += r;
            }
            if(m == n){ // m과 n이 같으면 i 출력
                System.out.println(i);
                return;
            }
        }

        System.out.println(0); // 생성자가 없으면 0 출력
    }
}
