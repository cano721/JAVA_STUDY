package studyGroup.april.april14;

/*

R 연산 구현 : 행 >= 열  세로 >= 가로
C 연산 구현 : 행 < 열   세로 <  가로
수를 정렬할 때 0을 무시한다.


런타임 에러 (ArrayIndexOutOfBounds)

4 4 1
1 2 1
2 1 3
3 3 3


 */


import java.util.*;
import java.lang.*;
import java.io.*;

public class 이차원배열과연산17140 {

    static int r;
    static int c;
    static int k;

    static int row;
    static int col;

    static int[][] a;

    static HashMap<Integer, ArrayList<Integer>> rowMap;
    static HashMap<Integer, ArrayList<Integer>> colMap;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        row = 3;
        col = 3;

        a = new int[row][col];

        for(int i = 0; i < 3; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++)
            {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = -1;


        if(r-1 < row && c-1 < col && a[r-1][c-1] == k)
        {
            answer = 0;
        }
        else
        {
            for(int i = 1; i < 101; i++)
            {
                if(row >= col)
                {
                    calcR();
                }
                else
                {
                    calcC();
                }

                if(r-1 < row && c-1 < col &&  a[r-1][c-1] == k)
                {
                    answer = i;
                    break;
                }
            }
        }

        System.out.println(answer);

    }

    // C연산
    public static void calcC()
    {
        colMap = new HashMap<>();

        int nowRow = row;

        for(int i = 0; i < col; i++)
        {
            oneC(i, nowRow);
        }

        a = new int[row][col];

        for(int i = 0; i < col; i++)
        {
            ArrayList<Integer> colOne = colMap.get(i);

            for(int j = 0; j < colOne.size(); j++)
            {
                a[j][i] = colOne.get(j);
            }

        }
    }

    // row 한 줄에 R연산
    public static void oneC(int colNum, int nowRow)
    {
        // 숫자, 횟수를 저장할 map
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nowRow; i++)
        {
            if(a[i][colNum] == 0) continue;
            map.put(a[i][colNum], map.getOrDefault(a[i][colNum], 0) + 1);
        }

        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();

        for(Integer one : map.keySet())
        {
            ArrayList<Integer> tempOne = new ArrayList<>();
            tempOne.add(one);
            tempOne.add(map.get(one));
            temp.add(tempOne);
        }

        // 수의 등장 횟수가 커지는 순서, 숫자가 커지는 순서
        Collections.sort(temp, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (o1.get(1).equals(o2.get(1)))
                {
                    return o1.get(0) - o2.get(0);
                }
                return o1.get(1) - o2.get(1);
            }
        });

        ArrayList<Integer> colOne = new ArrayList<>();

        // 숫자, 순서
        for(ArrayList<Integer> one : temp)
        {
            colOne.add(one.get(0));
            colOne.add(one.get(1));
        }

        colMap.put(colNum, colOne);

        // 가장 긴 행을 탐색
        row = Math.max(row, colOne.size());
    }


    // R연산
    public static void calcR()
    {
        rowMap = new HashMap<>();

        int nowCol = col;

        for(int i = 0; i < row; i++)
        {
            oneR(i, nowCol);
        }

        a = new int[row][col];

        for(int i = 0; i < row; i++)
        {
            ArrayList<Integer> rowOne = rowMap.get(i);

            for(int j = 0; j < rowOne.size(); j++)
            {
                a[i][j] = rowOne.get(j);
            }

        }
    }

    // row 한 줄에 R연산
    public static void oneR(int rowNum, int nowCol)
    {
        // 숫자, 횟수를 저장할 map
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nowCol; i++)
        {
            if(a[rowNum][i] == 0) continue;
            map.put(a[rowNum][i], map.getOrDefault(a[rowNum][i], 0) + 1);
        }

        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();

        for(Integer one : map.keySet())
        {
            ArrayList<Integer> tempOne = new ArrayList<>();
            tempOne.add(one);
            tempOne.add(map.get(one));
            temp.add(tempOne);
        }

        // 수의 등장 횟수가 커지는 순서, 숫자가 커지는 순서
        Collections.sort(temp, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (o1.get(1).equals(o2.get(1)))
                {
                    return o1.get(0) - o2.get(0);
                }
                return o1.get(1) - o2.get(1);
            }
        });

        ArrayList<Integer> rowOne = new ArrayList<>();

        // 숫자, 순서
        for(ArrayList<Integer> one : temp)
        {
            rowOne.add(one.get(0));
            rowOne.add(one.get(1));
        }

        rowMap.put(rowNum, rowOne);

        // 가장 긴 행을 탐색
        col = Math.max(col, rowOne.size());
    }


}
