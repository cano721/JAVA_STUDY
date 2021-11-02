package cindya.bj2606_바이러스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static List<Integer>[] connects;
    private static Boolean[] infected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int conn = Integer.parseInt(br.readLine());
        connects = new List[n];
        for(int i = 0; i < n; i++){
            connects[i] = new ArrayList<>();
        }

        infected = new Boolean[n];
        Arrays.fill(infected, false);

        for(int i = 0; i < conn; i++){
            int[] c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(j -> j - 1).toArray();
            connects[c[0]].add(c[1]);
            connects[c[1]].add(c[0]);
        }
        br.close();

        getInfectedComputers(0);

        int answer = (int)Arrays.stream(infected).filter(b -> b).count() - 1; // true만 센 후, 1번 컴퓨터 하나를 뺌
        System.out.println(answer);
    }

    private static void getInfectedComputers(int computer){
        infected[computer] = true; // computer가 감염되었다고 표시
        for(int c : connects[computer]){ // 연결된 컴퓨터 List 루프
            if(!infected[c]) // 아직 표시되지 않았으면
                getInfectedComputers(c); // 연결된 컴퓨터 호출
        }
    }
}
