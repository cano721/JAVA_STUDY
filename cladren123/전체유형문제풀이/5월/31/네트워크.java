package studyGroup.may.may31;

import java.util.*;

public class 네트워크 {

    public static void main(String[] args) {

        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};

        System.out.println(solution(n,computers));


    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;

        for(int i = 0; i < n; i++)
        {
            computers[i][i] = 0;
        }

        int count = 0;

        Queue<Integer> que = new LinkedList<>();

        int[] number = new int[n];

        for(int i = 0; i < n; i++)
        {

            if (number[i] != 0) continue;

            count++;

            number[i] = count;
            que.add(i);

            while(!que.isEmpty())
            {
                int one = que.poll();

                for(int j = 0; j < n; j++)
                {
                    if (computers[one][j] == 1 && number[j] == 0)
                    {
                        number[j] = count;
                        que.add(j);
                    }
                }
            }
        }

        // System.out.println(Arrays.toString(number));
        answer = count;
        return answer;
    }




}
