package studyGroup.april.april11;

/*
최솟값이 8보다 크면 -1을 return
-> 1~8까지만 탐색

HashSet을 이용해 중복을 제거

*/

import java.util.*;

public class N으로표현 {

    public static void main(String[] args) {
        int n = 5;
        int number = 12;

        System.out.println(solution(n, number));

    }

    static HashMap<Integer, HashSet<Integer>> map;
    static int n;

    public static int solution(int N, int number) {
        int answer = -1;

        n = N;

        map = new HashMap<>();

        for(int i = 1; i <= 8; i++)
        {
            makeSet(i);

            if(map.get(i).contains(number))
                return i;
        }

        return answer;
    }

    public static void makeSet(int index)
    {
        int number = 0;
        int count = 1;
        for(int i = 0; i < index; i++)
        {
            number += n * count;
            count = count * 10;
        }

        HashSet<Integer> set = new HashSet<>();
        set.add(number);

        for(int i = 1; i <= index / 2; i++)
        {
            HashSet<Integer> set1 = map.get(i);
            HashSet<Integer> set2 = map.get(index-i);

            for(int one : set1)
            {

                for(int two : set2)
                {
                    set.add(one + two);
                    set.add(one * two);

                    if(one > two)
                    {
                        set.add(one - two);
                        if(two == 0) continue;
                        set.add(one / two);
                    }
                    else
                    {
                        set.add(two - one);
                        if(one == 0) continue;
                        set.add(two / one);
                    }


                }
            }
        }

        map.put(index, set);

    }
}
