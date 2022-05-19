package studyGroup.may.may21;

import java.util.*;
import java.io.*;

/*

int[n+1][n+1] 을 사용하니 메모리초과 발생
메모리 초과를 방지하기 위해 HashMap과 ArrayList<line> 으로 2차원 배열 역할을 수행하게 함

 */

public class 해킹10282 {

    static int t; // 테스트케이스 갯수
    static int n; // 컴퓨터
    static int d; // 의존성 개수
    static int c; // 해킹당한 컴퓨터 번호호
    static HashMap<Integer, ArrayList<line>> lines;
    static int[][] answer;
    static int index;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        answer = new int[t][2];

        for (int i = 0; i < t; i++) {
            index = i;
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            lines = new HashMap<>();

            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                line lineOne = new line(a, s);

                if(lines.containsKey(b))
                {
                    lines.get(b).add(lineOne);
                }
                else
                {
                    ArrayList<line> lineList = new ArrayList<>();
                    lineList.add(lineOne);
                    lines.put(b, lineList);
                }

//                lines[b][a] = s;
            }

            dik();
        }

        for (int i = 0; i < t; i++) {
            System.out.println(answer[i][0] + " " + answer[i][1]);
        }

    }


    public static void dik() {
        int[] computers = new int[n + 1];
        Arrays.fill(computers, -1);

        Queue<Integer> que = new LinkedList<>();
        computers[c] = 0;
        que.add(c);

        while (!que.isEmpty()) {
            Integer p = que.poll();

            if(lines.containsKey(p))
            {
                ArrayList<line> lineList = lines.get(p);


                for(line one : lineList)
                {
                    int number = one.number;
                    int time = one.time;


                    if(computers[number] == -1)
                    {
                        computers[number] = computers[p] + time;
                        que.add(number);
                    }
                    else if(computers[number] > computers[p] + time)
                    {
                        computers[number] = computers[p] + time;
                        que.add(number);
                    }
                }
            }
        }



        int answer1 = 0;
        int answer2 = 0;

        for (int i = 1; i < n + 1; i++) {
            if (computers[i] != -1) answer1++;
            answer2 = Math.max(answer2, computers[i]);
        }

        answer[index][0] = answer1;
        answer[index][1] = answer2;

    }

    public static class line
    {
        int number;
        int time;

        line(int number, int time)
        {
            this.number = number;
            this.time = time;
        }
    }




}
