import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int N ;
    static int[] arr;
    static boolean[] result;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while ( true){
            N = sc.nextInt();
            if(N==0)
                break;
            arr = new int[N];
            result = new boolean[N];
            for (int i = 0; i <N ; i++) {
                arr[i] = sc.nextInt();
            }
            dfs(0, 0);
            System.out.println();

        }
    }
    private static void dfs(int start, int depth) {
        if (depth == 6) {
            for (int i = 0; i < N; i++) {
                if (result[i]) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
        }
        for (int i = start; i < N; i++) {
            result[i] = true;
            dfs(i + 1, depth + 1);
            result[i] = false;
        }
    }
}