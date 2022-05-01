import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T, x, d, k;
    static LinkedList<Integer>[] q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        q = new LinkedList[N+1];

        for (int i = 1; i <= N; i++) {
            q[i] = new LinkedList();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                q[i].offer(Integer.parseInt(st.nextToken()));
            }
        }
//        print("초기상태");
        int ans = 0;
        // T번 돌린다.
        int sum = 0;// 인접한 수가 없을 경우를 대비해 더해놓을 변수
        int cnt = 0;// 합을 수의 갯수로 나누기 위해 저장할 변수
        while (T --> 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            sum = 0;
            cnt = 0;
            HashMap<String, String> hashMap = new HashMap<>(); //돌릴 좌표가 중복되지 않게 저장할 hashmap

            // x의 배수 원판만 d 방향으로 돌린다.
            for (int i = 1, xi = x; xi <= N; i++, xi = x * i) {
                // k칸 돌린다.
                int kk = k;
                while (kk --> 0) {
                    if (d == 0) {
                        // 시계방향
//                        System.out.println("돌린다");
                        q[xi].addFirst(q[xi].removeLast());
                    } else {
                        q[xi].addLast(q[xi].removeFirst());
                    }
                }
            }
//            print("돌린 후");
            // 인접한 수가 있는지 확인
            for (int j = 1; j <= N; j++) {
                for (int l = 0; l < M; l++) {
                    int value = q[j].get(l);
                    if (value == -1) continue;
                    sum += value;
                    cnt++;
//                    if (j == 1 && l == 0) {
//                        System.out.println();
//                    }
                    if (value == q[j].get((l+1)%M)) {
                        hashMap.put(j+","+l, j+","+l);
                        hashMap.put(j+","+((l+1)%M),j+","+((l+1)%M));
                    } 
                    
                    if (j < N && value == q[j+1].get(l)) {
                        hashMap.put(j+","+l, j+","+l);
                        hashMap.put((j+1)+","+l, (j+1)+","+l);
                    }
                }
            }
            int size = hashMap.size();
            if (size > 0) {
                for (String s : hashMap.keySet()) {
                    String[] split = s.split(",");
                    int ii = Integer.parseInt(split[0]);
                    int jj = Integer.parseInt(split[1]);
                    q[ii].set(jj, -1);
                }
            } else {
                // 없으면 원판안의 수들이 평균보다 큰 수는 원래 수 -1, 작은 수는 원래 수 +1로 고친다.
                if (cnt == 0) break;
                double avg = (double)sum / cnt;
                for (int j = 1; j <= N; j++) {
                    for (int l = 0; l < M; l++) {
                        int num = q[j].poll();
                        if (num > 0) {
                            if ((double) num < avg) {
                                num++;
                            } else if ((double) num > avg) {
                                num--;
                            }
                        }
                        q[j].addLast(num);
                    }
                }
            }
//            print("인접한수 확인 후");
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                int num = q[i].poll();
                if (num > 0) ans += num;
            }
        }
        System.out.println(ans);
    }

    private static void print(String message) {
        System.out.println("=============" + message + "=============");
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(q[i].get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
