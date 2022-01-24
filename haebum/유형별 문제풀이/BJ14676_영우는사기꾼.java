import java.util.*;
import java.io.*;

/**
 * 위상정렬로 관계연결
 * 
 * 그래프 연결 -> graph[건설할건물].add(다음 건설할 수 있는 건물)
 * 
 * 건물 건설 시 현재 건물의 진입차수가 0이거나 작지않으면 거짓말쟁이
 * 0이면 건설 후 다음 건설할 수 있는 건물들 진입차수 감소
 * 
 * 그 후 건설 개수 증가
 * 
 * 파괴할땐 건설되어있는지만 확인하여 파괴
 * 
 * 파괴되었을때 건물수가 0이되면 그 건물 이후 건설건물들의 진입차수 증가
 * 
 * 게임 정보를 돌리는 도중 건설할 수 없는 건물이 나오거나
 * 건설되지 않은 건물을 파괴하면 Lier
 * 정상 작동시 king-god-emperor
 * 
 * 시간복잡도를 감소하기위해
 * 거짓말쟁이인게 이미 판별나면 입력값만 받고 넘어가기
 * 이미 한번 건설했던 건물이면 다음 건물들 진입차수 따로 안함.
 */
public class BJ14676_영우는사기꾼 {

    public static int n,m,k;
    public static int[] build,degree;
    public static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        boolean answer = true;

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        build = new int[n+1];
        degree = new int[n+1];
        graph = new ArrayList[n+1];
        
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        //그래프 생성
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int buildingA = Integer.parseInt(st.nextToken());
            int buildingB = Integer.parseInt(st.nextToken());

            graph[buildingA].add(buildingB);
            degree[buildingB]++;
        }

        //커맨드 실행
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            // 거짓말쟁이면 넘어가기
            if(answer == false) continue;
            int command = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken());

            //건물 짓기
            if(command == 1){
                // if(build[building] > 0){
                //     build[building]++;
                //     continue;
                // }

                // 진입차수가 아직 남아있다면 현재 건물 건설불가이므로 사기꾼
                if(degree[building] > 0){
                    answer = false;
                    continue;
                }
                // 아니라면 건설 가능
                build[building]++;
                // 진입차수들 감소
                for(int j = 0; j < graph[building].size(); j++){
                    int next = graph[building].get(j);
                    degree[next]--;
                }
            //건물 파괴
            }else{
                // 지어져있다면 파괴
                if(build[building] > 0){
                    build[building]--;
                    // 파괴해서 건물이 0이 되었다면 이후 관련 건물들 건설 불가
                    if(build[building] == 0){
                        for(int j = 0; j < graph[building].size(); j++){
                            int next = graph[building].get(j);
                            degree[next]++;
                        }
                    }
                // 건물 없는데 파괴하면 사기꾼
                }else{
                    answer = false;
                }
            }
        }

        if(answer){
            System.out.println("King-God-Emperor");
        }else{
            System.out.println("Lier!");
        }
    }
}