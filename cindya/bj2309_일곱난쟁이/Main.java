package cindya.bj2309_일곱난쟁이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dwarfs = new int[9];
        int total = 0;

        for(int i = 0; i < 9; i++){
            dwarfs[i] = Integer.parseInt(br.readLine());
            total += dwarfs[i];
        }

        loop:
        for(int i = 0; i < 9; i++){
            for(int j = i + 1; j < 9; j++){
                if(total - (dwarfs[i] + dwarfs[j]) == 100){ // 뺐을 때 100이 되는 수 2개를 찾으면
                    dwarfs[i] = 0; // 값을 0으로 세팅
                    dwarfs[j] = 0;
                    break loop; // 루프 멈춤
                }
            }
        }

        Arrays.sort(dwarfs); // 정렬

        for(int i = 2; i < 9; i++) // 0으로 세팅한 값 2개가 맨 앞으로 가기 때문에 2부터 시작
            System.out.println(dwarfs[i]);

        br.close();
    }
}
