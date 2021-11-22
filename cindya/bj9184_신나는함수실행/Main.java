package cindya.bj9184_신나는함수실행;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static Map<String, Integer> memory = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken(" ")), b = Integer.parseInt(st.nextToken(" ")), c = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1 && c == -1) break; // 입력이 -1 -1 -1이면 멈춤

            int res = w(a, b, c);
            bw.write(String.format("w(%d, %d, %d) = %d\n", a, b, c, res)); // 출력
        }

        br.close();
        bw.close();
    }

    private static int w(int a, int b, int c){
        int res;
        String key = String.format("%d %d %d", a, b, c); // a, b, c를 문자열 키로 만듦
        if(memory.containsKey(key)) // 이전에 같은 a b c 연산의 결과가 있으면
            return memory.get(key); // 그 결과를 반환
        if(a <= 0 || b <= 0 || c <= 0)
            res = 1;
        else if(a > 20 || b > 20 || c > 20)
            res = w(20, 20, 20);
        else if(a < b && b < c)
            res = w(a, b, c - 1) + w(a, b- 1, c - 1) - w(a, b - 1, c);
        else
            res = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
        memory.put(key, res); // 연산 결과를 저장
        return res; // 결과 반환
    }
}
