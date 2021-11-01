package baekjoon.silverⅡ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;



/**
 * @author JIHO
 */


public class Boj2529_부등호 {


    // 어려워서 ... 블로그 참조함... https://iamheesoo.github.io/blog/algo-boj2529
    static int k; // 부등호 문자의 개수
    static String[] array; // 부등호 저장 array
    static boolean[] visited; // 중복숫자를 거르는 배열
    static ArrayList<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        k = Integer.parseInt(br.readLine());
        array = new String[k];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            array[i] = st.nextToken();
        }
        list = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            visited = new boolean[10];
            visited[i] = true;
            dfs(0, i, String.valueOf(i));
        }

        // 0부터 차례대로 오름차순 생성되므로 list에도 오름차순으로 저장됨.
        // 따라서 list의 마지막에는 max값이, 처음에는 min값이 저장됨. 
        bw.write(list.get(list.size()-1)+"\n" + list.get(0)); // 최댓값
        bw.flush();
        bw.close();
    }

    // dfs() 를 통해 숫자 생성하고  list에 저장
    // idx는 부등호 배열 array를 순회하는 인덱스
    // now는 이전에 선택된 숫자. 부등호 체크를 위해 파라미터로 넘겨줌. 
    // num은 현재까지 만들어진 숫자를 나타냄

    public static void dfs(int idx, int now, String num) {
        if(idx==k) { // k는 부등호 문자의 갯수로 idx==k에서 종료 조건
            list.add(num); // k+1 길이의 숫자 생성 완료이므로 list에 넣고 종료
            return;
        }

        for(int i=0;i<=9;i++) {
            // visited[i]가 true로 방문했으면 다음 반복 실행
            if(visited[i]) continue; 

            if(array[idx].equals("<")) {
                if(now<i) {
                    visited[i]=true;
                    dfs(idx+1, i, num+i);
                }
            }
            else {
                if(now>i) {
                    visited[i]=true;
                    dfs(idx+1, i, num+i);
                }
            }

            // dfs()호출이 끝난 후에는 다음 사용을 위해 false처리 해준다. 
            visited[i]=false;
        }
    }
}