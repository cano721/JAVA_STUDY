package 백트래킹;

import java.util.Arrays;
import java.util.Scanner;

public class Boj6603_로또 {
    static int K;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            K = sc.nextInt(); // 숫자 갯수

            if(K==0) break; // 0 입력시 종료

            arr = new int[K];
            visited = new boolean[K]; // 백트래킹

            for(int i=0; i<arr.length; i++) {
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr); // 정렬

            dfs(0,0);
            System.out.println();
        }
    }

    public static void dfs(int st, int cnt) {
        if(cnt == 6) {
            for(int i=0; i<K; i++) {
                if(visited[i]) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
            return;
        }

        for(int i=st; i<K; i++) {
            visited[i] = true;
            dfs(i+1, cnt+1); // 재귀호출
            visited[i] = false;
        }
    }
}
