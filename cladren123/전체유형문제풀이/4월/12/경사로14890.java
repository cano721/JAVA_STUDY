package studyGroup.april.april12;

// 골드3

/*

for 문이 끝날 때의 상황을 염두하기

 */


import java.util.*;
import java.lang.*;
import java.io.*;


public class 경사로14890 {

    static int n; // 지도의 크기
    static int l; // 경사로 길이

    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        int answer = 0;

        map = new int[n][n];

        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++)
        {
            if (checkRow(i) == true)
            {
                answer += 1;
            }

            if( checkCol(i) == true)
            {
                answer += 1;
            }

        }

        System.out.println(answer);

    }

    // 행, 가로 판단
    public static boolean checkRow(int row)
    {
        int height = map[row][0];
        int stack = 0;
        int downFlag = 0; // 내리막길 체크

        for(int i = 0; i < n; i++)
        {
            // 높이가 같을 때
            if(height == map[row][i])
            {
                stack += 1;
            }
            // 높이 차이가 1 이상일 떄 false를 반환
            else if(map[row][i] > height + 1 || map[row][i] < height-1 )
            {
                return false;
            }
            // 오르막길
            else if (map[row][i] == height + 1)
            {
                // 경사로 설치가 가능할 때
                if(stack >= l)
                {
                    height = map[row][i];
                    stack = 1;
                }
                else
                {
                    return false;
                }

                if(downFlag == 1)
                {
                    return false;
                }
            }
            // 내리막길
            else if (map[row][i] == height - 1)
            {
                if(downFlag == 1)
                {
                    return false;
                }
                height = map[row][i];
                downFlag = 1;
                stack = 1;
            }

            if(downFlag == 1 && stack == l)
            {
                downFlag = 0;
                stack = 0;
            }

        }

        if(downFlag == 1)
        {
            return false;
        }

        return true;
    }

    // 열, 세로 판단
    public static boolean checkCol(int col)
    {
        int height = map[0][col];
        int stack = 0;
        int downFlag = 0; // 내리막길 체크

        for(int i = 0; i < n; i++)
        {
            // 높이가 같을 때
            if(height == map[i][col])
            {
                stack += 1;
            }
            // 높이 차이가 1 이상일 떄 false를 반환
            else if(map[i][col] > height + 1 || map[i][col] < height-1 )
            {
                return false;
            }
            // 오르막길
            else if (map[i][col] == height + 1)
            {
                // 경사로 설치가 가능할 때
                if(stack >= l)
                {
                    height = map[i][col];
                    stack = 1;
                }
                else
                {
                    return false;
                }

                if(downFlag == 1)
                {
                    return false;
                }
            }
            // 내리막길
            else if (map[i][col] == height - 1)
            {
                if(downFlag == 1)
                {
                    return false;
                }
                height = map[i][col];
                downFlag = 1;
                stack = 1;
            }

            if(downFlag == 1 && stack == l)
            {
                downFlag = 0;
                stack = 0;
            }

        }

        if(downFlag == 1)
        {
            return false;
        }

        return true;
    }

}
