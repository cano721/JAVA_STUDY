package cindya.bj1717_집합의표현;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")) + 1, m = Integer.parseInt(st.nextToken());
        parent = new int[n];

        // 자기자신을 부모로 초기화
        for(int i = 0; i < n; i++)
            parent[i] = i;

        while (m-- > 0){
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken(" ")),
                    a = findParent(Integer.parseInt(st.nextToken(" "))), b = findParent(Integer.parseInt(st.nextToken()));
            switch (oper){ // 연산 종류에 따라
                case 0: // 0이면
                    if(a != b) // a와 b가 같은 그룹이 아니면
                            parent[a] = b; // a를 b로 합침
                    break;
                case 1: // 1이면
                    if(a == b) bw.write("YES\n"); // a와 b의 최상위 부모가 같으면 YES 출력
                    else bw.write("NO\n"); // 다르면 NO 출력
                    break;
            }
        }

        br.close();
        bw.close();
    }

    // 최상위 부모를 찾는 함수
    private static int findParent(int child){
        if(child != parent[child])
            parent[child] = findParent(parent[child]);
        return parent[child];
    }
}
