/**
 * 특정 구간의 합을 구할거고, 원본 데이터가 계속 변경됨
 * 
 * 세그먼트 트리 or 펜윅 트리 중 펜윅 트리 이용하여 푼 코드
 * 
 * 입력으로 주어지는 값은 long이라는 점에서 주의
 * 
 */

import java.io.*;
import java.util.StringTokenizer;

public class BJ2042_구간합구하기_펜윅트리 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n+1];
        Fenwick ftree = new Fenwick(n);

        for(int i = 1; i <= n; i ++){
            arr[i] = Long.parseLong(br.readLine());
            // 펜윅트리 초기 데이터 넣기
            ftree.update(i, arr[i]);
        }

        for(int i = 0; i < m+k; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){
                ftree.update(b,c -arr[b]);
                arr[b] = c;
            }else{
                bw.write(ftree.sum((int)c) - ftree.sum(b-1) +"\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static class Fenwick{
        long[] tree;

        public Fenwick(int arrSize){
            // 인덱스만큼 생성
            tree = new long[arrSize+1];
        }

        // 데이터 변경
        public void update(int idx, long diff){
            while(idx < tree.length){
                tree[idx] += diff;
                idx += (idx&-idx); 
            }
        }

        // 특정 인덱스까지의 합
        public long sum(int idx){
            long result = 0;

            while(idx > 0){
                result += tree[idx];

                idx -= (idx & -idx);
            }
            return result;
        }
    }
}
