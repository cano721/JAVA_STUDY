import java.util.*;
import java.io.*;

/**
 * 위상정렬 풀이
 * 기존과 같으나, 시간작업이 더해짐.
 * 
 * 현재 작업을 하기위해 기존에 해야할 작업들 중 최대걸린 시간 + 현재 작업시간을 저장해두기.
 * answerList[현재작업] = Math.max(answerList[기존작업] + times[현재작업]);
 * 그후 모든 작업이 끝나면, 작업마다 최종 끝난 시간 중 제일 마지막에 끝난 시간을 출력
 * 
 */
public class BJ2056_작업 {

    public static int n,answer;
    public static int[] degree,times,answerList;
    public static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        
        degree = new int[n+1];
        times = new int[n+1];
        answerList = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            times[i] = time;
            while(st.hasMoreTokens()){
                int b = Integer.parseInt(st.nextToken());
                graph[b].add(i);
                degree[i]++;
            }
        }

        topological_sort();
        for(int i = 0; i < answerList.length; i++){
            answer = Math.max(answer,answerList[i]);
        }
        System.out.println(answer);
    }

    public static void topological_sort(){
        
        Queue<Integer> q = new LinkedList<>();
        for(int i =1; i <= n; i++){
            if(degree[i] == 0){
                q.offer(i);
                answerList[i] = times[i];
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i = 0; i < graph[cur].size(); i++){
                int next = graph[cur].get(i);
                degree[next]--;
                // 현재 작업을 끝내야 지을 수 있으므로, 최대값 저장
                answerList[next] = Math.max(answerList[next],answerList[cur] + times[next]);
                if(degree[next] == 0){ 
                    q.offer(next);
                }
            }
        }
    }
}
