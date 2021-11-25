package cindya.bj5557_1학년;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int res = numbers[n - 1]; // 만든 식에서 '=' 뒤에 올 숫자 (만들어진 식의 결과값)
        Map<Integer, Long> map = new HashMap<>();
        map.put(numbers[0], (long)1); // map에 첫번째 수 삽입

        // 두번째 수부터 n - 1번째 수까지 루프
        for(int i = 1; i < n - 1; i++){
            Map<Integer, Long> nextMap = new HashMap<>();
            // map의 key로 루프
            for(int key : map.keySet()){
                int nextKey = key + numbers[i];
                if(nextKey <= 20) // 더한 값이 20을 넘지 않는다면
                    nextMap.put(nextKey, nextMap.getOrDefault(nextKey, (long)0) + map.get(key)); // nextMap에 개수 더해줌
                nextKey = key - numbers[i];
                if(nextKey >= 0) // 뺀 값이 0보다 작지 않으면
                    nextMap.put(nextKey, nextMap.getOrDefault(nextKey, (long)0) + map.get(key)); // nextMap에 개수 더해줌
            }
            map = nextMap; // map을 nextMap으로 변경
        }

        System.out.println(map.getOrDefault(res, (long)0)); // key가 res인 값이 있으면 출력, 없으면 0 출력
        br.close();
    }
}
