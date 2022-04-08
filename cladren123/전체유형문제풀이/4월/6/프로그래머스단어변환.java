package studyGroup.april.five.sixth;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solution(begin, target, words));

    }

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;

        int n = words.length; // 총 단어의 수
        int m = begin.length(); // 단어의 길이

        int[] visited = new int[n];

        Queue<dot> que = new LinkedList();

        // 시작점 넣기
        que.add(new dot(begin, 0));

        while(!que.isEmpty())
        {
            dot one = que.poll();

            for(int i = 0; i < n; i++)
            {
                if(visited[i] == 1) {
                    continue;
                }

                int check = 0;

                for(int j = 0; j < m; j++)
                {
                    if(one.word.charAt(j) != words[i].charAt(j))
                    {
                        check += 1;
                    }
                }

                if(check == 1)
                {
                    if (target.equals(words[i])) {
                        return one.seq+1;
                    }

                    que.add(new dot(words[i], one.seq+1));
                    visited[i] = 1;
                }


            }
        }



        return answer;
    }

    public static class dot
    {
        String word;
        int seq;

        dot(String word, int seq)
        {
            this.word = word;
            this.seq = seq;
        }


    }
}
