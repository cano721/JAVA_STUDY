import java.util.*;
import java.io.*;

/**
 * 유니온파인드로 합치고 그 네트워크에 몇명이 있는지 체크
 * 
 */
public class BJ4195_친구네트워크 {

    public static int f;
    public static int[] parent; //부모노드
    public static int[] netCnt; //노드개수
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < t; tc++){
            

            f = Integer.parseInt(br.readLine());
            parent = new int[f*2];
            netCnt = new int[f*2];
            // 초기화작업
            for(int i = 0; i < f*2;i++){
                parent[i] = i;
                netCnt[i] = 1;
            }
            //스트링 -> 인트변환 아이디값 해쉬맵
            HashMap<String,Integer> map = new HashMap<>();
            int idx = 0; //인트 아이디
            for(int i = 0; i < f; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                //저장되어있지않으면 해쉬맵에 인트저장
                if(!map.containsKey(f1)){
                    map.put(f1,idx++);
                }
                if(!map.containsKey(f2)){
                    map.put(f2,idx++);
                }
                bw.write(union(map.get(f1),map.get(f2))+"\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static int find(int x){
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    public static int union(int x, int y){
        x = find(x);
        y = find(y);
        //서로 부모가 같다면 x의 노드수 반환
        if(x == y) return netCnt[x];
        //같지않으면 x로 합치기
        parent[y] = x;
        netCnt[x] += netCnt[y];
        netCnt[y] = 1;

        return netCnt[x];
    }
}
