import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] map = new int[9][9];
    static int count = 0;
    static boolean isEnd;
    static ArrayList<Point> list = new ArrayList<Point>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String row = br.readLine();
            char[] rowChar = row.toCharArray();
            for (int j = 0; j < 9; j++) {
                int num = rowChar[j] - 48;

                if (num == 0) {
                    list.add(new Point(i, j));
                    count++;
                }
                map[i][j] = num;
            }
        }

        solve(0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void solve(int now) {
        if (now == count) {
            isEnd = true;
            return;
        }
        int row = list.get(now).x;
        int column = list.get(now).y;

        for (int i = 1; i <= 9; i++) {
//            System.out.println("i : "+i);
            if (isGood(i, row, column)) {
//                System.out.println("row : "+ row + ", column : " + column + " 당첨!");
                map[row][column] = i;
//                for (int k = 0; k < 9; k++) {
//                    System.out.println(Arrays.toString(map[k]));
//                }
                solve(now+1);
                if (isEnd)
                    return;
            }
        }
        map[row][column] = 0;
    }

    private static boolean isGood(int num, int row, int column) {
//        System.out.println("--------디버깅---------");
//        System.out.println("현재 "+row+"행 "+column+"열에 넣을 숫자 : " + num);
        // 가로 검사
        for (int i = 0; i < 9; i++) {
            if (map[row][i] == num) {
//                System.out.println(row+"행 "+i+"열에서 중복되어 가로검사 불통과");
                return false;
            }
        }
//        System.out.println("가로검사 통과");
        // 세로 검사
        for (int i = 0; i < 9; i++) {
            if (map[i][column] == num) {
//                System.out.println(i+"행 "+column+"열에서 중복되어 세로검사 불통과");
                return false;
            }
        }
//        System.out.println("새로검사 통과");

        // 네모 검사
        int tempRow = row;
        int tempColumn = column;

        int rangeRow = tempRow/3; //0~2면 0, 3~5면 1, 6~8면 2가 저장
        rangeRow *= 3;
        int rangeColumn = tempColumn/3;
        rangeColumn *= 3;

        for (int i = rangeRow; i < rangeRow+3; i++) {
            for (int j = rangeColumn; j < rangeColumn+3; j++) {
                if (map[i][j] == num) {
//                    System.out.println("네모안에서 걸러져서 불통과");
                    return false;
                }
            }
        }

//        System.out.println("마지막까지 통과");
        return true;
    }
}
