import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        int max = 0;

        for (int i = 0; i < N; i++)
            board[i] = br.readLine().toCharArray();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (j + 1 < N) {
                    char t = board[i][j]; board[i][j] = board[i][j+1]; board[i][j+1] = t;
                    max = Math.max(check(board, N), max);
                    t = board[i][j]; board[i][j] = board[i][j+1]; board[i][j+1] = t;
                }

                if (i + 1 < N) {
                    char t = board[i][j]; board[i][j] = board[i+1][j]; board[i+1][j] = t;
                    max = Math.max(check(board, N), max);
                    t = board[i][j]; board[i][j] = board[i+1][j]; board[i+1][j] = t;
                }
            }
        System.out.print(max);
    }

    static int check(char[][] board, int size) {
        int answer = 1;
        for (int i = 0; i < size; i++) {
            int count = 1;
            for (int j = 1; j < size; j++) {
                count = board[i][j] == board[i][j-1] ? count + 1 : 1;
                answer = Math.max(answer, count);
            }

            count = 1;
            for (int j = 1; j < size; j++) {
                count = board[j][i] == board[j-1][i] ? count + 1 : 1;
                answer = Math.max(answer, count);
            }
        }
        return answer;
    }
}