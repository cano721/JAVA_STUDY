import java.util.*;
import java.io.*;

/**
 * 위상정렬로 관계연결
 * 
 * 그래프 연결 -> graph[건설할건물].add(사전에 건설되어야하는 건물)
 * 
 * 건물 건설 할땐 사전에 건설되어야할 것들이 건설되어있는지 확인
 * 그 후 건설 개수 증가
 * 
 * 파괴할땐 건설되어있는지만 확인하여 파괴
 * 
 * 게임 정보를 돌리는 도중 건설할 수 없는 건물이 나오거나
 * 건설되지 않은 건물을 파괴하면 Lier
 * 정상 작동시 king-god-emperor
 */
public class BJ14676_영우는사기꾼 {

    public static int n,m,k;
    public static int[] build;
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
        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        //그래프 생성
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int buildingA = Integer.parseInt(st.nextToken());
            int buildingB = Integer.parseInt(st.nextToken());

            graph[buildingB].add(buildingA);
            
        }

        //커맨드 실행
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken());

            //건물 짓기
            if(command == 1){
                for(int j = 0; j < graph[building].size(); j++){
                    int before = graph[building].get(j);
                    // 사전 건물이 안지어져있다면 사기꾼
                    if(build[before] <= 0){
                        answer = false;
                        break;
                    // 
                    }
                }
                //다 지어져있다면 건물 짓기
                build[building]++;
            //건물 파괴
            }else{
                // 지어져있다면 파괴
                if(build[building] > 0){
                    build[building]--;
                // 아니면 사기꾼
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