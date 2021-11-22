package baekjoon;

/*


성원이는 다이어트를 시도중이다. 성원이는 정말 정말 무겁기 때문에, 저울이 부셔졌다. 
성원이의 힘겨운 다이어트 시도를 보고만 있던 엔토피아는 성원이에게 새로운 저울을 선물해 주었다. 
성원이는 엔토피아가 선물해준 저울 위에 올라갔다. 
“안돼!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! G 킬로그램이나 더 쪘어ㅜㅠ”라고 성원이가 말했다. 
여기서 말하는 G킬로그램은 성원이의 현재 몸무게의 제곱에서 성원이가 기억하고 있던 몸무게의 제곱을 뺀 것이다.

성원이의 현재 몸무게로 가능한 것을 모두 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 G가 주어진다. G는 100,000보다 작거나 같은 자연수이다.

출력
첫째 줄부터 한 줄에 하나씩 가능한 성원이의 현재 몸무게를 오름차순으로 출력한다. 
가능한 몸무게가 없을 때는 -1을 출력한다. 현재 몸무게는 자연수로 떨어지지 않을 수도 있는데, 이런 경우는 제외해야 한다.

15

4
8

*/



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Boj1484_다이어트 {
    static final int MAX = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int a = 1; // 기억 몸무게
        int b = 2; // 현재 몸무게
        
        long[] powArr = new long[MAX + 1];
        for (int i = 0; i < powArr.length; i++) {
            powArr[i] = i * i;
        }

        List<Integer> result = new ArrayList<>();

        while (true) {
            long diff = (long) (Math.pow(b, 2)) - (long) Math.pow(a, 2);
            if ((b - a == 1) && (diff > 100000)) break;

            if (diff < G) {
                b++;
            }
            if (diff > G) {
                a++;
            }
            if (diff == G) {
                result.add(b);
                b++;
            }
        }
        Collections.sort(result);
        if (result.isEmpty() || G == 1) {
            System.out.println(-1);
        } else {
            for (int i : result) {
                System.out.println(i);
            }
        }



    }
}
