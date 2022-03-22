import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// arraylist set(idx,val) / add(idx,val) / sublist(from,to ).clear() 등의 메소드는 유용하니 기억할 것.

public class Main {
    static int[] dx = new int[]{-1, 0 , 1, -1, 1 ,-1, 0, 1 };
    static int[] dy = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        // write your code here
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);
        LandInfo[][] map = new LandInfo[N][N];
        for(int i=0; i< N; i++){
            String[] split = br.readLine().split(" ");
            for(int j=0; j<N;j++){
                map[i][j] = new LandInfo(5, Integer.parseInt(split[j]));
//                System.out.println(map[i][j]    );
            }
        }

        for(int i=0; i<M; i++){
            String[] split = br.readLine().split(" ");
            map[Integer.parseInt(split[0])-1][Integer.parseInt(split[1])-1].trees.add(Integer.parseInt(split[2]));
        }
        for(int i=0; i<K; i++){
            ssfw(map);
        }

        int cnt =0;
        for(int i=0; i<N;i++){
            for(int j=0; j<N;j++){
                cnt += map[i][j].trees.size();
            }
        }

        System.out.println(cnt);
    }

    public static void ssfw(LandInfo[][] map){
        int len = map.length;
        int[][] babyTree = new int[len][len];

        for(int i=0; i<len;i++){
            for(int j=0; j<len; j++){
                LandInfo li = map[i][j];
                int deadtree = 0;
                int deadIdx = li.trees.size();

                for(int k =0; k< li.trees.size(); k++){
                    int age = li.trees.get(k);
                    if(li.val >= age) {
                        li.val -= age;
                        li.trees.set(k,age+1);

                        if((age+1) % 5 == 0) babyTree[i][j]++;
                    }
                    else{
                        if(deadIdx == li.trees.size()) deadIdx = k;
                        deadtree += age/2;
                    }
                }
                li.trees.subList(deadIdx,li.trees.size()).clear();
                li.val += (deadtree + li.fur);
            }
        }
        for(int i=0; i<len; i++){for(int j=0; j<len;j++) {newTree(map,i,j,babyTree[i][j]); }}// 위치 조심.
    }

    public static void newTree(LandInfo[][] map, int row, int col, int num){// dependency가 존재하는지 확인할 것. task dependency 뿐만이 아닌 data dependency도 존재함
        if(num ==0) return;
        int len = map.length;

        for(int i=0; i< 8; i++){
            int r = row +dy[i];
            int c = col +dx[i];
            if(r<0||r>=len||c<0||c>=len) continue;
            for(int j=0; j<num; j++) map[r][c].trees.add(0,1);
        }
    }

}

class LandInfo{
    ArrayList<Integer> trees = new ArrayList<>();
    int val = 0;
    int fur = 0;
    public LandInfo(int v, int f){ val = v; fur = f;}
}