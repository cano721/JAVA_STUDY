package studyGroup.april.april8;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;
import java.io.*;

/*
1초 동안 일어나는 일

1. 낚시왕이 오른쪽으로 한 칸 이동
2. 열에 있는 상어 중 땅에 가장 가까운 상어 잡음
3. 상어 이동
 3-1 벽에 만나면 반대로 이동
 3-2 상어가 겹치면 덩치가 큰 상어가 다 잡아먹는다.
4. 낚시왕이 오른쪽으로 나가면 끝난다.


 */


public class 낚시왕17143 {

    static int mapr;
    static int mapc;

    static int r; // 행
    static int c; // 열
    static int m; // 상어의 수
    static int[][] board; // 격자판판

    static int s; // 속력
    static int d; // 방향
    static int z; // 크기

    static HashMap<Integer, shark> map;

    // 상어의 이동 방향 1: 위 2: 아래 3: 오른쪽 4: 왼쪽
    static int[] dc = {0,0,0,1,-1};
    static int[] dr = {0,-1,1,0,0};

    static int answer;

    static ArrayList<Integer> dieList = new ArrayList<>();

   public static void main(String[] args) throws IOException {

       answer = 0;

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       mapr = Integer.parseInt(st.nextToken());
       mapc = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());

       board = new int[mapr+1][mapc+1];

       map = new HashMap<>();

       for(int i = 1; i < m+1; i++ )
       {
           st = new StringTokenizer(br.readLine());

           r = Integer.parseInt(st.nextToken());
           c = Integer.parseInt(st.nextToken());
           s = Integer.parseInt(st.nextToken());
           d = Integer.parseInt(st.nextToken());
           z = Integer.parseInt(st.nextToken());

           map.put(i, new shark(i,r,c,s,d,z));
           board[r][c] = i;
       }


       for(int i = 1; i < mapc+1; i++)
       {
           catchShark(i);



           moveShark();
       }

       System.out.println(answer);
    }

    // 땅에서 가까운 상어를 잡는 함수
    public static void catchShark(int col)
    {
        for(int row = 1; row <= mapr; row++)
        {
            if(board[row][col] != 0)
            {
                answer += map.get(board[row][col]).z;
                map.remove(board[row][col]);
                board[row][col] = 0;

                return;
            }
        }
        return;
    }

    public static void moveShark()
    {
        board = new int[mapr+1][mapc+1];

        for(int i  : map.keySet())
        {
            shark s = map.get(i);

            for(int j = 0; j < s.s; j++)
            {
                if(s.d == 1 && s.r == 1)
                {
                    s.d = 2;
                }
                else if (s.d == 2 && s.r == mapr)
                {
                    s.d = 1;
                }
                else if (s.d == 3 && s.c == mapc)
                {
                    s.d = 4;
                }
                else if (s.d == 4 && s.c == 1)
                {
                    s.d = 3;
                }

                s.r += dr[s.d];
                s.c += dc[s.d];

            }

            // 상어가 중복되면 잡아 먹는다.
            if(board[s.r][s.c] != 0)
            {
                shark originShark = map.get(board[s.r][s.c]);

                if(s.z > originShark.z)
                {
                    dieList.add(board[s.r][s.c]);
                    board[s.r][s.c] = s.index;
                }
                else
                {
                   dieList.add(i);
                }
            }
            else {
                board[s.r][s.c] = s.index;
            }
        }

        // 잡아먹힌 상어 제거하기
        for (Integer index : dieList) {
            map.remove(index);
        }

        return;

    }

    public static class shark
    {
        int index;  // 번호
        int r;  // 행
        int c;  // 열
        int s;  // 속력
        int d;  // 이동방향
        int z;  // 크기

        shark(int index, int r, int c, int s, int d, int z)
        {
            this.index = index;
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }


    }

}
