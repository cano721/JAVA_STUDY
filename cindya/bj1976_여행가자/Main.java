package cindya.bj1976_여행가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
        int[][] map = new int[n][];
        int[] plan;

        parent = new int[n];

        for(int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            parent[i] = i; // 자기자신을 부모로 설정
        }
        plan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(i -> i - 1).toArray(); // 도시 번호를 1씩 빼고 저장
        br.close();

        // 그룹 만들기
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){ // i == j인 지점 위쪽으로만 루프
                if(map[i][j] == 1){ // i와 j가 이어져있으면
                    int ip = findParent(i), jp = findParent(j); // 각각의 최상위 부모를 찾고
                    parent[jp] = ip; // i의 그룹에 j의 그룹을 연결
                }
            }
        }

        int p = findParent(plan[0]); // 여행 계획 첮번째 도시의 최상위 부모 찾기
        for(int i = 1; i < m; i++){
            if(p != findParent(plan[i])){ // 최상위 부모가 다르면
                System.out.println("NO"); // NO를 출력하고 종료
                return;
            }
        }
        System.out.println("YES"); // 모두 연결되어 있으면 YES 출력
    }

    // 최상위 부모를 찾는 함수
    private static int findParent(int child){
        if(child == parent[child]) // 부모와 자식이 같으면
            return child; // 그대로 반환
        else // 다르면
            return (parent[child] = findParent(parent[child])); // 찾은 최상위 부모를 child의 부모로 설정하고 반환
    }
}
