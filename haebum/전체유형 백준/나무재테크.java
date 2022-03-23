/*
    1. 칸 별 나무 리스트, 죽은 리스트, 양분 존재(초기5)

    2. 봄엔 칸별 나무리스트가 나이가 어린 나무부터 먹기 시작.
        못먹은 나무들은 죽은 리스트에 저장.
    
    3. 여름엔 죽은 리스트/2를 칸의 양분으로 추가

    4. 가을엔 5의 배수인 나무들이 주변 8칸으로 번식(번식나무는 나이1)

    5. 겨울엔 지정된 양분만큼 추가

    시간복잡도 n*n(맵돌기) *(나무리스트정렬 후 돌기 klogk인접) *8(나무심기) 이게 최대

    10^7아래일것으로 판단

*/

import java.util.*;
import java.io.*;

public class 나무재테크 {

    public static int n,m,k,result;
    public static Maps[][] map;
    public static int[][] death, nutriment;

    public static class Maps{
        int nutriment = 5;
        ArrayList<Integer> trees = new ArrayList<>();

        public Maps(){
        }

        public void treesSort(){
            if(trees.size() > 1){
                Collections.sort(trees);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new Maps[n][n];
        death = new int[n][n];
        nutriment = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n; j++){
                nutriment[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new Maps();
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());
            Maps trees = map[x][y];
            trees.trees.add(z);
        }

        for(int i = 0; i < k; i++){
            springAndSummer();
            fall();
            winter();
        }
        check();
        System.out.println(result);
    }

    public static void check(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Maps trees = map[i][j];
                result += trees.trees.size();
            }
        }
    }

    public static void springAndSummer(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ArrayList<Integer> temp = new ArrayList<>();
                Maps trees = map[i][j];
                trees.treesSort();
                int curNutrement = trees.nutriment;
                for(int idx = 0; idx < trees.trees.size(); idx++){
                    int tree = trees.trees.get(idx);
                    if(tree <= curNutrement){
                        curNutrement -= tree;
                        tree++;
                        temp.add(tree);
                    }else{
                        death[i][j] += tree/2;
                    }
                }
                trees.trees = temp;
                trees.nutriment = death[i][j] + curNutrement;
                death[i][j] = 0;
            }
        }
    }

    public static void fall(){
        int[][] dirXY = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,-1},{-1,1}};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Maps trees = map[i][j];
                for(int idx = 0; idx < trees.trees.size(); idx++){
                    int tree = trees.trees.get(idx);
                    if(tree % 5 == 0){
                        for(int vec = 0; vec < 8; vec++){
                            int nx = i + dirXY[vec][0];
                            int ny = j + dirXY[vec][1];

                            if(nx < 0 || nx >= n || ny < 0 || ny >= n){
                                continue;
                            }
                            map[nx][ny].trees.add(1);
                        }
                    }
                }
            }
        }
    }

    public static void winter(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j].nutriment += nutriment[i][j];
            }
        }
    }
}
