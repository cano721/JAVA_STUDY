import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    static Circle[] map;
    static long[] changed;
    static int N;
    static int K;
    static int M;
    static int answer;
    static int cnt;
    static int sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        answer = 0;

        map = new Circle[N+1];
        for(int i= 0; i<N+1; i++)map[i] = new Circle(M);
        for(int i=1; i<N+1; i++){
            String[] nums = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                map[i].nums[j] = Integer.parseInt(nums[j]);
            }
        }

//        debug();
        for(int i=0; i<K; i++){
            boolean flag = false;
            sum = 0;
            cnt = 0;
            String[] trial = br.readLine().split(" ");
            int multiple = Integer.parseInt(trial[0]);
            int dir = Integer.parseInt(trial[1]);
            int dist = Integer.parseInt(trial[2]);
            int maxMultipleCnt = N / multiple;
            changed = new long[N+1];

            for(int j = 1; j<= maxMultipleCnt; j++){
                rotate(j*multiple,dir, dist);
            }

            for(int j = 1; j <N+1; j++){
                flag |=modifyCircle(j);
            }

            if(!flag){
                for(int j=1; j< N+1; j++){
                    compareWithAvg(map[j]);
                }
            }


//            debug();
        }

        for(int i =1; i< N+1; i++){
            for(int j= 0; j< M; j++){
                answer+= map[i].nums[j];
            }
        }

        System.out.println(answer);
    }
    public static void rotate(int circleNum, int dir, int dist){
        Circle target = map[circleNum];
        int val = 1;
        if(dir == 0) val = -1;
        target.pointer = (target.pointer + (val * (dist % M ) + M )) % M;
    }

    public static boolean modifyCircle(int circleNum){
        Circle target = map[circleNum];
        Circle next = map[(circleNum + 1) % (N+1)];
        boolean isLast = (circleNum == N);
        boolean flag = false;


        for(int i= 0; i< M; i++){
            int pos = (target.pointer + i + M) % M;
            int nextPos = (next.pointer + i + M) % M;
            int j = (pos + 1) % M;


            int val =target.nums[i];
            if(val != 0){ // 전체값 핪 및 개수
                sum += val;
                cnt++;
            }

            if(target.nums[pos] == 0 ) continue; // 0이면 continue;
            if(target.nums[j]==target.nums[pos]){ // 오른쪽 값과 비교
                    changed[circleNum] |= (1L <<j| 1L <<pos);
                    flag = true;
            }

            if(!isLast && target.nums[pos] == next.nums[nextPos] ){//위 원판과 비교
                changed[circleNum] |= 1L <<pos;
                changed[(circleNum+1) % (N+1)] |= 1L <<nextPos;
                flag = true;
            }

        }

        for(int i=0; i<M; i++){
            if((changed[circleNum] & (1L <<i))>0) target.nums[i] = 0;
        }

        return flag;
    }

    public static void compareWithAvg(Circle circle){

        for(int i=0; i<M; i++){
            int val = circle.nums[i];
            if(val != 0){
                int num = val*cnt;
                if(num > sum) circle.nums[i]--;
                else if(num < sum) circle.nums[i]++;
            }
        }
    }

    public static void debug(){
        for(int i=1; i< N+1; i++){
            Circle circle = map[i];
            int pointer = circle.pointer;
            for(int j=0; j<M;j++){
                System.out.print(circle.nums[(pointer + j) % M]+" ");
            }
            System.out.println(map[i].pointer);
        }
        System.out.println("");
    }

}

class Circle{
    int size;
    int pointer = 0;
    int[] nums;

    public Circle(int M){
        size = M;
        nums = new int[M];
    }

}