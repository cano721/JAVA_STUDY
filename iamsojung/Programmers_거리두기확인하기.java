class Solution {
    static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1, -2, 0, 2, 0};
    static int[] dy = {0, 1, 0, -1, 1, 1, -1, -1, 0, 2, 0, -2};

    static int[] nx = {-1, 0, 1, 0};
    static int[] ny = {0, 1, 0, -1};

    static char[][] map;
    static boolean[][] visited;


    static public int[] solution(String[][] places) {

        int[] answer = new int[5];

        for (int i = 0; i < places.length; i++) {

            //System.out.println(places[0][i]);
            map = new char[5][5];
            visited = new boolean[5][5];

            for (int j = 0; j < 5; j++) {
                char[] temp = places[i][j].toCharArray();
                for (int m = 0; m < 5; m++) {
                    map[j][m] = temp[m];
                }

            }

            int temp = func();
            answer[i] = temp;
            System.out.println(answer[i]);
        }


        return answer;
    }

    private static int func() {

        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                if (map[j][k] == 'P') {
                    if (check(j, k) == false) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    private static boolean check(int j, int k) {

        for (int i = 0; i < 4; i++) {

            int tempX = j + dx[i];
            int tempY = k + dy[i];

            if (tempX < 0 || tempY < 0 || tempX >= 5 || tempY >= 5)
                continue;

            if (map[tempX][tempY] == 'P')
                return false;
        }

        for (int i = 4; i < 8; i++) {

            int tempX = j + dx[i];
            int tempY = k + dy[i];

            if (tempX < 0 || tempY < 0 || tempX >= 5 || tempY >= 5)
                continue;

            if (map[tempX][tempY] == 'P') {
                if (i == 4) {

                    int tempNX = j + nx[0];
                    int tempNY = k + ny[0];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;

                    tempNX = j + nx[1];
                    tempNY = k + ny[1];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;

                } else if (i == 5) {

                    int tempNX = j + nx[1];
                    int tempNY = k + ny[1];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;

                    tempNX = j + nx[2];
                    tempNY = k + ny[2];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;
                } else if (i == 6) {

                    int tempNX = j + nx[2];
                    int tempNY = k + ny[2];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;

                    tempNX = j + nx[3];
                    tempNY = k + ny[3];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;
                } else if (i == 7) {
                    int tempNX = j + nx[3];
                    int tempNY = k + ny[3];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;

                    tempNX = j + nx[0];
                    tempNY = k + ny[0];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;
                }
            }
        }
        for (int i = 8; i < 12; i++) {

            int tempX = j + dx[i];
            int tempY = k + dy[i];

            if (tempX < 0 || tempY < 0 || tempX >= 5 || tempY >= 5)
                continue;

            if (map[tempX][tempY] == 'P') {

                if (i == 8) {
                    int tempNX = j + nx[0];
                    int tempNY = k + ny[0];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;
                } else if (i == 9) {
                    int tempNX = j + nx[1];
                    int tempNY = k + ny[1];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;
                } else if (i == 10) {
                    int tempNX = j + nx[2];
                    int tempNY = k + ny[2];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;

                } else if (i == 11) {
                    int tempNX = j + nx[3];
                    int tempNY = k + ny[3];
                    if (tempNX < 0 || tempNY < 0 || tempNX >= 5 || tempNY >= 5)
                        continue;
                    if (map[tempNX][tempNY] == 'P' || map[tempNX][tempNY] == 'O')
                        return false;
                }
            }

        }
        return true;
    }
}
//
//
//            for (int j = 0; j < 5; j++) {
//                System.out.println();
//                for (int m = 0; m < 5; m++) {
//                    System.out.print(map[j][m]);
//                }
//            }