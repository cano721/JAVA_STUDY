package day220425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062_가르침 {

    static int N, K, words[];
    static String[] s;
    static int visited = 0;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken())-5;
        words = new int[N];
        s = new String[N];
        int aa = 'a' - 'a';
        int nn = 'n' - 'a';
        int tt = 't' - 'a';
        int ii = 'i' - 'a';
        int cc = 'c' - 'a';

        visited = visited | 1 << aa;
        visited = visited | 1 << nn;
        visited = visited | 1 << tt;
        visited = visited | 1 << ii;
        visited = visited | 1 << cc;

        for (int i = 0; i < N; i++) {
            String now = br.readLine();
            int length = now.length();
            int temp = visited;
            s[i] = now.substring(4, length - 4);
            for (int j = 0; j < s[i].length(); j++) {
                temp = temp | 1 << s[i].charAt(j) - 'a';
                words[i] = temp;
            }
        }

        //이미 5개의 anta, tica는 5개의 알파벳을 포함하고 있기 때문에
        //N이 5미만이면 0출력 리턴
        if (K < 0) {
            System.out.println(0);
            return;
        }


        dfs(0, 0);
        System.out.println(max);
    }

    private static void dfs(int cnt, int idx) {
        if (cnt == K) {
            int answer = 0;
            // 현재 마킹된 visited 값이 배운 알파벳 값이다.
            // 이 값을 기준으로 모든 s[i] 알파벳을 순회하며 아는 단어라면 answer++;
            // 이후 max값에 갱신
            for (int word : words) {
                int check = visited | word;
                if (check == visited) answer++;
            }
            max = Math.max(max, answer);
            return;
        }

        for (int i = idx; i < 26; i++) {
            if ((visited & (1 << i)) > 0) continue;
            visited = visited | 1 << i;
            dfs(cnt + 1, i + 1);
            visited = visited ^ 1 << i;
        }
    }
}
