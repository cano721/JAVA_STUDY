package cindya.bj1476_날짜계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] year = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] check = {1, 1, 1, 1}; // 변경하면서 비교할 숫자들 (차례로 E, S, M, 우리가 쓰는 연도)
        int[] divide = {15, 28, 19, Integer.MAX_VALUE}; // 나눌 숫자
        int diff = year[1] - check[1] - 1; // 범위가 가장 큰 S를 기준으로 check의 S가 year의 S와 같아지기 위해 더할 수를 구함
        int cnt = 0;

        for(int i = 0; i < check.length; i++){
            check[i] = (check[i] + diff) % divide[i] + 1; // 각 수가 0이 되는 것을 방지하기 위해 1을 빼고 계산한 후 나중에 1을 더해줌
            if(i < year.length && check[i] == year[i]) cnt++; // 우리가 쓰는 연도를 제외하고 check와 year이 맞는지 체크
        }

        while(cnt < year.length){ // E, S, M이 모두 같으면 멈춤
            cnt = 0;
            for(int i = 0; i < check.length; i++){
                check[i] = ((check[i] + 27) % divide[i]) + 1; // 연산을 줄이기 위해 가장 큰 범위인 28을 더하며 확인
                if(i < year.length && check[i] == year[i]) cnt++;
            }
        }

        System.out.println(check[3]);

        br.close();
    }
}
