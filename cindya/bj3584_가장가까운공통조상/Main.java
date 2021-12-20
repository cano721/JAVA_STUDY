package cindya.bj3584_가장가까운공통조상;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<Integer>[] lists;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        // test case 만큼 루프
        while (t-- > 0){
            int n = Integer.parseInt(br.readLine());
            lists = new List[n + 1];
            parent = new int[n + 1];
            for(int i = 0; i <= n; i++)
                lists[i] = new ArrayList<>();

            for(int i = 0; i < n - 1; i++){
                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken(" ")), c = Integer.parseInt(st.nextToken());
                parent[c] = p;
                lists[i].add(p);
            }
            st = new StringTokenizer(br.readLine());
            int target1 = Integer.parseInt(st.nextToken(" ")), target2 = Integer.parseInt(st.nextToken());
            List<Integer> t1Root = new ArrayList<>();
            // 첫번째 타겟의 부모를 모두 저장
            while (target1 > 0){
                t1Root.add(target1);
                target1 = parent[target1];
            }
            // 두번째 타겟의 부모가 첫번째 타겟의 부모 중 있는 지 확인
            while (target2 > 0){
                // 있으면 출력하고 중지
                if(t1Root.contains(target2)){
                    bw.write(target2 + "\n");
                    break;
                }
                //없으면 다음 부모로 올라감
                target2 = parent[target2];
            }
        }
        br.close();
        bw.close();
    }
}
