import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int r, c, k;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] input = br.readLine().split(" ");
        String[] input = br.readLine().split(" ");
        map = new int[3][3];
        r = Integer.parseInt(input[0])-1;
        c = Integer.parseInt(input[1])-1;
        k = Integer.parseInt(input[2]);

        for(int i= 0; i<3; i++){
            String[] nums = br.readLine().split(" ");
            for(int j=0; j<3; j++){
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }


        int time = 0;
        while(time<=100){

            int R = map.length;
            int C = map[0].length;

            if(R>r&&C>c &&map[r][c]==k){
                break;
            } 

            if(R>=C) rowOp(R,C);
            else colOp(R,C);

            time++;
        }

        if(time>100) System.out.println(-1);
        else System.out.println(time);

    }

    public static ArrayList<Integer> calc(ArrayList<Integer> list){
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
           return (a.cnt != b.cnt) ? a.cnt - b.cnt : a.val - b.val;
        });
        ArrayList<Integer> result = new ArrayList<>();
        Collections.sort(list);
        int current= 0;
        int cnt = 0;

        for(int i=0; i<list.size(); i++){
            if(list.get(i) == 0) continue;
            if(current != list.get(i)){
                if(current!=0) pq.offer(new Pair(current, cnt));
                current = list.get(i);
                cnt = 0;
            }
            cnt++;

            if(i == list.size()-1) pq.offer(new Pair(current,cnt));
        }

        while(!pq.isEmpty()){
            Pair pair= pq.poll();
            result.add(pair.val);
            result.add(pair.cnt);
        }
        return result;
    }

    public static void rowOp(int N, int M){
        ArrayList<Integer>[] list = new ArrayList[N];
        int maxsize = 0;
        for(int i=0; i<N; i++){
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int j=0; j<M; j++){
                tmp.add(map[i][j]);
            }
            list[i] = calc(tmp);
            maxsize = Integer.max(list[i].size(),maxsize);
        }

        int[][] tmpMap = new int[N][maxsize];

        for(int i=0; i< N; i++){
            for(int j=0; j<list[i].size(); j++){
                tmpMap[i][j] = list[i].get(j);
            }
        }

        map = tmpMap;
    }
    public static void colOp(int N, int M){
        ArrayList<Integer>[] list = new ArrayList[M];
        int maxsize = 0;
        for(int i=0; i<M; i++){
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int j=0; j<N; j++){
                tmp.add(map[j][i]);
            }
            list[i] = calc(tmp);
            maxsize = Integer.max(list[i].size(),maxsize);
        }

        int[][] tmpMap = new int[maxsize][M];

        for(int i=0; i< M; i++){
            for(int j=0; j<list[i].size(); j++){
                tmpMap[j][i] = list[i].get(j);
            }
        }

        map = tmpMap;
    }

    public static void debug(){
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

 }

 class Pair{
    int val;
    int cnt;
    public Pair(int a, int b){
        val = a;
        cnt = b;
    }
 }