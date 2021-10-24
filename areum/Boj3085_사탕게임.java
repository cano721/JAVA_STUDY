import java.io.*;
import java.util.StringTokenizer;

public class Boj3085_사탕게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        char[][] candy = new char[N][N];
        int answer = 0;

        int[] dx = {0, 1};
        int[] dy = {1, 0};

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                candy[i][j] = line[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (j + 1 < N) {
                    swap(candy, j, j + 1, i, i);
                    answer = Math.max(answer, countCandy(candy)); // 최댓값 갱신
                    swap(candy, j, j + 1, i, i);
                }
                if (i + 1 < N) {
                    swap(candy, j, j, i, i + 1);
                    answer = Math.max(answer, countCandy(candy)); // 최댓값 갱신
                    swap(candy, j, j, i, i + 1);
                }

            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    // 같은 캔디 체크
    private static int countCandy(char[][] arr) {
        int count = 1;

        for (int i = 0; i < arr.length; i++) { // 가로확인
            int temp = 1;
            for (int j = 1; j < arr.length; j++) {
                if (arr[i][j] == arr[i][j - 1]) {
                    temp++;
                } else temp = 1;
                count = Math.max(count, temp);
            }
        }

        for (int i = 0; i < arr.length; i++) { // 세로확인
            int temp = 1;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j][i] == arr[j - 1][i]) {
                    temp++;
                } else temp = 1;
                count = Math.max(count, temp);
            }
        }
        return count;
    }

    // 캔디 교환
    private static void swap(char[][] arr, int x1, int x2, int y1, int y2) {
        char temp = arr[y1][x1];
        arr[y1][x1] = arr[y2][x2];
        arr[y2][x2] = temp;
    }
}
