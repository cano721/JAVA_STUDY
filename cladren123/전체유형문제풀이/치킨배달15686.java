package studyGroup.april.five;

import java.util.*;
import java.io.*;
import java.lang.*;


public class 치킨배달15686 {

    static int n; // 도시의 크기
    static int m; // 고른 치킨집의 수
    static int city[][]; // 도시 지도
    static ArrayList<location> house; // 집들을 저장할 리스트
    static ArrayList<location> chicken; // 치킨집들을 저장할 리스트
    static int chickenNumber; // 치킨집 갯수
    static Stack<location> select; // 선택된 치킨집들
    static int answer; // 정답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n][n];
        house = new ArrayList<>();
        chicken = new ArrayList<>();


        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
            {
                city[i][j] = Integer.parseInt(st.nextToken());

                if ( city[i][j] == 1 ) {
                    // 집 추가
                    house.add(new location(i,j));
                }
                if ( city[i][j] == 2 ) {
                    // 치킨집 추가
                    chicken.add(new location(i,j));
                }
            }
        }

        chickenNumber = chicken.size();
        select = new Stack<>();
        answer = Integer.MAX_VALUE;

        selectChicken(0,0);

        System.out.println(answer);
    }

    // start 추가로 중복을 제거
    public static void selectChicken(int start, int stage) {

        // 종단 조건 치킨집을 m개 선택
        if (stage == m) {

            calcDistance();
            return;
        }

        for(int i = start; i < chickenNumber; i++)
        {
            select.push(chicken.get(i));
            selectChicken(i+1, stage+1);
            select.pop();
        }
    }

    // 선택된 치킨집과 집들의 거리 계산
    public static void calcDistance() {

        int chickenDistance = 0;

        for (location home : house )
        {
            int result = Integer.MAX_VALUE;

            for (location chicken : select)
            {
                int distance = Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y);

                result = Math.min(result, distance);
            }
            chickenDistance += result;
        }

        if (answer > chickenDistance) {
            answer = chickenDistance;
        }
    }

    public static class location
    {
        int y;
        int x;

        location(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
