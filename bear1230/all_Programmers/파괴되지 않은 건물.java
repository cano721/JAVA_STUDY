class Solution {
    static int[][] arr;
    static int N, M;
 
    public static int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
 
        arr = new int[N + 1][M + 1];
        for (int[] s : skill) {
            int y1 = s[1], x1 = s[2];
            int y2 = s[3], x2 = s[4];
            int degree = s[5] * (s[0] == 1 ? -1 : 1);
 
            arr[y1][x1] += degree;
            arr[y1][x2 + 1] += (degree * -1);
            arr[y2 + 1][x1] += (degree * -1);
            arr[y2 + 1][x2 + 1] += degree;
        }
        operate();

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + arr[i][j] > 0) answer++;
            }
        }
        return answer;
    }
 
    private static void operate() {
        for (int y = 1; y < N; y++) {
            for (int x = 0; x < M; x++) {
                arr[y][x] += arr[y - 1][x];
            }
        }
        for (int x = 1; x < M; x++) {
            for (int y = 0; y < N; y++) {
                arr[y][x] += arr[y][x - 1];
            }
        }
    }
 
}
