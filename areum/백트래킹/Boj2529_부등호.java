package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2529_부등호 {
    static char[] arr = new char[10];;
    static boolean[] visited = new boolean[10];;
    static List<String> ans = new ArrayList<>();
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine()); // 부등호 문자 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        dfs("", 0);
        Collections.sort(ans); // 정렬

        System.out.println(ans.get(ans.size() - 1)); // 최대정수
        System.out.println(ans.get(0)); // 최소정수
    }

    static void dfs(String nStr, int idx) {
        if(idx == k+1) { // 부등호 갯수 + 1 을 만족한다면
            ans.add(nStr);
            return;
        }

        for(int i=0; i<=9; i++) {
            if(visited[i]) continue;
            if(idx == 0 || check(Character.getNumericValue(nStr.charAt(idx-1)), i, arr[idx-1])) {
                visited[i] = true;
                dfs(nStr+i, idx+1);
                visited[i] = false;
            }
        }
    }

    // 부등호가 알맞는지 확인
    static boolean check(int n1, int n2, int sign) {
        if(sign == '<') {
            if(n1 > n2) return false;
        } else if (sign == '>') {
            if(n1 < n2) return false;
        }
        return true;
    }

}
