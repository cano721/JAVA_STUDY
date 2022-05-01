package studyGroup.april.april30;

import java.util.*;
import java.io.*;

//  https://hees-dev.tistory.com/56

public class 원판돌리기17822 {
    static int[][] circlePlate;
    static int N, M, T;
    static boolean isFindSameNumber = false;
    static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        circlePlate = new int[N][M];
        int x, d, k;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circlePlate[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            for(int j =1; j<=N; j++) {
                if(j % x == 0) {
                    rotate(j-1, d, k);
                }
            }
            checkPlate();
        }
        System.out.print(sumOfPlate());
    }

    public static void rotate(int x, int d, int k) {
        int[] rorateXPlate = new int[M];
        int index;
        for (int i = 0; i < M; i++) {
            index = calculateIndex(d, i, k);
            rorateXPlate[index] = circlePlate[x][i];
        }
        circlePlate[x] = rorateXPlate;

    }

    public static void checkPlate() {
        int nx, ny;
        boolean isErased = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isFindSameNumber = false;
                if (circlePlate[i][j] != 0) {
                    dfs(i, j, circlePlate[i][j]);
                }
                if (isFindSameNumber) {
                    circlePlate[i][j] = 0;
                    isErased = true;
                }
            }
        }
        if (!isErased) {
            calculatePlate();
        }

    }

    public static void dfs(int x, int y, int num) {
        int nx, ny;
        for (int i = 0; i < dx.length; i++) {
            nx = x + dx[i];
            ny = (y + dy[i])%M;
            if(ny < 0) {
                ny = M-1;
            }
            if(isRange(nx,ny)) {
                if (circlePlate[nx][ny] == num) {
                    isFindSameNumber = true;
                    circlePlate[nx][ny] = 0;
                    dfs(nx, ny, num);
                }
            }
        }
    }

    public static int calculateIndex(int d, int i, int k) {
        if (d == 0) {
            return (i + k) % M;
        }
        if (i - k < 0) {
            return M + (i - k);
        }
        return i - k;
    }

    public static void calculatePlate() {
        double sum = 0;
        int totalNumber = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (circlePlate[i][j] != 0) {
                    sum += circlePlate[i][j];
                    totalNumber++;
                }
            }
        }

        double average = sum / totalNumber;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (circlePlate[i][j] != 0 && circlePlate[i][j] < average) {
                    circlePlate[i][j] += 1;
                }
                else if (circlePlate[i][j] != 0 && circlePlate[i][j] > average) {
                    circlePlate[i][j] -= 1;
                }
            }
        }
    }

    public static int sumOfPlate() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += circlePlate[i][j];
            }
        }
        return sum;
    }

    public static boolean isRange(int x, int y) {
        return (x < 0 || x >= N || y < 0 || y >= M) ? false : true;
    }

}