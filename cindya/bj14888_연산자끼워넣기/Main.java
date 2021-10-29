package cindya.bj14888_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static int[] numbers;
    private static int[] operators;
    private static List<Integer> results = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operators = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        getResults(n, 1, numbers[0]);
        Collections.sort(results);

        System.out.println(results.get(results.size() - 1));
        System.out.println(results.get(0));

        br.close();
    }

    private static void getResults(int n, int idx, int res){
        if(idx == n) {
            results.add(res);
        }
        else{
            for(int i = 0; i < operators.length; i++){
                if(operators[i] > 0){
                    operators[i] -= 1;
                    int newRes = res;
                    switch (i){
                        case 0:
                            newRes += numbers[idx];
                            break;
                        case 1:
                            newRes -= numbers[idx];
                            break;
                        case 2:
                            newRes *= numbers[idx];
                            break;
                        case 3:
                            newRes /= numbers[idx];
                            break;
                    }
                    getResults(n, idx + 1, newRes);
                    operators[i] += 1;
                }
            }
        }
    }
}
