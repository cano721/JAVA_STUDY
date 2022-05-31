package studyGroup.may.may30;

import java.util.*;
import java.lang.*;
import java.io.*;

/*

모든 벽이 떨어질 때 까지 하나라도 살아있으면 1, 그렇지 않으면 0

 */

public class 움직이는미로탈출16954 {

    static char[][] board;
    static boolean[][] visited;

    // 시간방향
    static int[] dy = {0,0,-1,-1,-1,0,1,1,1};
    static int[] dx = {0,1,1,0,-1,-1,-1,0,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[8][8];

        for(int i = 0; i < 8; i++)
        {
            String one = br.readLine();
            for(int j = 0; j < 8; j++)
            {
                board[i][j] = one.charAt(j);
            }
        }

        ArrayList<dot> arr1 = new ArrayList<>();


        arr1.add(new dot(7,0));




        // 8턴만 지나면 벽은 다 내려가서 없어진다.
        for(int i = 0; i <= 8; i++)
        {
            visited = new boolean[8][8];
            ArrayList<dot> arr2 = new ArrayList<>();

            for (dot d : arr1)
            {

                for(int j = 0; j < 9; j++)
                {
                    int ny = d.y + dy[j];
                    int nx = d.x + dx[j];

                    if(ny < 8 && ny >= 0 && nx < 8 && nx >= 0)
                    {
                        if(board[ny][nx] == '.' && visited[ny][nx] == false)
                        {
                            visited[ny][nx] = true;
                            arr2.add(new dot(ny,nx));
                        }
                    }
                }
            }
            moveWall();

            arr1 = new ArrayList<>();
            for (dot d : arr2) {
                if (board[d.y][d.x] == '.')
                {
                    arr1.add(d);
                }
            }
        }

        if(arr1.isEmpty())
        {
            System.out.println(0);
        }
        else
        {
            System.out.println(1);
        }



    }

    // 벽이 아래로 내려가는 함수
    public static void moveWall() {
        for(int i = 7; i >= 0; i--)
        {
            for(int j = 0; j < 8; j++)
            {
                if(i == 0)
                {
                    board[i][j] = '.';
                }
                else
                {
                    board[i][j] = board[i-1][j];
                }
            }
        }
    }

    public static class dot
    {
        int y;
        int x;

        dot(int y, int x)
        {
            this.y = y;
            this.x = x;
        }
    }

}
