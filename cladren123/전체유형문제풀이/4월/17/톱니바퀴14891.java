package studyGroup.april.april17;

/*
톱니바퀴 4개의 정보 주어짐
k번 회전 (톱니바퀴 선택, 어떤 방향으로 회전할지 선택)
맞닿은 톱니바퀴의 극이 같으면 회전X 다르면 회전O
회전을 마친 다음에 점수를 출력

처음 상태에 다 같이 체크한다음 옆에 톱니바퀴가 회전하면 같이 회전한다.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class 톱니바퀴14891 {

    static HashMap<Integer, String> gears; // 톱니바퀴 정보
    static int k; // 회전 횟수
    static int[][] rotateFlag;
    static int[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gears = new HashMap<>();
        answer = 0;


        for(int i = 1; i <= 4; i++)
        {
            String gear = br.readLine();
            gear.trim();
            gears.put(i, gear);
        }

        k = Integer.parseInt(br.readLine());

        for(int i = 0; i < k; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            rotateFlag = new int[5][5];
            visited = new int[5];
            rotateCheck();
            rotate(index, dir);
        }


        count();



        System.out.println(answer);

    }

    // 점수계산 함수
    public static void count()
    {
        String gear1 = gears.get(1);
        String gear2 = gears.get(2);
        String gear3 = gears.get(3);
        String gear4 = gears.get(4);

        if(gear1.charAt(0) == '1') answer += 1;
        if(gear2.charAt(0) == '1') answer += 2;
        if(gear3.charAt(0) == '1') answer += 4;
        if(gear4.charAt(0) == '1') answer += 8;
    }


    public static void rotate(int index, int dir)
    {
        if(visited[index] == 1)
            return;

        visited[index] = 1;

        rotatePlay(index, dir);

        int nextdir = 0;
        if(dir == 1)
        {
            nextdir = -1;
        }
        else if(dir == -1)
        {
            nextdir = 1;
        }

        for(int i = 1; i <= 4; i++)
        {
            if(rotateFlag[index][i] == 1)
            {
                rotate(i, nextdir);
            }
        }

    }

    // 회전
    public static void rotatePlay(int index, int dir)
    {
        // 반시게 방향
        if(dir == -1)
        {
            String gear = gears.get(index);
            gear = gear + gear.charAt(0);
            gear = gear.substring(1, gear.length());
            gears.put(index, gear);
        }
        // 시계 방향
        else if(dir == 1)
        {
            String gear = gears.get(index);
            gear = gear.charAt(gear.length()-1) + gear;
            gear = gear.substring(0, gear.length()-1);
            gears.put(index, gear);
        }
    }

    // 회전 여부 확인 함수
    public static void rotateCheck()
    {
        String gear1 = gears.get(1);
        String gear2 = gears.get(2);
        String gear3 = gears.get(3);
        String gear4 = gears.get(4);

        if(gear1.charAt(2) != gear2.charAt(6))
        {
            rotateFlag[1][2] = 1;
            rotateFlag[2][1] = 1;
        }
        if(gear2.charAt(2) != gear3.charAt(6))
        {
            rotateFlag[2][3] = 1;
            rotateFlag[3][2] = 1;
        }
        if(gear3.charAt(2) != gear4.charAt(6))
        {
            rotateFlag[3][4] = 1;
            rotateFlag[4][3] = 1;
        }


    }



}
