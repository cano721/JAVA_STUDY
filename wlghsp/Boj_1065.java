package baekjoon.silverⅣ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
110

99

1

1

1000

144

*/


public class Boj_1065 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine());
        int cnt = arithmetic_sequence(n);

        bw.write(cnt+"\n");
        bw.flush();
        bw.close();        

    }


    static int arithmetic_sequence(int num) {
        int cnt= 0;

        if (num < 100) {
            return num;

        } else {
            cnt = 99;
            if (num == 1000) {
                num = 999;
            }

            for (int i = 100; i <= num; i++) {
                int hun = i/100;  // 100의 자리수
                int ten = (i/10)%10; // 10의 자리수
                int one = i%10; // 1의 자리수 
                if ((hun-ten) == (ten-one)) {
                    cnt++;
                }
            }
            return cnt;
        }
    }
}
