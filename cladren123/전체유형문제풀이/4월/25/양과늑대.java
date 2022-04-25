package studyGroup.april.april25;

/*

늑대가 양과 같거나 많으면 양을 잡아먹는다.
최대한 많은 수의 양을 모으자.

info : 노드의 정보(양인지 늑대인지)
edge : 노드의 연결

양을 먹을 때마다 지난 온 노드들을 전부 확인해야 한다.


*/

import java.util.*;

public class 양과늑대 {

    public static void main(String[] args) {

        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};

        System.out.println(solution(info, edges));

    }


    static int[][] lines;

    public static int solution(int[] info, int[][] edges) {
        int answer = 0;

        int node = info.length; // 노드의 개수
        int[] visited = new int[node];

        lines = new int[node][node];

        for(int[] ints : edges)
        {
            int start = ints[0];
            int end = ints[1];

            lines[start][end] = 1;
        }

        Queue<dot> que = new LinkedList<>();
        ArrayList<Integer> trace = new ArrayList<>();
        trace.add(0);
        visited[0] = 1;
        que.add(new dot(1,0,trace,visited));

        while(!que.isEmpty())
        {
            dot d = que.poll();


            answer = Math.max(answer, d.lamb);

            for(Integer one : d.trace)
            {
                for(int i = 0; i < node; i++)
                {
                    // 연결되어 있으면 탐색 시작
                    if(lines[one][i] == 1 && d.visited[i] == 0)
                    {
                        // 양인 경우
                        if(info[i] == 0)
                        {
                            // 탐색 가능한 노드의 깊은 복사
                            ArrayList<Integer> newTrace = new ArrayList<>();
                            for(Integer traceOne : d.trace)
                            {
                                newTrace.add(traceOne);
                            }
                            newTrace.add(i);

                            // 지나온 길에 대한 깊은 복사
                            int[] newVisited = new int[node];
                            for(int j = 0; j < node; j++)
                            {
                                newVisited[j] = d.visited[j];
                            }
                            newVisited[i] = 1;

                            // 탐색 가능한 노드 중에서 갈 수 있는 모든 길을 탐색한 경우 제거해준다.
                            int flag = 0;
                            for(int j = 0; j < node; j++)
                            {
                                if(lines[one][j] == 1)
                                {
                                    if(visited[j] == 0)
                                    {
                                        flag = 1;
                                    }
                                }
                            }

                            if(flag == 0)
                            {
                                newTrace.remove(one);
                            }

                            que.add(new dot(d.lamb+1, d.wolf, newTrace, newVisited));
                        }

                        // 늑대인 경우
                        else if(info[i] == 1)
                        {
                            // 늑대가 하나 더 들어와도 양이 더 많을 때만 탐색
                            if(d.wolf + 1 < d.lamb)
                            {
                                // 탐색 가능한 노드의 깊은 복사
                                ArrayList<Integer> newTrace = new ArrayList<>();
                                for(Integer traceOne : d.trace)
                                {
                                    newTrace.add(traceOne);
                                }
                                newTrace.add(i);

                                // 지나온 길에 대한 깊은 복사
                                int[] newVisited = new int[node];
                                for(int j = 0; j < node; j++)
                                {
                                    newVisited[j] = d.visited[j];
                                }
                                newVisited[i] = 1;

                                // 탐색 가능한 노드 중에서 갈 수 있는 모든 길을 탐색한 경우 제거해준다.
                                int flag = 0;
                                for(int j = 0; j < node; j++)
                                {
                                    if(lines[one][j] == 1)
                                    {
                                        if(visited[j] == 0)
                                        {
                                            flag = 1;
                                        }
                                    }
                                }

                                if(flag == 0)
                                {
                                    newTrace.remove(one);
                                }

                                que.add(new dot(d.lamb, d.wolf+1, newTrace, newVisited));
                            }
                        }
                    }
                }
            }
        }


        return answer;
    }

    static class  dot
    {
        int lamb;
        int wolf;
        ArrayList<Integer> trace;
        int[] visited;

        dot(int lamb, int wolf, ArrayList<Integer> trace, int[] visited)
        {
            this.lamb = lamb;
            this.wolf = wolf;
            this.trace = trace;
            this.visited = visited;
        }
    }

}
