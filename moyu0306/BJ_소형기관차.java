    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.Arrays;
    import java.util.StringTokenizer;

public class Main {
        static int N, M;
        static int[] rooms;
        static int[][] DP;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N= Integer.parseInt(br.readLine());
            DP = new int[N+1][4];
            rooms = new int[N+1];

            StringTokenizer stk = new StringTokenizer(br.readLine()," ");
            M = Integer.parseInt(br.readLine());
            for(int i = 1; i<N+1; i++) Arrays.fill(DP[i],-1);
            for(int i=1; i<N+1; i++){
                rooms[i]+= (rooms[i-1]+Integer.parseInt(stk.nextToken()));
            }
            System.out.println(getPeople(M,3,N));
        }

        public static int getPeople(int length, int num, int idx){
            if(DP[idx][num]!= -1) return DP[idx][num];
            if(num == 0) return 0;
            int nextIdx = (idx-length<0) ? 0 : idx-length;
            return DP[idx][num] = Integer.max(getPeople(length,num,idx-1),getPeople(length,num-1,nextIdx)+rooms[idx]-rooms[nextIdx]);
        }
}