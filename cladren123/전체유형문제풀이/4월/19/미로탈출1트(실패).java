/*

경로는 두 노드의 상태에 따라 결정 





*/


import java.util.*;

class Solution {

    static HashMap<Integer, Integer> trapCheck;
    static int[][][][] visited;
    static HashMap<Integer, ArrayList<Integer>> ns;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = Integer.MAX_VALUE;

        int[][] road = new int[n+1][n+1];
        visited = new int[n+1][n+1][2][2];
        ns = new HashMap<>();
        trapCheck = new HashMap<>();


        HashMap<Integer, Integer> trapStatus = new HashMap<>();

        for(int one : traps)
        {
            trapCheck.put(one, 0);
            trapStatus.put(one, 0);
        }

        for(int[] one : roads)
        {
            int s = one[0];
            int e = one[1];
            int cost = one[2];

            if(road[s][e] == 0)
                road[s][e] = cost;
            else
                road[s][e] = Math.min(road[s][e], cost);

            if(ns.containsKey(s))
            {
                if(ns.get(s).contains(e)) continue;
                ns.get(s).add(e);
            }
            else
            {
                ArrayList<Integer> caseOne = new ArrayList<>();
                caseOne.add(e);
                ns.put(s, caseOne);
            }

            if(ns.containsKey(e))
            {
                if(ns.get(e).contains(s)) continue;
                ns.get(e).add(s);
            }
            else
            {
                ArrayList<Integer> caseOne = new ArrayList<>();
                caseOne.add(s);
                ns.put(e, caseOne);
            }
        }

        Queue<dot> que = new LinkedList<>();

        que.add(new dot(start, 0, 0, road, trapStatus));

        while(!que.isEmpty())
        {
            dot d = que.poll();

            if(d.number == end)
            {
                answer = Math.min(answer, d.cost);
                continue;
            }

            for(int next = 1; next < n+1; next++)
            {
                // 길이 있는 경우
                if(d.road[d.number][next] >= 1)
                {
                    // 도착지가 함정일 경우
                    if(trapCheck.containsKey(next))
                    {
                        int ts = d.trapStatus.get(next);

                        if(visited[d.number][next][d.status][ts] == 0)
                        {
                            visited[d.number][next][d.status][ts] = 1;

                            if(next == end)
                            {
                                answer = Math.min(answer, d.cost + d.road[d.number][next]);
                                continue;
                            }


                            HashMap<Integer, Integer> newtrapStatus = new HashMap<>();
                            for(Integer one : d.trapStatus.keySet())
                            {
                                newtrapStatus.put(one, d.trapStatus.get(one));
                            }
                            if(newtrapStatus.get(next) == 0)
                                newtrapStatus.put(next,1);
                            else if(newtrapStatus.get(next) == 1)
                                newtrapStatus.put(next,0);

                            int[][] newRoad = new int[n+1][n+1];
                            for(int j = 0; j < n+1; j++)
                                for(int k = 0; k <n+1; k++)
                                    newRoad[j][k] = d.road[j][k];

                            // 함정을 밟았을 경우 화살표가 바뀐다.
                            for(Integer one : ns.get(next))
                            {
                                int temp1 = newRoad[next][one];
                                int temp2 = newRoad[one][next];
                                newRoad[next][one] = temp2;
                                newRoad[one][next] = temp1;
                            }


                            que.add(new dot(next, d.cost + d.road[d.number][next], newtrapStatus.get(next), newRoad, newtrapStatus));
                        }

                    }
                    // 함정이 아닐 경우
                    else
                    {
                        if(visited[d.number][next][d.status][0] == 0)
                        {
                            visited[d.number][next][d.status][0] = 1;

                            if(next == end)
                            {
                                answer = Math.min(answer, d.cost + d.road[d.number][next]);
                                continue;
                            }

                            que.add(new dot(next, d.cost + d.road[d.number][next], 0, d.road, d.trapStatus));
                        }

                    }

                }
            }


        }





        return answer;
    }


    public class dot implements Comparable<dot>
    {
        int number;
        int cost;
        int status;

        int[][] road;

        HashMap<Integer, Integer> trapStatus;


        dot(int number, int cost, int status, int[][] road, HashMap<Integer, Integer> trapStatus)
        {
            this.number = number;
            this.cost = cost;
            this.status = status;
            this.road = road;
            this.trapStatus = trapStatus;
        }

        @Override
        public int compareTo(dot o)
        {
            return this.cost - o.cost;
        }
    }






}