import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int N, M;
    static int[][] map;
    static ArrayList<int[]> houses;
    static ArrayList<int[]> chickens;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        map = new int[N][N];
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        for(int i=0; i<N; i++){
            String[] nums = br.readLine().split(" ");
            for(int j=0; j<N;j++){
                int num = Integer.parseInt(nums[j]);
                if(num == 1) houses.add(new int[]{i,j});
                else if(num == 2) chickens.add(new int[]{i,j});
                map[i][j] = num;
            }
        }

        combination(0,0,0,M);
        System.out.println(min);

    }

    public static void combination(int bitmask, int idx, int cnt, int size){
        if(size == cnt){
            int sum = 0;
            for(int[] house: houses){
                int partialMin = Integer.MAX_VALUE;
                for(int i=0; i<chickens.size(); i++){
                    int[] chicken = chickens.get(i);
                    if((bitmask&1<<i)>0){
                        int dist = Math.abs(house[0]-chicken[0])+Math.abs(house[1]-chicken[1]);
                        partialMin = Integer.min(dist,partialMin);
                    }
                }
                sum+= partialMin;
            }
            min = Integer.min(sum,min);
            return;
        }

        for(int i=idx; i<chickens.size(); i++){
            if((bitmask&1<<i)>0) continue;
                combination(bitmask|(1<<i),i+1,cnt+1,size);

        }
    }
}