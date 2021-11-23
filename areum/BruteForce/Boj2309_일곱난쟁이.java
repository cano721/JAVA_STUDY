package BruteForce;

import java.util.Arrays;
import java.util.Scanner;

public class Boj2309_일곱난쟁이 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] high = new int[9];
        int sum = 0;
        int spyA = 0;
        int spyB = 0;

        // 난쟁이 키 입력
        for (int i=0; i<high.length; i++) {
            high[i] = in.nextInt();
            sum += high[i]; // 난쟁이 9명의 합
        }

        // 키를 오름차순으로 정렬
        Arrays.sort(high);

        // 브루트 포스
        for(int i=0; i<high.length-1; i++) {
            for(int j=i+1; j<high.length; j++) {
                if(sum - high[i] - high[j] == 100) {
                    spyA = i;
                    spyB = j;
                }
            }
        }

        // 난쟁이 키 출력
        for(int k=0; k<high.length; k++) {
            if(k==spyA || k==spyB) {
                continue;
            }
            System.out.println(high[k]);
        }
    }
}
