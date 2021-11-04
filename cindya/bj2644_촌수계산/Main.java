package cindya.bj2644_촌수계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());
        int d = -1;
        parent = new int[n + 1];
        Arrays.fill(parent, -1); // parent 값을 모두 -1로 초기화

        // 부모 관계 저장 (부모가 -1이면 최상위)
        for(int i = 0; i < m; i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            parent[input[1]] = input[0];
        }

        br.close();

        int p1 = target[0], p2 = target[1];
        // 확인할 사람들의 최상위 부모 확인
        while(parent[p1] != -1){
            p1 = parent[p1];
        }
        while (parent[p2] != -1){
            p2 = parent[p2];
        }

        // 최상위 부모가 같으면
        if(p1 == p2)
            for(int i = 0; i < parent.length; i++) {
                // 최상위 부모일 때 함수 호출
                if(parent[i] == -1)
                    d = Math.max(d, getDegree(target, i, 0)); // 큰 값(-1이 아닌 값) 선택
            }

        System.out.println(d);
    }

    private static int getDegree(int[] target, int person, int cnt){
        int d, res = -1;

        for(int i = 0; i < parent.length; i++){
            if(parent[i] == person){ // person을 부모로 가지는 사람이면
                d = getDegree(target, i, cnt + 1); // 함수 호출
                if(d != -1){ // i 아래에 있을 경우
                    if(Arrays.stream(target).anyMatch(j -> j == person)){ // person이 target 중 한 명이면 (같은 줄에 있음)
                        return d - cnt; // 같은 줄에 있기 때문에 둘의 위치 차를 반환
                    }
                    else{ // 같은 줄에 없는 경우
                        if(res == -1) res = d; // 한 명만 찾은 경우, res에 값을 저장
                        else return (res - cnt) + (d - cnt); // 둘 다 찾은 경우, person의 위치와 각각의 위치차를 구하여 더함
                    }
                }
            }
        }

        if(Arrays.stream(target).anyMatch(i -> i == person)) return cnt; // 아래에 target에 해당하는 사람은 없고 target에 person이 있는 경우
        return res; // target에 해당하는 사람이 없거나 아래에 한 명만 있는 경우
    }
}
