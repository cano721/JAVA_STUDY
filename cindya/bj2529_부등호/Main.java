package cindya.bj2529_부등호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 주석 코드 부분은 List에 모든 값 저장 후 정렬하여 푸는 방법
// 백준 실행 결과 list 사용 방식보다 min/max 방식이 시간과 메모리 모두 적게 듦

public class Main {
    private static boolean[] used = new boolean[10];
    //private static List<String> numbers = new ArrayList<>();
    private static String min = "9999999999", max = ""; // min은 가장 큰 10자리 수, max는 빈 칸 (사전 순 비교이기 때문)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] signs = br.readLine().split(" ");
        Arrays.fill(used, false);

        for(int i = 0; i < used.length; i++){ // 첫번째 수를 0 ~ 9까지 하나씩 넣어서 함수 호출
            used[i] = true;
            getNumbers(signs, 0, String.valueOf(i), i);
            used[i] = false;
        }

//        Collections.sort(numbers);
//
//        System.out.println(numbers.get(numbers.size() - 1));
//        System.out.println(numbers.get(0));

        System.out.println(max);
        System.out.println(min);

        br.close();
    }

    private static void getNumbers(String[] signs, int idx, String number, int lastNum){
        if(idx == signs.length){ // 마지막 부등호까지 확인한 경우
            //numbers.add(number);
            // number을 각각 min, max와 비교
            min = min.compareTo(number) < 0 ? min : number;
            max = max.compareTo(number) > 0 ? max : number;
            return;
        }

        // 모두 확인하지 않은 경우
        switch (signs[idx]){ // 부등호 비교
            case ">":
                for(int i = lastNum - 1; i >= 0; i--){ // 마지막 수보다 큰 수 중
                    if(!used[i]){ // 아직 방문하지 않은 숫자 방문
                        used[i] = true;
                        getNumbers(signs, idx + 1, number + i, i);
                        used[i] = false;
                    }
                }
                break;
            case "<":
                for(int i = lastNum + 1; i < used.length; i++){ // 마지막 수보다 작은 수 중
                    if(!used[i]){ // 아직 방문하지 않은 숫자 방문
                        used[i] = true;
                        getNumbers(signs, idx + 1, number + i, i);
                        used[i] = false;
                    }
                }
                break;
        }
    }
}
