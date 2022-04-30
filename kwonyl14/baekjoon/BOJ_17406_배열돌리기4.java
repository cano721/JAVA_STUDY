import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class Rotate{
        int r, c, s;

        public Rotate(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    public static int N, M, K, min;
    public static int[][] maps;
    public static Rotate[] rt;        //회전 도는 애 저장
    //배열 A의 최솟값을 출력한다.
    public static void main(String[] args) throws Exception {
        INIT();
        min = 876543219;
        perm(0, 0);
        System.out.println(min);
    }
    public static void INIT() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 배열의 크기 N, M: 3<= N,M <=50
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        // 1<= maps[i][j] <= 100
        maps = new int[N][M];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //회전연산 정보: 1 <= K <=6
        // r, c, s    가장 윗칸 (r-s, c-s)에서 가장 아랫칸(r+s, c+s) 까지 돌린다
        // r-1, c-1로 만들어줘야 편함
        rt = new Rotate[K];
        for(int i = 0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            rt[i] = new Rotate(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
        }
        br.close();
    }

    public static void perm(int dep, int start) {
        if(dep == K) {
            min = Math.min(Rsum(rot(rt)), min);
            return;
        }
        
        for(int i = start; i<K; i++) {
            //swap
            Rotate tmp = rt[i];
            rt[i] = rt[start];
            rt[start] = tmp;
            
            perm(dep+1, start+1);
            
            //reset
            tmp = rt[i];
            rt[i] = rt[start];
            rt[start] = tmp;
        }
    }
    public static int[][] rot(Rotate[] x) {
        //map copys
        int[][] copys = new int[N][M];
        for(int i = 0; i<N; i++) {
            copys[i] = maps[i].clone();
        }
        
        for(int t = 0; t<x.length; t++) {
            //중앙을 중심으로 s번 회전 수행
            for(int size = 1; size<=x[t].s; size++) {
                int r = x[t].r - size;
                int c = x[t].c - size;
                int last = copys[r][c];
                //1. up
                for(int i = 0; i<(size*2); i++) {
                    copys[r][c] = copys[r+1][c];
                    r++;
                }
                //2. left
                for(int i = 0; i<(size*2); i++) {
                    copys[r][c] = copys[r][c+1];
                    c++;
                }
                //3. down
                for(int i = 0; i<(size*2); i++) {
                    copys[r][c] = copys[r-1][c];
                    r--;
                }
                
                //4. right
                for(int i = 0; i<(size*2); i++) {
                    copys[r][c] = copys[r][c-1];
                    c--;
                }
                copys[r][c+1] = last;
    
            }//ROTATION END
        }//turn end
        
        return copys;
    }
    public static int Rsum(int[][] copys) {
        int min = 987654321;
        for(int i = 0; i<N; i++) {
            int sum = 0;
            for(int j = 0; j<M; j++) {
                sum += copys[i][j];
            }
            min = min<sum?min:sum;    //삼항연산자
        }
        return min;
    }
}