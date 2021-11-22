package cindya.bj1484_다이어트;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int g = Integer.parseInt(br.readLine());
        br.close();
        int now = (int)Math.ceil(Math.sqrt(g)), before = 1; // 현재 몸무게를 g의 제곱근을 올림한 값으로 설정
        boolean isPossible = false;

        while(now > before){
            Integer gap = (int)(Math.pow(now, 2) - Math.pow(before, 2)); // 현재 몸무게와 이전 몸무게 각각 제곱의 차
            // gap과 g를 비교한 결과가
            switch (gap.compareTo(g)){
                case 0: // 같으면
                    isPossible = true;
                    bw.write(now + "\n"); // 현재 몸무게 출력
                case -1: // 같거나 gap이 작으면
                    now++; // 현재 몸무게 증가
                    break;
                case 1: // gap이 더 크면
                    before++; // 이전 몸무게 증가
                    break;
            }
        }

        if(!isPossible) bw.write("-1"); // 불가능하면 -1 출력

        bw.close();
    }
}
