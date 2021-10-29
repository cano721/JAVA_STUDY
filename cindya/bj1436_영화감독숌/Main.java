package cindya.bj1436_영화감독숌;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        getNumbers(0, n);  // set에 n개 이상의 666이 들어간 숫자 채우기

        List<Integer> list = new ArrayList<>(set); // set -> list
        Collections.sort(list); // list 정렬

        System.out.println(list.get(n - 1)); // n번째(인덱스 = n-1) 숫자 출력

        br.close();
    }

    private static void getNumbers(int d, int n){
        for(int fnd = 0; fnd <= d; fnd++) { // d자리 중 666 앞부분 자릿수가 0일 때 부터 d일 때까지 루프
            int bnd = d - fnd; // 뒷부분 자릿수
            // 666 앞에 들어갈 수 fn을 fnd 자릿수를 만족하도록 루프
            for (int fn = (int) Math.pow(10, fnd - 1); fn < (int) Math.pow(10, fnd); fn++) {
                String s = (fn > 0 ? String.valueOf(fn) : "") + "666"; // 앞부분을 더한 666
                if(bnd > 0) { // 뒷부분 자릿수가 0이 아닐 경우
                    String format = "%0" + bnd + "d";
                    for (int bn = 0; bn < (int) Math.pow(10, bnd); bn++) { // 0부터 bnd 자릿수를 만족하는 동안 루프
                        set.add(Integer.parseInt(s + String.format(format, bn))); // 뒷자리를 더해서 set에 저장
                    }
                }
                else // 뒷부분 자릿수가 0인 경우
                    set.add(Integer.parseInt(s)); // 뒷부분 없이 set에 저장
            }
        }
        if(set.size() >= n) return; // n보다 크면 멈춤
        getNumbers(d + 1, n); // n보다 작으면 자릿수 늘려서 한번 더
    }
}
